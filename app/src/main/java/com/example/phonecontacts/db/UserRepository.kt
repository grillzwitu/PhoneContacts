package com.example.phonecontacts.db


import androidx.lifecycle.LiveData
import com.example.phonecontacts.db.dao.UserDao
import com.example.phonecontacts.db.entities.UserEntity


class UserRepository(private val userDao: UserDao) {

    private val userAccountLiveData: LiveData<UserEntity>? = null

    fun isValidAccount(email: String, password: String): Boolean {

        val userAccount = userDao.getUserData(email)
        return userAccount.password == password
    }

    fun insertUser(email: String, password: String) {
        val account = UserEntity(0, email, password)
        userDao.insertUsers(account)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}