package com.example.phonecontacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.phonecontacts.adapter.UserViewModelAdapter
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

    private lateinit var userViewModelAdapter: UserViewModelAdapter
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var signUpBtn: Button
    private lateinit var signInBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        init()
    }

    private  fun init(){

        title = "Contacts"

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpBtn = findViewById(R.id.signUpButton)
        signInBtn = findViewById(R.id.signInButton)

        userViewModelAdapter = ViewModelProvider(this, UserViewModelAdapter.Factory(baseContext)).get(UserViewModelAdapter::class.java)


        signUpBtn.setOnClickListener {

            userViewModelAdapter!!.createUser(emailEditText!!.text.toString(), passwordEditText!!.text.toString())
//            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
//                    .putBoolean("signup", false).apply()
            if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Successfully Created An Account, Now sign In!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            }

        }

        signInBtn.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)

        }

    }

}