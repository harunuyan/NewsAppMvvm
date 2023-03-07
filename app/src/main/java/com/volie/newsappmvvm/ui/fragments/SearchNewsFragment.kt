package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.volie.newsappmvvm.databinding.FragmentSearchNewsBinding
import com.volie.newsappmvvm.ui.adapters.NewsAdapter
import com.volie.newsappmvvm.viewmodels.SearchNewsViewModel

class SearchNewsFragment : Fragment() {
    private var _mBinding: FragmentSearchNewsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mViewModel: SearchNewsViewModel
    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = SearchNewsViewModel(requireContext())
        setupAdapter()

        mBinding.etSearch.addTextChangedListener { editable ->
            editable?.let {
                if (editable.toString().isNotEmpty()) {
                    mViewModel.searchNews(editable.toString())
                }
            }
        }
        initObserver()
    }

    private fun setupAdapter() {
        mAdapter = NewsAdapter()
        with(mBinding.rvSearchNews) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    private fun initObserver() {
        mViewModel.mSearchNews.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }

        mViewModel.mLoadingData.observe(viewLifecycleOwner) {
            if (it) {
                mBinding.paginationProgressBar.visibility = View.VISIBLE
            } else {
                mBinding.paginationProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}