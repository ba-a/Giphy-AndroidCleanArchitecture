package de.abauer.giphy_androidcleanarchitecture.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.uniflow.test.rule.TestDispatchersRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testDispatchersRule = TestDispatchersRule()
}