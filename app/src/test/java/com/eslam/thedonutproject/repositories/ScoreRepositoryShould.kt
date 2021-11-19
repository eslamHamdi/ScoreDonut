package com.eslam.thedonutproject.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eslam.thedonutproject.data.remote.Result
import com.eslam.thedonutproject.data.remote.ScoreResponse
import com.eslam.thedonutproject.data.remote.ScoreService
import com.eslam.thedonutproject.domain.model.ScoreModel
import com.eslam.thedonutproject.utils.MainCoroutineScopeRule
import com.eslam.thedonutproject.utils.toDomain
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ScoreRepositoryShould {


    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()


    private var repository:ScoreRepository? = null
    private var service:ScoreService? = null

    private  var response: ScoreResponse = mock()


    @Before
    fun initializeRepository()
    {
        service = mock()
        repository = ScoreRepository(service = service!!, dispatcher = Dispatchers.Main)

    }

    fun tearDown()
    {
        repository= null
        service = null

    }

    @Test
    fun invoke_getScore()= coroutineTestRule.runBlockingTest {

        repository?.getRemoteData()?.first()

        verify(service, times(1))?.getScore()
    }

    @Test
    fun emitTheCorrectScoreModelUponSuccess() = coroutineTestRule.runBlockingTest{

        whenever(service!!.getScore()).thenReturn(response)

        val scoreResponce = repository?.getRemoteData()?.last() as Result.Success

        assertThat(response.toDomain()).isEqualTo(scoreResponce.data)
    }


    @Test
    fun emitLoadingState() = coroutineTestRule.runBlockingTest {

        val scoreResponce = repository?.getRemoteData()?.first()

        assertThat(scoreResponce).isInstanceOf(Result.Loading::class.javaObjectType)

    }


    @Test
   fun emitErrorState()= coroutineTestRule.runBlockingTest {

        whenever(service!!.getScore()).thenThrow(RuntimeException("No Internet"))

        val scoreResponse = repository?.getRemoteData()?.last()

        assertThat(Result.Error("Fetching Score Failed : No Internet")).isEqualTo(scoreResponse)

    }
}