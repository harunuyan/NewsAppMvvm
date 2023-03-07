package com.volie.newsappmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.volie.newsappmvvm.databinding.ItemArticlePreviewBinding
import com.volie.newsappmvvm.models.Article

class NewsAdapter(private val listener: Listener) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val newsList: MutableList<Article> = mutableListOf()

    interface Listener {
        fun onItemClick(article: Article)
    }

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val article = newsList[position]
            binding.apply {
                Glide.with(this.root).load(article.urlToImage).into(ivArticleImage)
                tvSource.text = article.source?.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
                root.setOnClickListener {
                    listener.onItemClick(newsList[position])
                }
            }
        }
    }

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
        return newsList.size
    }

    fun setData(items: List<Article>) {
        newsList.clear()
        newsList.addAll(items)
        notifyDataSetChanged()
    }
}