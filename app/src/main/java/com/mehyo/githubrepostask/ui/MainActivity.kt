package com.mehyo.githubrepostask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mehyo.githubrepostask.R
import com.mehyo.githubrepostask.databinding.ActivityMainBinding
import com.mehyo.githubrepostask.repo.GitViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter:GitAdapter
    lateinit var gitViewModel:GitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView(binding.recyclerView)
        getData()
    }
    //RecyclerView initialization with dividerItemDecoration and setting the Adapter
    fun initRecyclerView(recyclerView: RecyclerView){
        recyclerView.apply {
            layoutManager=LinearLayoutManager(applicationContext)
            mAdapter=GitAdapter()
            adapter=mAdapter
            val decoration = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            decoration.setDrawable(this.resources.getDrawable(R.drawable.shape,null))
            addItemDecoration(decoration)
        }
    }
    //get the data using the viewModel then submit inside the adapter
    fun getData(){
        gitViewModel=ViewModelProvider(this).get(GitViewModel::class.java)
        gitViewModel.gitPagedList.observe(this, Observer {
            mAdapter.submitList(it)
            mAdapter.notifyDataSetChanged()
        })
    }

}