package com.szn.kaam

object Utils {

    /**
     * Dans la doc, c'est
     * GET /all/personnage/:personnage/pic
     * en regardant dans le PHP
     * https://github.com/sin0light/api-kaamelott/blob/master/controller/controllerAPI.php
     * on s'appercoit que c'est
     * /api/personnage/[:character]/pic
     */
    fun getPicturePath(name: String): String {
        return BuildConfig.BASE_URL + "personnage/" + name + "/pic"
    }
}