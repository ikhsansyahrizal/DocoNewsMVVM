package com.docotel.ikhsansyahrizal.second.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.docotel.ikhsansyahrizal.doconewss.helper.DIFFUTIL_ARTICLEITEM_CALLBACK
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private val setOnItemClickListener: OnItemClickListener) :RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(article: ArticlesItem, position: Int)
    }


    private val differ = AsyncListDiffer(this, DIFFUTIL_ARTICLEITEM_CALLBACK)

    inner class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ArticlesItem, position: Int) {
            binding.tvTitle.text = data.title ?: ""
            binding.tvDesc.text = data.description ?: ""
            binding.tvAuthor.text = (data.author ?: "")
            binding.tvPublish.text = data.publishedAt ?: ""

            Picasso.get()
                .load(data.urlToImage)
                .into(binding.imgNews)

            binding.root.setOnClickListener {
                setOnItemClickListener.onItemClick(data, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bind(currentItem, position)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setList(list: MutableList<ArticlesItem?>) {
        differ.submitList(list)
    }



}