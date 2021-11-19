package com.eslam.thedonutproject

import com.eslam.thedonutproject.repositories.ScoreRepositoryShould
import com.eslam.thedonutproject.ui.MainViewModelShould
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite


@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainViewModelShould::class,ScoreRepositoryShould::class)
class UnitTestSuite {

}