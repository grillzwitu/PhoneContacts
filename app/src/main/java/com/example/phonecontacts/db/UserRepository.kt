package com.example.phonecontacts.db


import android.content.Context
import androidx.lifecycle.LiveData
import com.example.phonecontacts.db.dao.UserDao
import com.example.phonecontacts.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserRepository(context: Context) {
    private val db = UserDatabase.invoke(context)

//    fun getAllNotes() : LiveData<List<UserEntity>> = db.userDao().selectAllUsers()

    fun isValidUser(email: String, password: String): Boolean {
        val validUser: UserEntity? = db.userDao().getUserData(email)

        return if (validUser != null) {
            validUser.password == password
        } else{
            false
        }
    }

    fun isExisting(email: String): Boolean {

        return email == db.userDao().getUserData(email).email
    }

    fun insertUsers(userEntity: UserEntity){
        GlobalScope.launch{
            withContext(Dispatchers.IO){
                db.userDao().insertUsers(userEntity)
            }
        }
    }
}

//class UserRepository(private val userDao: UserDao) {
//
//
//    fun isValidAccount(email: String, password: String): Boolean {
//
//        val userAccount: UserEntity = userDao.getUserData(email)
//
//        return (userAccount.password == password)
//    }
//
//
//    fun insertUser(email: String, password: String) {
//
//        val account = UserEntity(0, email, password)
//        userDao.insertUsers(account)
//    }
//
//    companion object {
//        private var instance: UserRepository? = null
//
//        fun getInstance(userDao: UserDao): UserRepository {
//            if (instance == null) {
//                instance = UserRepository(userDao)
//            }
//            return instance!!
//        }
//    }
//}