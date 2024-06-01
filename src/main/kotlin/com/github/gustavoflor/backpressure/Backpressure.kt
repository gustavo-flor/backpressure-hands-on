package com.github.gustavoflor.backpressure

interface Backpressure {
    fun shouldWait(): Boolean
}