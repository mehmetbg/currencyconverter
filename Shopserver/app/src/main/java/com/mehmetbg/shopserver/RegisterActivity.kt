package com.mehmetbg.shopserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
    }

    fun userRegister(view: View){
        val email = userEmailText.text.toString()
        val password = userPasswordText.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                val user = auth.currentUser
                val profileUpdates = userProfileChangeRequest {
                    displayName = userNameText.text.toString() + " " + userSurnameText.text.toString()
                }
                user!!.updateProfile(profileUpdates).addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext,"Profil oluşturuldu",Toast.LENGTH_LONG).show()
                    }
                }
                val intent = Intent(applicationContext,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.addOnFailureListener { exception ->
            if(exception!=null)
            Toast.makeText(applicationContext,exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
        }

    }
}