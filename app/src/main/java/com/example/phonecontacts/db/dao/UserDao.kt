package com.example.phonecontacts.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.phonecontacts.db.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun selectAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE user.email LIKE :email")
    fun getUserData(email: String): UserEntity

    @Insert
    fun insertUsers(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)
}