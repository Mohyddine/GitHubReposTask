package com.mehyo.githubrepostask


data class Owner(val login:String,val avatar_url:String)
data class Item( val name:String,val owner:Owner,val description:String,
                 val language:String,val stargazers_count: Int,val open_issues_count:Int)
data class GitApiResponse( val items:ArrayList<Item>)