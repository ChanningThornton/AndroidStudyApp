package com.cse5236.headsUpStudy.game

class Game(private val categoryId: String, private val words: List<String>) {
    private var index = -1;
    private val cards = words.map{ Card(it) }

    fun startGame(){
        //TODO
    }

    fun nextCard(isSkipped: Boolean): Card? {
        if(index != -1) cards[index].wasSkipped = isSkipped
        if(index + 1 < cards.size) {
            index++;
            return cards[index]
        }
        return null
    }

 }