package com.sample.randomscanner.domain.model

data class Barcode(
    val name: String,
    val type: String,
    val color: BarcodeColor,
    val date: String
)