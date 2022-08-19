package com.szn.kaam.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Infos(
    val acteur: String?,
    val auteur: String?,
    val episode: String?,
    val personnage: String?,
    val saison: String?
): Parcelable