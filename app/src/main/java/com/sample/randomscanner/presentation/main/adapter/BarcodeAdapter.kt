package com.sample.randomscanner.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.randomscanner.databinding.BarcodeItemBinding
import com.sample.randomscanner.domain.model.Barcode

class BarcodeAdapter(private val onItemClick: (Barcode) -> Unit) :
    RecyclerView.Adapter<BarcodeAdapter.ViewHolder>() {

    private var barcodes = mutableListOf<Barcode>()

    fun addBarcode(barcode: Barcode) {
        barcodes.add(barcode)
        barcodes = barcodes.sortedBy { it.name }.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BarcodeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding) { onItemClick(barcodes[it]) }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.onBind(barcodes[position])
    }

    override fun getItemCount() = barcodes.size

    class ViewHolder(private val binding: BarcodeItemBinding, onItemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onItemClick(adapterPosition) }
        }

        fun onBind(barcode: Barcode) {
            binding.run {
                name.text = barcode.name
                name.setTextColor(barcode.color.resource)
                type.text = barcode.type
                date.text = barcode.date
            }
        }
    }
}