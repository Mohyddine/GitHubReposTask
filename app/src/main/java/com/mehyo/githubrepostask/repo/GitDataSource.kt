package com.mehyo.githubrepostask.repo

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.mehyo.githubrepostask.Item
import com.mehyo.githubrepostask.network.RetrofitInstanceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okio.IOException
import java.text.SimpleDateFormat
import java.util.*

class GitDataSource:PageKeyedDataSource<Int,Item>() {
    val past30days:String

    init {
        val calendar= Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR,-30)
        val formatter= SimpleDateFormat("yyyy-MM-dd")
        past30days=formatter.format(calendar.time)
    }

    val q="created:%3E$past30days"

companion object{
    const val PAGE_SIZE=30
    const val FIRST_PAGE_Number=1
    const val SORT_WITH="stars"
    const val ORDER="desc"
}

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(q, SORT_WITH, ORDER,
                    FIRST_PAGE_Number, PAGE_SIZE)
                if(response.isSuccessful){
                    val apiResponse=response.body()!!
                    val responseItems=apiResponse.items

                    responseItems?.let {
                        callback.onResult(responseItems,null, FIRST_PAGE_Number+1)
                    }

                }
            }catch (e:IOException){
                Log.d("DataSource:loadInitial","IOException "+e.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(q, SORT_WITH, ORDER,
                    params.key, PAGE_SIZE)
                if(response.isSuccessful){
                    val apiResponse=response.body()!!
                    val responseItems=apiResponse.items
                    val key= if(params.key>1)params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }

                }
            }catch (e:IOException){
                Log.d("DataSource:loadBefore","IOException "+e.message)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(q, SORT_WITH, ORDER,
                    params.key, PAGE_SIZE)
                if(response.isSuccessful){
                    val apiResponse=response.body()!!
                    val responseItems=apiResponse.items
                    val key=params.key + 1

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }

                }
            }catch (e:IOException){
                Log.d("DataSource:loadAfter","IOException "+e.message)
            }
        }
    }
}