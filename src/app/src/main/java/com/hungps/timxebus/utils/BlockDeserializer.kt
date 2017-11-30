package com.hungps.timxebus.utils

import com.google.gson.*
import com.hungps.timxebus.model.Block
import java.lang.reflect.Type
import com.hungps.timxebus.model.BusBlock
import com.hungps.timxebus.model.Route
import com.hungps.timxebus.model.WalkBlock


/*
* Author: scit
* Time: 11/30/17
*/


class BlockDeserializer() : JsonDeserializer<MutableList<Route>> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MutableList<Route> {
        val jsonRoutes = json.asJsonArray
        val resultRoutes = mutableListOf<Route>()

        jsonRoutes.forEach {
            val jsonRoute = it.asJsonObject
            val jsonBlocks: JsonArray = jsonRoute.getAsJsonArray("blocks")

            val routeName = jsonRoute.get("name").asString
            val routeBlocks = MutableList(jsonBlocks.size(), { i ->
                val block = jsonBlocks.get(i).asJsonObject
                val blockType = block.get("type").asString

                return@MutableList Gson().fromJson(
                        block,
                        if (blockType.equals("bus"))
                            BusBlock::class.java
                        else
                            WalkBlock::class.java
                )
            })

            resultRoutes.add(Route(routeName, routeBlocks))
        }

        return resultRoutes
    }
}