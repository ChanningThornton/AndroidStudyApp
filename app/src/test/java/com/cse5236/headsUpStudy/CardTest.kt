package com.cse5236.headsUpStudy

import com.cse5236.headsUpStudy.game.Card
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CardTest {

    @Test
    fun testCardInitialization() {
        val card = Card("python")
        assertEquals("python", card.word)
        assertEquals(0, card.timeTaken)
    }

    @Test
    fun testCardSkipped() {
        val card = Card("java")
        card.wasSkipped = true
        assertTrue(card.wasSkipped)
    }

    @Test
    fun testCardTimeTaken() {
        val card = Card("ruby")
        card.timeTaken = 5000
        assertEquals(5000, card.timeTaken)
    }
}