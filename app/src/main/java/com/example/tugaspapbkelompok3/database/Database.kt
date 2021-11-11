package com.example.tugaspapbkelompok3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun ContactDAO(): ContactDAO

    companion object{
        @Volatile
        private var INSTANCE: DB? = null

        fun getDB(context: Context): DB{

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "app_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance

                return instance
        }
    }
}
