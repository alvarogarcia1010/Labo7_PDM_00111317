package com.example.labo7.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.labo7.Database.Entities.GithubRepo

@Dao
interface GithubDAO {

    @Query("SELECT * FROM repos")
    fun getAllRepos():LiveData<List<GithubRepo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: GithubRepo)

    @Query("DELETE FROM repos")
    fun nukeTable()

}