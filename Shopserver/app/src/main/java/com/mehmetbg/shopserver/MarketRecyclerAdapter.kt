package com.mehmetbg.shopserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MarketRecyclerAdapter(private val marketNameArray : ArrayList<String>,private val marketDescriptionArray : ArrayList<String>,private val marketImageArray : ArrayList<String>) : RecyclerView.Adapter<MarketRecyclerAdapter.MarketHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.market_recycler_view_unit,parent,false)
        return MarketHolder(view)
    }

    override fun getItemCount(): Int {
        return marketNameArray.size

    }

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {

        holder.marketNameText?.text = marketNameArray[position]
        holder.marketDescriptionText?.text = marketDescriptionArray[position]
        Picasso.get().load(marketImageArray[position]).into(holder.marketImageView)


    }
    class MarketHolder(view: View) : RecyclerView.ViewHolder(view){

        var marketNameText : TextView?=null
        var marketDescriptionText : TextView?=null
        var marketImageView : ImageView?=null

        init{
            marketNameText = view.findViewById(R.id.marketNameText)
            marketDescriptionText = view.findViewById(R.id.marketDescriptionText)
            marketImageView = view.findViewById(R.id.marketImageView)
        }

    }


}