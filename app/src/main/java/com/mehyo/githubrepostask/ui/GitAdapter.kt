package com.mehyo.githubrepostask.ui

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.mehyo.githubrepostask.Item
import com.mehyo.githubrepostask.R
import com.mehyo.githubrepostask.databinding.ItemRowBinding
import java.text.SimpleDateFormat
import java.util.*


class GitAdapter:PagedListAdapter<Item, GitAdapter.GitViewHolder>(ITEM_COMPARATOR) {

    //CreateViewHolder using view binding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding=ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitViewHolder(binding)
    }

    //onBindViewHolder binding the data from the data to the row
    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        val item=getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    //custom view holder with the bind function to bind the data to the item row views
    class GitViewHolder(val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root){
        private val avatarImage=binding.ivAvatar
        private val repoDescription=binding.tvRepoDesc
        private val repoName=binding.tvRepoName
        private val repoOwnerName=binding.tvRepoOwner
        private val numberOfStars=binding.tvNumberOfStars
        private val numberOfIssues=binding.tvNumberOfIssues
        private val lang=binding.tvLang

        fun bind(data: Item){
            var createdDate:String=data.created_at.dropLast(10)
            val calendar= Calendar.getInstance()
            val formatter= SimpleDateFormat("yyyy-MM-dd")
            var today=formatter.format(calendar.time)
            val dateStart: Date = formatter.parse(createdDate)
            val dateEnd: Date = formatter.parse(today)
            var diffDays = Math.round((dateEnd.getTime() - dateStart.getTime()) / 86400000.toDouble())

            repoOwnerName.text="[Submitted ${diffDays} days ago by ${data.owner.login}]"
            repoName.text=data.name
            if(!TextUtils.isEmpty(data.description)) {
                repoDescription.text = data.description
            }else{
                repoDescription.text ="No Desc available."
            }

            if (data.stargazers_count>1000){
                var num=(data.stargazers_count.toDouble()/1000)
                var s=String.format("%.1f", num)+"k"
                numberOfStars.text="[Stars: $s]"
            }else{
                numberOfStars.text="[Stars: ${data.stargazers_count}]"
            }

            numberOfIssues.text="[Issues: ${data.open_issues_count}]"

            val url:String=data.owner.avatar_url
            avatarImage.load(url){
                crossfade(true)
                placeholder(R.drawable.ic_image_place_holder)
                transformations(CircleCropTransformation())
            }
            lang.text=data.language
        }
    }
    //checking for data change
    companion object{
        private val ITEM_COMPARATOR= object :DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem.id==newItem.id
            override fun areContentsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem==newItem
            }
        }
}