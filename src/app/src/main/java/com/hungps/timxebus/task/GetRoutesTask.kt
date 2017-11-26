package com.hungps.timxebus.task

import android.os.AsyncTask
import android.util.Log
import com.hungps.timxebus.model.Bus
import com.hungps.timxebus.model.BusRoute
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.model.WalkRoute
import com.hungps.timxebus.utils.forEachJsonArray
import com.hungps.timxebus.utils.lastIndex
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern


/*
* Author: scit
* Time: 11/24/17
*/
class GetRoutesTask: AsyncTask<String, Void, MutableList<MutableList<Route>>>() {

    override fun doInBackground(vararg args: String?): MutableList<MutableList<Route>> {
        val from = args[0]
        val to = args[1]
        val resultRoutes = mutableListOf<MutableList<Route>>()

        // Send a query to google maps and get all script tags in <head>
        val scriptTags = Jsoup.connect(getUrlQuery(from!!, to!!))
                .get().head()
                .getElementsByTag("script")

        // The data is in the script tag number 7, so if there is no script at position 7, stop the task
        if (scriptTags.size < 7) return mutableListOf()

        val queryData = getDataFromScriptTag(scriptTags[7].html())
        if (queryData != null) {
            // Get All Routes
            val queryRoutes = queryData
                    .getJSONArray(0)
                    .getJSONArray(1)

            queryRoutes.forEachJsonArray { queryRoute ->
                val routes = mutableListOf<Route>()

                val blocks = queryRoute
                        .getJSONArray(1)
                        .getJSONArray(0)
                        .getJSONArray(1)

                blocks.forEachJsonArray { block ->
                    try {
                        val busInfo = block
                            .getJSONArray(0)
                            .getJSONArray(14)
                            .getJSONArray(1)
                            .getJSONArray(1)
                            .getString(0)

                        val isWalk = busInfo.toLowerCase() in arrayOf("đi bộ", "walk")

                        if (isWalk) {
                            val blockRouteDetail = block.getJSONArray(1)

                            routes.add(WalkRoute(
                                detail = List(
                                    blockRouteDetail.length(),
                                    { i -> getWalkRouteDetail(blockRouteDetail.getJSONArray(i)
                                                .getJSONArray(0)
                                                .getJSONArray(14)
                                    )}
                                )
                            ))
                        } else {
                            val blockDetail = block.getJSONArray(0).getJSONArray(6)
                            val blockRouteDetail = blockDetail.getJSONArray(14)

                            routes.add(BusRoute(
                                bus = getBusFromBusInfo(busInfo),
                                name = blockDetail.getString(0),
                                detail = List(blockRouteDetail.length(), { i ->
                                    blockRouteDetail.getJSONArray(i).getString(0)
                                })
                            ))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        // Just for continue the loop
                    }
                }

                resultRoutes.add(routes)
            }
        }

        println()
        return resultRoutes
    }

    private fun getUrlQuery(from: String, to: String): String {
        val from_encoded = URLEncoder.encode(from, StandardCharsets.UTF_8.toString())
        val to_encoded = URLEncoder.encode(to, StandardCharsets.UTF_8.toString())

        return "http://maps.google.com/maps?saddr=${from_encoded}&daddr=${to_encoded}&dirflg=r"
    }

    private fun getDataFromScriptTag(scriptHtml: String): JSONArray? {
        val pattern = Pattern.compile("APP_INITIALIZATION_STATE=(.+?);window\\.APP_FLAGS", Pattern.DOTALL)
        val matcher = pattern.matcher(scriptHtml)

        if (matcher.find()) {
            // The real data is under APP_INITIALIZATION_STATE[3][4]
            var routesInString = JSONArray(matcher.group(1))
                    .getJSONArray(3)
                    .getString(4)

            // There are some unuse string in the beginning of the data, so we remove it
            // And unescape the string
            routesInString = routesInString
                    .replace(")]}'", "")
                    .replace("\n", "")
            return JSONArray(routesInString)
        }
        return null
    }

    private fun getBusFromBusInfo(busInfo: String): Bus {
        val pattern = Pattern.compile("^Tuyến (.+?) - (.+?) (đi|-) (.+?)$", Pattern.DOTALL)
        val matcher = pattern.matcher(busInfo)

        if (matcher.find()) {
            return Bus(
                    id = matcher.group(1),
                    goFrom = matcher.group(2),
                    goTo = matcher.group(4)
            )
        }

        return Bus()
    }

    private fun getWalkRouteDetail(blockRouteDetail: JSONArray): String {
        var result = ""

        blockRouteDetail.forEachJsonArray {
            result += it.getJSONArray(1).getString(0)
        }

        return result
    }
}
