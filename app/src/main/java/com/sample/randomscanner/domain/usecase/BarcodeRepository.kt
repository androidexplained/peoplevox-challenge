package com.sample.randomscanner.domain.usecase

import com.sample.randomscanner.domain.model.Barcode

interface BarcodeRepository {
    fun get(): Barcode
}
