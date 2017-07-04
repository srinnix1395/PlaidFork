package com.example.ominext.plaidfork.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by DoanNH on 14-Jun-17.
 */

const val NAME1 = "TU"

data class User(@SerializedName("name") val name: String?,
                @SerializedName("avatar_url") val avatar: String?) : Parcelable{

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }

        const val NAME = "TU"
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(avatar)
    }

    fun print(){
        println(NAME)
        println(NAME1)
    }

    fun println(int: Int){
        fun endLine(){
            println()
        }
    }
}


