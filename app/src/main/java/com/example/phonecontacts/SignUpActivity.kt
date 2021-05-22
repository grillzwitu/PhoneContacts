package com.example.phonecontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.lifecycle.ViewModelProvider
//import com.example.phonecontacts.adapter.UserViewModelAdapter
import com.example.phonecontacts.db.UserRepository
import com.example.phonecontacts.db.entities.UserEntity
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

//    private lateinit var userViewModelAdapter: UserViewModelAdapter
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var signUpBtn: Button
    private lateinit var signInBtn: Button
    private var userRepository: UserRepository? = null


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
        userRepository = UserRepository(this)

//        userViewModelAdapter = ViewModelProvider(this, UserViewModelAdapter.Factory(baseContext)).get(UserViewModelAdapter::class.java)


        signUpBtn.setOnClickListener {

            val userAccount = UserEntity(0, email = emailEditText.text.toString().trim(), password = passwordEditText.text.toString().trim())

            if (emailEditText.text.isNullOrBlank() || passwordEditText.text.isNullOrBlank()){
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            else if (!validEmail(emailEditText)){
                Toast.makeText(this, "Account must be an email", Toast.LENGTH_SHORT).show()
            }
            else if (userRepository?.isExisting(userAccount.email) == true) {
                    Toast.makeText(this, "You already signed up. Please sign in", Toast.LENGTH_SHORT).show()
                }
            else{
                userRepository?.insertUsers(userAccount)
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

    private fun validEmail(email: EditText): Boolean{
        val emailInput: String = email.text.toString().trim()
        return emailInput.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
    }

}