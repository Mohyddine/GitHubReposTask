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
    val time:String=getPast30Days()
    val sort:String="stars"
    val order:String="desc"

companion object{
    const val PAGE_SIZE=20
    const val FIRST_PAGE_Number=1
}
    fun getPast30Days():String{
        var past30days=""
        val calendar= Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR,-30)
        val formatter= SimpleDateFormat("yyyy-MM-dd")
        past30days=formatter.format(calendar.time)
        return "created:>$past30days"
    }
    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(time,sort,order,
                    FIRST_PAGE_Number, PAGE_SIZE)
                if(response.isSuccessful){
                    val apiResponse=response.body()!!
                    val responseItems=apiResponse.items
                    Log.d("DataSource Good",response.toString())

                    responseItems?.let {
                        callback.onResult(responseItems,null, FIRST_PAGE_Number+1)
                    }

                }
                else{
                    Log.d("DataSource Bad",response.toString())
                }
            }catch (e:IOException){
                Log.d("DataSource:loadInitial","IOException "+e.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO){

            try {
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(time,sort,order,
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
                val response =RetrofitInstanceBuilder.apiService.getAPIResult(time,sort,order,
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