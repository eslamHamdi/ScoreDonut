package com.eslam.thedonutproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eslam.thedonutproject.data.remote.Result
import com.eslam.thedonutproject.domain.DataSource
import com.eslam.thedonutproject.domain.model.ScoreModel
import com.eslam.thedonutproject.repositories.ScoreRepository
import com.eslam.thedonutproject.ui.viewmodels.MainViewModel
import com.eslam.thedonutproject.utils.MainCoroutineScopeRule
import com.eslam.thedonutproject.utils.getValueForTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelShould {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    private var viewModel:MainViewModel? = null
    private var repository:ScoreRepository? = null
    private val scoreModel:ScoreModel = mock()


    @Before
    fun initializeViewModel()
    {
        repository = mock(ScoreRepository::class.java)
        viewModel = MainViewModel(repository!!as DataSource)


    }
    @After
    fun tearDown()
    {
        viewModel = null
        repository = null


    }


   @Test
    fun invoke_getRemoteData() = coroutineTestRule.runBlockingTest {

       // viewModel?.getTheScore()   runs at init Block

        verify(repository, times(1))?.getRemoteData()



    }

    @Test
    fun getTheCorrectScoreFromRepositoryOnSuccess()= coroutineTestRule.runBlockingTest {

        whenever(repository?.getRemoteData()).thenReturn(flowOf(Result.Success(scoreModel)))

       viewModel?.getTheScore()

        assertThat(viewModel?.scoreLiveData?.getValueForTest())?.isEqualTo(scoreModel)

    }

    @Test
    fun getErrorMessageFromRepositoryInCaseOfFailure() =coroutineTestRule.runBlockingTest {

        whenever(repository?.getRemoteData()).thenReturn(flowOf(Result.Error("Failed!!")))
        viewModel?.getTheScore()
        assertThat(viewModel?.errorevent?.getValueForTest())?.isEqualTo("Failed!!")

    }

    @Test
    fun hideLoaderOnSuccess()=coroutineTestRule.runBlockingTest {
        whenever(repository?.getRemoteData()).thenReturn(flowOf(Result.Success(scoreModel)))
        viewModel?.getTheScore()
        assertThat(viewModel?.loadingState?.getValueForTest())?.isEqualTo(false)

    }

    @Test
    fun hideLoaderOnFailure()=coroutineTestRule.runBlockingTest {
        whenever(repository?.getRemoteData()).thenReturn(flowOf(Result.Error("Failed!!")))
        viewModel?.getTheScore()
        assertThat(viewModel?.loadingState?.getValueForTest())?.isEqualTo(false)

    }

    @Test
    fun showLoaderDuringFetchingScore()=coroutineTestRule.runBlockingTest {
        whenever(repository?.getRemoteData()).thenReturn(flowOf(Result.Loading()))
       viewModel?.getTheScore()
        assertThat(viewModel?.loadingState?.getValueForTest())?.isEqualTo(true)

    }


}