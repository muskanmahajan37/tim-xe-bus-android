package com.hungps.timxebus.model

import android.os.Parcel
import android.os.Parcelable

/*
* Author: scit
* Time: 11/27/17
*/

class Route(val name: String = "", val blocks: MutableList<Block>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            arrayListOf<Block>().apply {
                parcel.readList(this, Block::class.java.classLoader)
            }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeList(blocks)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel) = Route(parcel)

        override fun newArray(size: Int): Array<Route?> = arrayOfNulls(size)
    }

}