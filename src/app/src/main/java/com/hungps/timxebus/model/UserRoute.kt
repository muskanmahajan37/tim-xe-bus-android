package com.hungps.timxebus.model

/**
 * Created by scit on 11/12/17.
 */

data class UserRoute(val name: String, val goFrom: String, val goTo: String, val buses: MutableList<Bus>)