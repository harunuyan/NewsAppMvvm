package com.volie.newsappmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.newsappmvvm.databinding.ItemArticlePreviewBinding
import com.volie.newsappmvvm.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val article = differ.currentList[position]
            binding.apply {
                Glide.with(this.root).load(article.urlToImage).into(ivArticleImage)
                tvSource.text = article.source.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
                onItemClickListener?.let { it(article)}
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article)-> Unit)? = null

    fun  setOnItemClickListener(listener: (Article)-> Unit) {
         onItemClickListener = listener
    }


}