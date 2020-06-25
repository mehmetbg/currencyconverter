package com.mehmetbg.shopserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class MainPageActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        auth = FirebaseAuth.getInstance()
    }

    fun marketCatalogTrigger(view:View) {
        val intent = Intent(applicationContext,MarketCatalogActivity::class.java)
        startActivity(intent)

    }

    fun marketLocationTrigger(view:View){
        val intent = Intent(applicationContext,MarketLocationActivity::class.java)
        startActivity(intent)

    }

    fun productCatalogTrigger(view:View){
        val intent = Intent(applicationContext,ProductCatalogActivity::class.java)
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            auth.signOut()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}