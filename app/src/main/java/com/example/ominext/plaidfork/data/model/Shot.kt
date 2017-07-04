package com.example.ominext.plaidfork.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Ominext on 6/13/2017.
 */
data class Shot(@SerializedName("id") val id: Int,
                @SerializedName("images") val images: Images?,
                @SerializedName("title") val title: String?,
                @SerializedName("description") val description: String?,
                @SerializedName("created_at") val createdTime: Date?,
                @SerializedName("user") val user: User?,
                @SerializedName("animated") val animate: Boolean,
                @SerializedName("views_count") val viewCount: Int,
                @SerializedName("comments_count") val commentCount: Int,
                @SerializedName("likes_count") val likeCount: Int) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Shot> = object : Parcelable.Creator<Shot> {
            override fun createFromParcel(source: Parcel): Shot = Shot(source)
            override fun newArray(size: Int): Array<Shot?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readParcelable<Images>(Images::class.java.classLoader),
    source.readString(),
    source.readString(),
    source.readSerializable() as Date?,
    source.readParcelable<User>(User::class.java.classLoader),
    1 == source.readInt(),
    source.readInt(),
    source.readInt(),
    source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeParcelable(images, 0)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeSerializable(createdTime)
        dest.writeParcelable(user, 0)
        dest.writeInt((if (animate) 1 else 0))
        dest.writeInt(viewCount)
        dest.writeInt(commentCount)
        dest.writeInt(likeCount)
    }
}