package com.szn.kaam.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.szn.kaam.BuildConfig

@Entity
data class Quote(@PrimaryKey val citation: String, // Une citation par perso
                 val acteur: String?,
                 val auteur: String?,
                 val episode: String?,
                 val personnage: String?,
                 val saison: String?
){

    /**
     * Dans la doc, c'est
     * GET /all/personnage/:personnage/pic
     * en regardant dans le PHP
     * https://github.com/sin0light/api-kaamelott/blob/master/controller/controllerAPI.php
     * on s'appercoit que c'est
     * /api/personnage/[:character]/pic
     */
    fun getPicturePath(): String {
        return BuildConfig.BASE_URL + "personnage/" + personnage + "/pic"
//        return "${BuildConfig.BASE_URL}personnage/$personnage/pic"
    }
}

val MockCitation = Quote("Test", null, null, null, null, null)