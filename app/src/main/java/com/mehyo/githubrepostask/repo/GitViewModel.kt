package com.mehyo.githubrepostask.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mehyo.githubrepostask.Item

class GitViewModel: ViewModel() {
    //PagedList liveData variable
    val gitPagedList:LiveData<PagedList<Item>>
    //DataSource liveData variable
    private val liveDataSource:LiveData<GitDataSource>

    init {
        //DataSource Factory variable
       val mItemDataSourceFactory=ItemDataSourceFactory()
        //initialize the dataSource liveData using DataSourceFactory
        liveDataSource=mItemDataSourceFactory.itemLiveDataSource
        //PagedList Config variable
        val config=
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitDataSource.PAGE_SIZE)
                .build()
        //initialize the PagedList using the builder
        gitPagedList=LivePagedListBuilder(mItemDataSourceFactory,config).build()

    }
}