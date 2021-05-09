package com.sample.randomscanner.domain.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sample.randomscanner.domain.RandomBarcodeProducerUseCase
import com.sample.randomscanner.domain.Timer
import com.sample.randomscanner.domain.model.Barcode

import org.junit.jupiter.api.Test

internal class RandomBarcodeProducerUseCaseTest {
    private val repository = mock<BarcodeRepository>()
    private val timer = mock<Timer>()

    private val timerInterval = 5000L

    private val useCase = RandomBarcodeProducerUseCase(repository, timer, timerInterval)

    @Test
    fun `onResume starts the timer`() {
        val action = mock<(Barcode) -> Unit>()

        useCase.onResume(action)

        verify(timer).start(eq(timerInterval), any())
    }

    @Test
    fun `onPause stops the timer`() {
        useCase.onPause()

        verify(timer).cancel()
    }
}