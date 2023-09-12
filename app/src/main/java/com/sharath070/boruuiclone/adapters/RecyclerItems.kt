package com.sharath070.boruuiclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.sharath070.boruuiclone.databinding.RecyclerItemBinding
import com.sharath070.boruuiclone.model.ProductList
import com.sharath070.boruuiclone.model.Products
import com.sharath070.boruuiclone.utils.UtilConstants



class RecyclerItems(
    private val context: Context,
    private var productList: MutableList<Products>,
    private val viewModel: UtilConstants
) : RecyclerView.Adapter<RecyclerItems.ViewHolder>() {

    private val viewTypeArray = productList.indices.map { it }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItems.ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItems.ViewHolder, position: Int) {
        val product = productList[position]
        holder.bindItem(product)


        holder.itemBinding.addBtn.setOnClickListener {
            ProductList.ProductList[position].numOfItems++
            holder.itemBinding.quantity.text = "1"
            viewModel.increaseItemCount()
            viewModel.increasePrice(ProductList.ProductList[position].price.substring(2).toDouble())
            holder.itemBinding.addCard.visibility = View.GONE
            holder.itemBinding.quantityBtn.visibility = View.VISIBLE
            notifyDataSetChanged()
        }

        holder.itemBinding.plusBtn.setOnClickListener {
            val numOfItems = ProductList.ProductList[position].numOfItems++
            holder.itemBinding.quantity.text = numOfItems.toString()
            viewModel.increaseItemCount()
            viewModel.increasePrice(ProductList.ProductList[position].price.substring(2).toDouble())
            holder.itemBinding.addCard.visibility = View.GONE
            holder.itemBinding.quantityBtn.visibility = View.VISIBLE
            notifyDataSetChanged()
        }

        holder.itemBinding.minusBtn.setOnClickListener {
            val numOfItems = ProductList.ProductList[position].numOfItems--
            viewModel.decreaseItemCount()
            viewModel.decreasePrice(ProductList.ProductList[position].price.substring(2).toDouble())
            if (numOfItems > 1) {
                holder.itemBinding.quantity.text = numOfItems.toString()
                holder.itemBinding.addCard.visibility = View.GONE
                holder.itemBinding.quantityBtn.visibility = View.VISIBLE

            } else {
                holder.itemBinding.addCard.visibility = View.VISIBLE
                holder.itemBinding.quantityBtn.visibility = View.GONE
            }
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(val itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(products: Products) {
            itemBinding.productName.text = products.productName
            itemBinding.about.text = products.about
            itemBinding.price.text = products.price
            itemBinding.quantity.text = products.numOfItems.toString()
            itemBinding.productImg.setImageResource(products.productImg)
        }
    }

    // For data persistence
    override fun getItemViewType(position: Int): Int {
        // Return a unique view type for each item based on position
        return viewTypeArray[position]
    }

}