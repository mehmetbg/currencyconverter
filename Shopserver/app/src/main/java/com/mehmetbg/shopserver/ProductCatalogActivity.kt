package com.mehmetbg.shopserver

import android.drm.DrmStore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_product_catalog.*
import kotlin.String as String

class ProductCatalogActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    var productNameFromFirebase : ArrayList<String> = ArrayList()
    var productStoreFromFirebase : ArrayList<String> = ArrayList()
    var productPriceFromFirebase : ArrayList<String> = ArrayList()
    var productImageFromFirebase : ArrayList<String> = ArrayList()

    var adapter : ProductRecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_catalog)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        getProductDataFromFirestore()


        val layoutManager = LinearLayoutManager(this)
        productRecyclerView.layoutManager = layoutManager

        adapter = ProductRecyclerAdapter(productNameFromFirebase,productPriceFromFirebase,productStoreFromFirebase,productImageFromFirebase)
        productRecyclerView.adapter = adapter




    }

    fun getProductDataFromFirestore(){

        db.collection("Products").addSnapshotListener { snapshot, exception ->
            if(exception!=null){
                Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){

                        productPriceFromFirebase.clear()
                        productStoreFromFirebase.clear()
                        productNameFromFirebase.clear()
                        productImageFromFirebase.clear()

                        val documents = snapshot.documents
                        for (document in documents){
                            val name = document.get("name") as String
                            val price = document.get("price") as String
                            val store = document.get("store") as String
                            val downloadUrl = document.get("downloadUrl") as String


                            productImageFromFirebase.add(downloadUrl)
                            productNameFromFirebase.add(name)
                            productPriceFromFirebase.add("Fiyat : ${price}")
                            productStoreFromFirebase.add("Market : ${store}")

                            adapter!!.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }
}