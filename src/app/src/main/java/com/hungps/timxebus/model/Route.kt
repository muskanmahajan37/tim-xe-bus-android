package com.hungps.timxebus.model

import android.os.Parcel
import android.os.Parcelable
import com.hungps.timxebus.utils.equalsWith

/*
* Author: scit
* Time: 11/27/17
*/

data class Route(val name: String = "", val blocks: MutableList<Block>): Parcelable {
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

    override fun equals(other: Any?): Boolean {
        val otherOne = other as Route
        return name.equals(otherOne.name) && blocks.equalsWith(otherOne.blocks)
    }
}