package com.mehyo.githubrepostask.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mehyo.githubrepostask.Item

//DataSourceFactory class
class ItemDataSourceFactory: DataSource.Factory<Int, Item>() {
    //DataSource MutableLiveData variable
    val itemLiveDataSource= MutableLiveData<GitDataSource>()
    //DataSourceFactory create function
    override fun create(): DataSource<Int, Item> {
        //DataSource variable
        val gitDataSource= GitDataSource()
        itemLiveDataSource.postValue(gitDataSource)
        return gitDataSource
    }
}