package com.example.labo7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labo7.Adapters.ReposAdapter
import com.example.labo7.Database.Entities.GithubRepo
import com.example.labo7.Database.ViewModels.GitHubRepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ReposAdapter
    lateinit var viewModel: GitHubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()
    }

    private fun bind(){
        adapter = ReposAdapter(ArrayList())
        viewModel = ViewModelProviders.of(this).get(GitHubRepoViewModel::class.java)

        recyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        btn_add.setOnClickListener{
            val name = et_repo_name.text.toString()

            if (name.isNotEmpty()&&name.isNotBlank()){
                viewModel.insert(GithubRepo(name))
            }
        }


        viewModel.getAll().observe(this, Observer {repos ->
            adapter.updateList(repos)
            Log.d("Lista de Repos","_____________________________________")
            for (repo in repos){
                Log.d("Lista de Repos", repo.name)
            }
        })
    }
}
