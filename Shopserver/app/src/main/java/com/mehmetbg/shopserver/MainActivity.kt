package com.mehmetbg.shopserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if(currentUser!=null){
            val intent = Intent(applicationContext,MainPageActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun loginPageTrigger(view: View){
        val intent = Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun registerPageTrigger(view:View){
        val intent = Intent(applicationContext,RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}