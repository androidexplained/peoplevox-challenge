package com.sample.randomscanner.domain

import com.sample.randomscanner.domain.di.DomainModule
import com.sample.randomscanner.domain.model.Barcode
import javax.inject.Inject
import javax.inject.Named

class RandomBarcodeProducerUseCase @Inject constructor(
    private val repository: BarcodeRepository,
    private val timer: Timer,
    @Named(DomainModule.timerInterval)
    private val interval: Long
) : BarcodeProducerUseCase {

    override fun onResume(onProduce: (Barcode) -> Unit) {
        timer.start(interval) { onProduce(repository.get()) }
    }

    override fun onPause() {
        timer.cancel()
    }
}

interface BarcodeProducerUseCase {
    fun onResume(onProduce: (Barcode) -> Unit)
    fun onPause()
}
