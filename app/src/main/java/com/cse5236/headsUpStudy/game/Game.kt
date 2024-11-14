package com.cse5236.headsUpStudy.game

import android.util.Log

class Game(private val categoryId: String, private val words: List<String>, private var streak: Int) {
    private var index = -1;
    private val cards = words.map{ Card(it) }
    private var lastCardTime: Long = 0

    fun startGame(){
        lastCardTime = System.currentTimeMillis()
    }

    fun nextCard(isSkipped: Boolean): Card? {
        if(index != -1) {
            cards[index].wasSkipped = isSkipped
            if(isSkipped){
                streak = 0
            } else {
                streak++
            }
            val currentTime = System.currentTimeMillis()
            cards[index].timeTaken = currentTime - lastCardTime
            lastCardTime = currentTime
        }
        if(index + 1 < cards.size) {
            index++;
            return cards[index]
        }
        return null
    }

    fun getStreak(): Int{
        return streak
    }

    fun getWordsStatus(): ArrayList<Pair<String, Boolean>> {
        val wordsStatus = ArrayList<Pair<String, Boolean>>()
        for (card in cards) {
            wordsStatus.add(Pair(card.word, card.wasSkipped))
        }
        return wordsStatus
    }
    fun test(){
        for(card in cards){
            Log.d(card.word, "${card.timeTaken}")
        }
    }

 }