package com.mehyo.githubrepostask.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mehyo.githubrepostask.Item

class ItemDataSourceFactory: DataSource.Factory<Int, Item>() {
    val itemLiveDataSource= MutableLiveData<GitDataSource>()
    override fun create(): DataSource<Int, Item> {
        val gitDataSource= GitDataSource()
        itemLiveDataSource.postValue(gitDataSource)
        return gitDataSource
    }
}