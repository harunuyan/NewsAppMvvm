package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.volie.newsappmvvm.databinding.FragmentArticleBinding
import com.volie.newsappmvvm.viewmodels.ArticleViewModel

class ArticleFragment : Fragment() {
    private var _mBinding: FragmentArticleBinding? = null
    private val mBinding get() = _mBinding!!
    val args: ArticleFragmentArgs by navArgs()
    private lateinit var mViewModel: ArticleViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentArticleBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ArticleViewModel(requireContext())
        val article = args.article
        mBinding.webView.apply {
            webViewClient = WebViewClient()
            try {
                loadUrl(article.url!!)
            } catch (e: Exception) {
                Log.e("$this@ArticleFragment", "$e")
            }
        }

        mBinding.fab.setOnClickListener {
            mViewModel.saveArticle(article)
            Snackbar.make(view, "Article saved succesfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}