package com.szn.kaam.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Citation(
    val citation: String,
    val infos: Infos
): Parcelable