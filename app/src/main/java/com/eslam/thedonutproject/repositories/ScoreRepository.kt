package com.eslam.thedonutproject.repositories

import com.eslam.thedonutproject.data.remote.Result
import com.eslam.thedonutproject.data.remote.ScoreService
import com.eslam.thedonutproject.domain.DataSource
import com.eslam.thedonutproject.domain.model.ScoreModel
import com.eslam.thedonutproject.utils.toDomain
import com.eslam.thedonutproject.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ScoreRepository @Inject constructor(private val service: ScoreService,private val dispatcher:CoroutineDispatcher):
    DataSource
{
    override suspend fun getRemoteData(): Flow<Result<ScoreModel>> {
        return wrapEspressoIdlingResource {
            flow {
                emit(Result.Loading())

                val response = service.getScore().toDomain()

                emit(Result.Success(response))
            }.catch { e->

                emit(Result.Error("Fetching Score Failed : ${e.message}"))
            }.flowOn(dispatcher)
        }


        }
}