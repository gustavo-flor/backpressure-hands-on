package com.github.gustavoflor.backpressure

import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

fun threadPoolExecutor(
    corePoolSize: Int = 2,
    maximumPoolSize: Int = 2,
    keepAliveTime: Long = 0L,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    workQueue: BlockingQueue<Runnable> = LinkedBlockingQueue(3)
) = ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue)

fun main() {
    val threadPool = threadPoolExecutor()
    val backpressure = ReachCapacityBackpressure(threadPool)
    val executor = Executor()
    val collector = Collector(threadPool, backpressure, executor)

    Executors.newScheduledThreadPool(1)
        .scheduleWithFixedDelay({
           collector.collect()
        }, 0, 1, TimeUnit.SECONDS)
}
