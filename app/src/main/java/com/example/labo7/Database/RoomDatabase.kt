package com.example.labo7.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labo7.Database.DAO.GithubDAO
import com.example.labo7.Database.Entities.GithubRepo

@Database(entities = [GithubRepo::class], version = 1, exportSchema = false)
public abstract class RoomDB: RoomDatabase(){

    abstract fun repoDao(): GithubDAO

    companion object {
        @Volatile
        private var INSTANCE:RoomDB? = null

        fun getInstance(Appcontext: Context): RoomDB{
            val tempInstance = INSTANCE

            if (tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room
                    .databaseBuilder(Appcontext,RoomDB::class.java,"Repo_DB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}