package com.eslam.thedonutproject.utils

import com.eslam.thedonutproject.data.remote.ScoreResponse
import com.eslam.thedonutproject.domain.model.ScoreModel


//mapper from dto to domain model
fun ScoreResponse.toDomain():ScoreModel
{
    return ScoreModel(score = this.creditReportInfo?.score, maxScoreValue = this.creditReportInfo?.maxScoreValue,
    dashboardStatus = this.dashboardStatus,personaType = this.personaType,accountIDVStatus = this.accountIDVStatus)
}