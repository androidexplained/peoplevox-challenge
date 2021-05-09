package com.sample.randomscanner.presentation.main

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sample.randomscanner.utils.InstantExecutorExtension
import com.sample.randomscanner.domain.BarcodeProducerUseCase
import com.sample.randomscanner.domain.model.Barcode
import com.sample.randomscanner.domain.model.BarcodeColor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class MainViewModelTest {
    private val useCase = mock<BarcodeProducerUseCase>()

    private val viewModel = MainViewModel(useCase)

    @Test
    fun `onResume delegates call to the useCase`() {
        viewModel.onResume()

        verify(useCase).onResume(any())
    }

    @Test
    fun `onPause delegates call to the useCase`() {
        viewModel.onPause()

        verify(useCase).onPause()
    }

    @Test
    fun `onBarcodeItemClick exposes toolbarConfig`() {
        val barcode =
            Barcode(name = "barcode", type = "type", color = BarcodeColor.Green, date = "date")

        viewModel.onBarcodeItemClick(barcode)

        viewModel.toolbarConfig.value?.run {
            Truth.assertThat(title).isEqualTo(barcode.name)
            Truth.assertThat(subtitle).isEqualTo(barcode.type)
            Truth.assertThat(color).isEqualTo(barcode.color.resource)
        }
    }
}