package com.example.ominext.plaidfork.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Ominext on 6/13/2017.
 */

data class Images(@SerializedName("hidpi") val hidpi: String?,
                  @SerializedName("normal") val normal: String?,
                  @SerializedName("teaser") val teaser: String?) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Images> = object : Parcelable.Creator<Images> {
            override fun createFromParcel(source: Parcel): Images = Images(source)
            override fun newArray(size: Int): Array<Images?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(hidpi)
        dest.writeString(normal)
        dest.writeString(teaser)
    }
}