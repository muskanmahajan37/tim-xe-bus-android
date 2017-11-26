package com.hungps.timxebus.model

/*
* Author: scit
* Time: 11/25/17
*/

class BusRoute(val bus: Bus = Bus(), name: String, detail: List<String>)
    : Route(name, detail)