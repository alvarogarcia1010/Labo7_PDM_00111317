package com.example.labo7.Database.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.labo7.Database.Entities.GithubRepo
import com.example.labo7.Database.RoomDB
import com.example.labo7.Database.Repositories.GitHubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubRepoViewModel(app: Application): AndroidViewModel(app) {

    private val repository : GitHubRepoRepository

    init{
        val repoDao = RoomDB.getInstance(app).repoDao()
        repository = GitHubRepoRepository(repoDao)
    }

    fun getAll():LiveData<List<GithubRepo>> = repository.getAll()

    fun nukeAll() = repository.nuke()

    fun insert(repo: GithubRepo) = viewModelScope.launch ( Dispatchers.IO ){ repository.insert(repo) }

}