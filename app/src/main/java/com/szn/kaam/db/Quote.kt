package com.szn.kaam.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.szn.kaam.network.model.Citation
import com.szn.kaam.network.model.Infos

@Entity
data class Quote(@PrimaryKey val citation: String, // Une citation par perso
                 val acteur: String?,
                 val auteur: String?,
                 val episode: String?,
                 val personnage: String?,
                 val saison: String?
)

val MockCitation = Quote("Test", null, null, null, null, null)