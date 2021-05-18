package com.example.phonecontacts.adapter

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phonecontacts.db.UserDatabase
import com.example.phonecontacts.db.UserRepository

class UserViewModelAdapter(context: Context) : ViewModel() {
    private val userRepository: UserRepository =
        UserRepository.getInstance(UserDatabase.getAppDatabase(context).userDao())

    internal fun createUser(email: String, password: String) {
        userRepository.insertUser(email, password)
    }

    internal fun checkValidLogin(email: String, password: String): Boolean {
        return userRepository.isValidAccount(email, password)
    }

    class Factory internal constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context = ctxt.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModelAdapter(ctxt) as T
        }
    }


}