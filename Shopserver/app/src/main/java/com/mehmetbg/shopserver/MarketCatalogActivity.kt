package com.mehmetbg.shopserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_market_catalog.*

class MarketCatalogActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    var storeNameFromFirebase : ArrayList<String> = ArrayList()
    var storeDescriptionFromFirebase : ArrayList<String> = ArrayList()
    var storeImageFromFirebase : ArrayList<String> = ArrayList()

    var adapter : MarketRecyclerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_catalog)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        getMarketDataFromFirestore()

        val layoutManager = LinearLayoutManager(this)
        marketRecyclerView.layoutManager = layoutManager

        adapter = MarketRecyclerAdapter(storeNameFromFirebase,storeDescriptionFromFirebase,storeImageFromFirebase)
        marketRecyclerView.adapter = adapter
    }

    fun getMarketDataFromFirestore(){
        db.collection("Stores").addSnapshotListener {snapshot,exception->
            if(exception!=null){
                Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        val documents = snapshot.documents

                        storeNameFromFirebase.clear()
                        storeDescriptionFromFirebase.clear()
                        storeImageFromFirebase.clear()

                        for(document in documents){
                            val name = document.get("name") as String
                            val description = document.get("description") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            storeNameFromFirebase.add(name)
                            storeDescriptionFromFirebase.add(description)
                            storeImageFromFirebase.add(downloadUrl)

                            adapter!!.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}