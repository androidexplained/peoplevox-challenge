package com.sample.randomscanner.domain

import com.sample.randomscanner.domain.model.Barcode

interface BarcodeRepository {
    fun get(): Barcode
}
