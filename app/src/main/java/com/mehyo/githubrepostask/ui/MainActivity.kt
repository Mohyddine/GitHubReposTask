package com.mehyo.githubrepostask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehyo.githubrepostask.R
import com.mehyo.githubrepostask.databinding.ActivityMainBinding
import com.mehyo.githubrepostask.repo.GitViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mAdapter=GitAdapter()
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        val gitViewHolder=ViewModelProvider(this).get(GitViewModel::class.java)
        gitViewHolder.gitPagedList.observe(this, Observer {
            Log.d("main", it.size.toString())
            mAdapter.submitList(it)
            mAdapter.notifyDataSetChanged()
        })
        binding.recyclerView.adapter=mAdapter
    }
}