package com.hungps.timxebus.model

import android.os.Parcel
import android.os.Parcelable

/*
* Author: scit
* Time: 11/12/17
*/

class Bus(val id: String = "", val goFrom: String = "", val goTo: String = ""): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(goFrom)
        parcel.writeString(goTo)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Bus> {
        override fun createFromParcel(parcel: Parcel) = Bus(parcel)

        override fun newArray(size: Int): Array<Bus?> = arrayOfNulls(size)
    }

    override fun equals(other: Any?): Boolean {
        val otherOne = other as Bus
        return id.equals(otherOne.id) && goFrom.equals(otherOne.goFrom) && goTo.equals(otherOne.goTo)
    }

    override fun toString(): String {
        return "Tuyến ${id} - ${goFrom} đi ${goTo}"
    }
}