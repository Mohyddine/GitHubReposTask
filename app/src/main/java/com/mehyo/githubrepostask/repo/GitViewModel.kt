package com.mehyo.githubrepostask.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mehyo.githubrepostask.Item

class GitViewModel: ViewModel() {

    val gitPagedList:LiveData<PagedList<Item>>

    private val liveDataSource:LiveData<GitDataSource>

    init {
       val mItemDataSourceFactory=ItemDataSourceFactory()

        liveDataSource=mItemDataSourceFactory.itemLiveDataSource
        val config=
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(GitDataSource.PAGE_SIZE)
                .build()

        gitPagedList=LivePagedListBuilder(mItemDataSourceFactory,config).build()

    }
}