package com.eslam.thedonutproject.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eslam.thedonutproject.utils.MainCoroutineScopeRule
import org.junit.Rule

class MainViewModelShould {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

}