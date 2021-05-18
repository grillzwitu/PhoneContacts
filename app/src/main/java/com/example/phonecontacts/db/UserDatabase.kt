package com.example.phonecontacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phonecontacts.db.dao.UserDao
import com.example.phonecontacts.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)

abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getAppDatabase(context: Context): UserDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user-database").allowMainThreadQueries().build()

            }

            return INSTANCE!!

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}