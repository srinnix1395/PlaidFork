package com.example.ominext.plaidfork.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Admin on 15-Jun-17.
 */

data class Comment(@SerializedName("id") val id: Int,
                   @SerializedName("body") val body: String,
                   @SerializedName("created_at") val createdTime: Date,
                   @SerializedName("user") val user: User) :
        Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Comment> = object : Parcelable.Creator<Comment> {
            override fun createFromParcel(source: Parcel): Comment = Comment(source)
            override fun newArray(size: Int): Array<Comment?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readSerializable() as Date,
            source.readParcelable<User>(User::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(body)
        dest.writeSerializable(createdTime)
        dest.writeParcelable(user, 0)
    }
}

