package com.example.nuevaera.ui

import android.annotation.SuppressLint
import android.content.ClipData
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nuevaera.databinding.ProductItemBinding
import com.example.nuevaera.pojo.ProductoResponseItem

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    private var listProductItem = listOf<ProductoResponseItem>()
    private var selectedItem = MutableLiveData<ProductoResponseItem>()
    fun selectedItem() = selectedItem

    fun update(list: List<ProductoResponseItem>) {

        listProductItem = list
        notifyDataSetChanged()
    }

    inner class ProductVH(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        @SuppressLint("SetTextI18n")
        fun bind(productoResponseItem: ProductoResponseItem) {
            with(binding) {

                nameEd.text = productoResponseItem.name
                priceEd.text = "Precio : $ ${productoResponseItem.price}"
                IDed.text = productoResponseItem.id.toString()

                Glide.with(imageView)
                    .load(productoResponseItem.image)
                    .fitCenter()
                    .centerCrop()
                    .into(imageView)

            }


            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            selectedItem.value = listProductItem[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(ProductItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val productItem = listProductItem[position]
        holder.bind(productItem)
    }

    override fun getItemCount(): Int = listProductItem.size
}


