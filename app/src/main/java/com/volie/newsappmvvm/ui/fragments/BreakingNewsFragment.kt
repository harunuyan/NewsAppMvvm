package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.volie.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.volie.newsappmvvm.models.Article
import com.volie.newsappmvvm.ui.adapters.NewsAdapter
import com.volie.newsappmvvm.viewmodels.BreakingNewsViewModel

class BreakingNewsFragment : Fragment(), NewsAdapter.Listener {
    private var _mBinding: FragmentBreakingNewsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mViewModel: BreakingNewsViewModel
    private lateinit var mAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = BreakingNewsViewModel(requireContext())
        setupAdapter()
        mViewModel.getBreakingNews("us")
        initObserver()
    }

    private fun setupAdapter() {
        mAdapter = NewsAdapter(this)
        with(mBinding.rvBreakingNews) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObserver() {
        mViewModel.mBreakingNews.observe(viewLifecycleOwner) {
            mAdapter.differ.submitList(it)
        }

        mViewModel.mLoadingData.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    mBinding.paginationProgressBar.visibility = View.VISIBLE
                } else {
                    mBinding.paginationProgressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

    override fun onItemClick(article: Article) {
        Bundle().putSerializable("article", article)
        val action =
            BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(article)
        Navigation.findNavController(mBinding.root).navigate(action)
    }
}