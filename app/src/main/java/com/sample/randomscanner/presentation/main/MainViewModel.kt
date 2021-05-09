package com.sample.randomscanner.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.randomscanner.domain.BarcodeProducerUseCase
import com.sample.randomscanner.domain.model.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val barcodeProducerUseCase: BarcodeProducerUseCase) :
    ViewModel() {

    private val _barcode = MutableLiveData<Barcode>()
    val barcode: LiveData<Barcode>
        get() = _barcode

    private val _toolbarConfig = MutableLiveData<ToolbarConfig>()
    val toolbarConfig: LiveData<ToolbarConfig>
        get() = _toolbarConfig

    fun onResume() {
        barcodeProducerUseCase.onResume {
            _barcode.postValue(it)
        }
    }

    fun onPause() {
        barcodeProducerUseCase.onPause()
    }

    fun onBarcodeItemClick(barcode: Barcode) {
        _toolbarConfig.value = ToolbarConfig(
            color = barcode.color.resource,
            title = barcode.name,
            subtitle = barcode.type
        )
    }

    data class ToolbarConfig(val color: Int, val title: String, val subtitle: String)
}