package com.szn.kaam.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Citation(
    val citation: String,
    val infos: Infos
): Parcelable