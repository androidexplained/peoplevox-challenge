package com.sample.randomscanner.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.randomscanner.databinding.ActivityMainBinding
import com.sample.randomscanner.presentation.adapter.BarcodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    private val barcodeAdapter by lazy { BarcodeAdapter(viewModel::onBarcodeItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        addObservers()
    }

    private fun initRecyclerView() {
        binding.barcodes.run {
            adapter = barcodeAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun addObservers() {
        viewModel.barcode.observe(this, { barcodeAdapter.addBarcode(it) })
        viewModel.toolbarConfig.observe(this, {
            supportActionBar?.run {
                title = it.title
                subtitle = it.subtitle
                setBackgroundDrawable(ColorDrawable(it.color))
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()

        viewModel.onPause()
    }
}