package com.eslam.thedonutproject.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoreModel(val score: Int? = 0,val maxScoreValue: Int? = null,val dashboardStatus: String?,
                      val personaType: String? = null,val accountIDVStatus: String? ) : Parcelable {
}