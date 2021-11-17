package com.eslam.thedonutproject.domain

import com.eslam.thedonutproject.data.remote.Result
import com.eslam.thedonutproject.domain.model.ScoreModel
import kotlinx.coroutines.flow.Flow

interface DataSource {

    suspend fun getRemoteData():Flow<Result<ScoreModel>>
}