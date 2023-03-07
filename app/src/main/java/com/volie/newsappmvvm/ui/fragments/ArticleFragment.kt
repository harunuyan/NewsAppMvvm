package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.volie.newsappmvvm.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private var _mBinding: FragmentArticleBinding? = null
    private val mBinding get() = _mBinding!!
    val args: ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentArticleBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleArgs = args.article
        mBinding.webView.apply {
            webViewClient = WebViewClient()
            try {
                loadUrl(articleArgs.url!!)
            } catch (e: Exception) {
                Log.e("$this@ArticleFragment", "$e")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}