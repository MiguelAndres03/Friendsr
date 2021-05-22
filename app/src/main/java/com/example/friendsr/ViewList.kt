package com.example.friendsr

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ViewList(val name: String, val description: String, val photo: Int, var score: Float) :
    Parcelable {
}