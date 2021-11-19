package com.eslam.thedonutproject

import com.eslam.thedonutproject.repositories.ScoreRepositoryShould
import com.eslam.thedonutproject.ui.MainViewModelShould
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainViewModelShould::class,ScoreRepositoryShould::class)
class UnitTestSuite {

}