package com.example.labo7.Database.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.labo7.Database.DAO.GithubDAO
import com.example.labo7.Database.Entities.GithubRepo

class GitHubRepoRepository(private val repoDao: GithubDAO) {

    fun getAll(): LiveData<List<GithubRepo>> = repoDao.getAllRepos()

    fun nuke() = repoDao.nukeTable()

    @WorkerThread
    suspend fun insert(repo: GithubRepo) = repoDao.insert(repo)

}