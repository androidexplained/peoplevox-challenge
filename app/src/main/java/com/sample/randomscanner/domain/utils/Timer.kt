package com.sample.randomscanner.domain

import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

class FixedRateTimer @Inject constructor() : Timer {

    private lateinit var timer: java.util.Timer

    override fun start(interval: Long, action: () -> Unit) {
        timer = fixedRateTimer("timer", false, 0, interval) { action() }
    }

    override fun cancel() {
        timer.cancel()
    }
}

interface Timer {
    fun start(interval: Long, action: () -> Unit)
    fun cancel()
}

