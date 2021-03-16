package com.mehyo.githubrepostask

//single owner data class
data class Owner(val login:String,val avatar_url:String)
//single item data class
data class Item( val id:Int, val name:String,val owner:Owner,val description:String,
                 val language:String,val stargazers_count: Int,val open_issues_count:Int)
//Response model data class
data class GitApiResponse( val items:ArrayList<Item>)