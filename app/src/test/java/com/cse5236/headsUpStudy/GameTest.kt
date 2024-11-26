package com.cse5236.headsUpStudy

import com.cse5236.headsUpStudy.game.Game
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test

class GameTest {

    @Test
    fun testGameInit(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        assertNotNull(game)
        assertEquals(3,game.getNumCards())
    }

    @Test
    fun testNextCardReturn(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        val card = game.nextCard(false)
        assertNotNull(card)
        assertEquals("python", card?.word)
    }

    @Test
    fun testStreakIncrement(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        game.nextCard(false)
        game.nextCard(false)
        game.nextCard(false)
        assertEquals(2, game.getStreak())
    }

    @Test
    fun testStreakReset(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        game.nextCard(false)
        game.nextCard(false)
        game.nextCard(true)
        assertEquals(0, game.getStreak())
    }

    @Test
    fun testNextCardNull(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        game.nextCard(false)
        game.nextCard(false)
        game.nextCard(true)
        val nextCard = game.nextCard(false)
        assertNull(nextCard)
    }

    @Test
    fun testWordStatus(){
        val game = Game("category", listOf("python", "java", "ruby"), 0)
        game.nextCard(false)
        game.nextCard(false)
        game.nextCard(false)
        game.nextCard(true)
        val wordStatus = game.getWordsStatus()
        assertEquals(listOf(Pair("python", false), Pair("java", false),Pair("ruby", true)), wordStatus)
    }
}