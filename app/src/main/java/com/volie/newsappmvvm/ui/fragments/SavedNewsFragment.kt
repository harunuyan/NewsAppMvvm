package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.volie.newsappmvvm.databinding.FragmentSavedNewsBinding
import com.volie.newsappmvvm.models.Article
import com.volie.newsappmvvm.ui.adapters.NewsAdapter

class SavedNewsFragment : Fragment(), NewsAdapter.Listener {
    private var _mBinding: FragmentSavedNewsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private fun setupAdapter() {
        mAdapter = NewsAdapter(this)
        with(mBinding.rvSavedNews) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

    override fun onItemClick(article: Article) {

        val bundle = Bundle().apply {
            putSerializable("article", article)
        }
        val action =
            SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(article)
        Navigation.findNavController(mBinding.root).navigate(action)
    }
}