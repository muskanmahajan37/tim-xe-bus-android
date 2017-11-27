package com.hungps.timxebus.model

import android.os.Parcel
import android.os.Parcelable

/*
* Author: scit
* Time: 11/25/17
*/

class BusBlock(val bus: Bus = Bus(), name: String, detail: Array<String>)
    : Block(name, detail) {

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Bus::class.java.classLoader),
            parcel.readString(),
            parcel.createStringArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(bus, flags)
        super.writeToParcel(parcel, flags)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<BusBlock> {
        override fun createFromParcel(parcel: Parcel) = BusBlock(parcel)

        override fun newArray(size: Int): Array<BusBlock?> = arrayOfNulls(size)
    }

}