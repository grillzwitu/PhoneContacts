package com.example.phonecontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
//import androidx.lifecycle.ViewModelProvider
//import com.example.phonecontacts.adapter.UserViewModelAdapter
import com.example.phonecontacts.db.UserRepository
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

//    private lateinit var userViewModelAdapter: UserViewModelAdapter

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginBtn: Button
    private var userRepository: UserRepository? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login()
    }

    private fun login() {

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        title = "Login"

        emailEditText = findViewById(R.id.loginEmailEditText)
        passwordEditText = findViewById(R.id.loginPasswordEditText)
        loginBtn = findViewById(R.id.loginButton)
        userRepository = UserRepository(this)

//        userViewModelAdapter = ViewModelProvider(this, UserViewModelAdapter.Factory(baseContext)).get(UserViewModelAdapter::class.java)

        loginBtn.setOnClickListener {
            if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            else{
                val isValid: Boolean = userRepository?.isValidUser(emailEditText.text.toString(), passwordEditText.text.toString()) == true
                if (isValid) {
                    Toast.makeText(baseContext, "Welcome to Contacts", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Invalid Login! Please create an account", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}