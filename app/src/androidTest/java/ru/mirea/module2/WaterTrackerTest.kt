package ru.mirea.module2

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WaterTrackerTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    fun openTracker() {
        compose.activity.getSharedPreferences("water_tracker", Context.MODE_PRIVATE)
            .edit().clear().commit()
        compose.onNodeWithText("8").performClick()
    }

    @Test
    fun addButtonAdds250MlEachTime() {
        val add = compose.onNodeWithText("+250 мл").assertIsDisplayed()
        compose.onNodeWithText("100 мл").assertIsDisplayed()
        add.performClick()
        compose.onNodeWithText("350 мл").assertIsDisplayed()
        add.performClick()
        compose.onNodeWithText("600 мл").assertIsDisplayed()
    }

    @Test
    fun finishDayButtonClearsTheDailyAmount() {
        compose.onNodeWithText("+250 мл").performClick()
        compose.onNodeWithText("Завершить день").assertIsDisplayed().performClick()
        compose.onNodeWithText("0 мл").assertIsDisplayed()
        checkStreak(0)
    }

    @Test
    fun streakCountsSuccessfulDaysAndResetsWhenGoalIsMissed() {
        finishDay()
        repeat(6) { addWater() }
        compose.onNodeWithText("1500 мл").assertIsDisplayed()
        finishDay()
        checkStreak(1)

        repeat(7) { addWater() }
        finishDay()
        checkStreak(2)

        repeat(5) { addWater() }
        compose.onNodeWithText("1250 мл").assertIsDisplayed()
        finishDay()
        checkStreak(0)
        compose.onNodeWithText("0 мл").assertIsDisplayed()
    }

    @Test
    fun progressAndStreakSurviveRecreatingActivity() {
        finishDay()
        repeat(6) { addWater() }
        finishDay()
        addWater()

        compose.activityRule.scenario.recreate()

        compose.onNodeWithText("250 мл").assertIsDisplayed()
        checkStreak(1)
    }

    private fun addWater() {
        compose.onNodeWithText("+250 мл").performClick()
    }

    private fun finishDay() {
        compose.onNodeWithText("Завершить день").performClick()
    }

    private fun checkStreak(days: Int) {
        compose.onNodeWithText("Дней подряд с 1500 мл и больше: $days").assertIsDisplayed()
    }
}
