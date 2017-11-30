package com.hungps.timxebus.model

import android.os.Parcel
import android.os.Parcelable

/*
* Author: scit
* Time: 11/12/17
*/

open class Block(val type: String, val name: String, val detail: Array<String>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeStringArray(detail)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Block> {
        override fun createFromParcel(parcel: Parcel) = Block(parcel)

        override fun newArray(size: Int): Array<Block?> = arrayOfNulls(size)
    }

    override fun equals(other: Any?): Boolean {
        val otherOne = other as Block

        return type.equals(other.type)
                && name.equals(otherOne.name)
                && detail contentDeepEquals otherOne.detail
    }
}
