package com.mehmetbg.shopserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductRecyclerAdapter(private val productNameArray: ArrayList<String>,private val productPriceArray: ArrayList<String>,private val productStoreArray: ArrayList<String>,private val productImageArray: ArrayList<String>) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_recycler_view_unit,parent,false)
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productNameArray.size

    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.productNameText?.text = productNameArray[position]
        holder.productPriceText?.text = productPriceArray[position]
        holder.productStoreText?.text = productStoreArray[position]
        Picasso.get().load(productImageArray[position]).into(holder.productImageView)
    }

    class ProductHolder(view:View) : RecyclerView.ViewHolder(view){

        var productNameText : TextView? = null
        var productPriceText : TextView? = null
        var productStoreText : TextView? = null
        var productImageView : ImageView? = null

        init{
            productNameText = view.findViewById(R.id.productNameText)
            productPriceText = view.findViewById(R.id.productPriceText)
            productStoreText = view.findViewById(R.id.productStoreText)
            productImageView = view.findViewById(R.id.productImageView)
        }


    }
}