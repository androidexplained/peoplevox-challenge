package com.sample.randomscanner.data

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

class RandomBarcodeRepositoryTest {
    private val barcodes = listOf("PK01", "BARCODE_A", "4354834", "PO14785-20171215")
    private val types = listOf("EAN8", "UPCE", "EAN13", "I25", "QRCODE", "CODE128")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")

    private val repository = RandomBarcodeRepository(barcodes, types, colors)

    @Test
    fun `barcode is produced based on injected data`() {
        repository.get().run {
            Truth.assertThat(barcodes.contains(name)).isTrue()
            Truth.assertThat(types.contains(type)).isTrue()
            Truth.assertThat(colors.contains(color.name)).isTrue()
        }
    }
}