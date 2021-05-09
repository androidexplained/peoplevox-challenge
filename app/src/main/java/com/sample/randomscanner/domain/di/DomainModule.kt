package com.sample.randomscanner.domain.di

import com.sample.randomscanner.domain.BarcodeProducerUseCase
import com.sample.randomscanner.domain.FixedRateTimer
import com.sample.randomscanner.domain.RandomBarcodeProducerUseCase
import com.sample.randomscanner.domain.Timer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {
    @Binds
    abstract fun bindsProducerUseCase(barcodeProducer: RandomBarcodeProducerUseCase): BarcodeProducerUseCase

    @Binds
    abstract fun bindsTimer(barcodeProducer: FixedRateTimer): Timer

    companion object {
        const val timerInterval = "timerInterval"

        @Named(timerInterval)
        @Provides
        fun timerInterval() = 5000L
    }
}