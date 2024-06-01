package com.github.gustavoflor.backpressure

class Executor {
    fun execute(word: String) {
        println("Executing word: $word")
        Thread.sleep(8000) // faking a slow process
        println("Executed word: $word")
    }
}