package com.szn.kaam

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.szn.kaam.ui.navigation.CITATIONS
import com.szn.kaam.ui.navigation.HOME
import com.szn.kaam.ui.navigation.LOADING
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class KaamTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appLaunches() {
        composeTestRule.onNodeWithTag(HOME).assertIsDisplayed()
    }

    @Test
    fun appLaunchesAndDisplayList() {
        composeTestRule.onNodeWithTag(HOME).assertIsDisplayed()
        runBlocking {
            composeTestRule.onNodeWithTag(LOADING).assertIsDisplayed()
            // Wait a bit to get sure have list
            delay(1000)
            composeTestRule.onNodeWithTag(CITATIONS).assertIsDisplayed()
        }
    }

}