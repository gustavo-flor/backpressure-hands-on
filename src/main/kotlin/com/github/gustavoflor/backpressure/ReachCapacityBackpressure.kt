package com.github.gustavoflor.backpressure

import java.util.concurrent.ThreadPoolExecutor

class ReachCapacityBackpressure(
    private val threadPoolExecutor: ThreadPoolExecutor
) : Backpressure {
    override fun shouldWait(): Boolean {
        val remainingCapacity = threadPoolExecutor.queue.remainingCapacity()
        println("Reaching capacity: $remainingCapacity")
        val shouldWait = remainingCapacity == 0
        if (shouldWait) {
            println("Waiting for the next process...")
        }
        return shouldWait
    }
}