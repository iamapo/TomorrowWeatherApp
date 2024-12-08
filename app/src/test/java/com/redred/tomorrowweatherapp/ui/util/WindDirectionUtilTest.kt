package com.redred.tomorrowweatherapp.ui.util

import com.redred.tomorrowweatherapp.R
import org.junit.Assert.assertEquals
import org.junit.Test


class WindDirectionUtilsTest {

    @Test
    fun `getWindDirectionResId returns north for 0 degrees`() {
        val resId = WindDirectionUtils.getWindDirectionResId(0.0)
        assertEquals(R.string.north, resId)
    }

    @Test
    fun `getWindDirectionResId returns northeast for 45 degrees`() {
        val resId = WindDirectionUtils.getWindDirectionResId(45.0)
        assertEquals(R.string.northeast, resId)
    }

    @Test
    fun `getWindDirectionResId returns south for 180 degrees`() {
        val resId = WindDirectionUtils.getWindDirectionResId(180.0)
        assertEquals(R.string.south, resId)
    }

    @Test
    fun `getWindDirectionResId returns invalid_direction for negative degrees`() {
        val resId = WindDirectionUtils.getWindDirectionResId(-10.0)
        assertEquals(R.string.invalid_direction, resId)
    }

    @Test
    fun `getWindDirectionResId returns invalid_direction for degrees = 360`() {
        val resId = WindDirectionUtils.getWindDirectionResId(360.0)
        assertEquals(R.string.invalid_direction, resId)
    }
}