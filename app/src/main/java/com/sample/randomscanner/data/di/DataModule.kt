package com.sample.randomscanner.data.di

import com.sample.randomscanner.data.RandomBarcodeRepository
import com.sample.randomscanner.domain.BarcodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Named(barcodes)
    @Provides
    fun provideBarcodes() = listOf("PK01", "BARCODE_A", "4354834", "PO14785-20171215")

    @Named(types)
    @Provides
    fun provideTypes() = listOf(
        "EAN8",
        "UPCE",
        "EAN13",
        "I25",
        "QRCODE",
        "CODE128"
    )

    @Named(colors)
    @Provides
    fun provideColors() = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")

    @Provides
    fun provideRepository(
        @Named(Companion.barcodes) barcodes: List<String>,
        @Named(Companion.types) types: List<String>,
        @Named(Companion.colors) colors: List<String>
    ): BarcodeRepository = RandomBarcodeRepository(barcodes, types, colors)

    private companion object {
        const val barcodes = "barcodes"
        const val types = "types"
        const val colors = "colors"
    }
}
