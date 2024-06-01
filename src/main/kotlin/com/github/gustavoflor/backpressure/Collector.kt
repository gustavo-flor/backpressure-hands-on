package com.github.gustavoflor.backpressure

import java.util.concurrent.ThreadPoolExecutor

class Collector(
    private val threadPoolExecutor: ThreadPoolExecutor,
    private val backpressure: Backpressure,
    private val executor: Executor
) {
    private var counter = 0;

    fun collect() {
        if (backpressure.shouldWait()) {
            return
        }
        execute(loadWord())
    }

    private fun loadWord(): String {
        // faking load words...
        println("Loading word...")
        counter++
        return "hello-$counter"
    }

    private fun execute(word: String) {
        threadPoolExecutor.execute { executor.execute(word) }
    }
}