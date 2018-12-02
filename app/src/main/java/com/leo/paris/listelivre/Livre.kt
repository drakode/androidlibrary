package com.leo.paris.listelivre

import android.os.Parcel
import android.os.Parcelable

data class Livre(val isbn: String, val title: String, val price: String, val cover: String, val synopsis: Array<String>): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(cover)
        parcel.writeStringArray(synopsis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Livre> {
        override fun createFromParcel(parcel: Parcel): Livre {
            return Livre(parcel)
        }

        override fun newArray(size: Int): Array<Livre?> {
            return arrayOfNulls(size)
        }
    }
}