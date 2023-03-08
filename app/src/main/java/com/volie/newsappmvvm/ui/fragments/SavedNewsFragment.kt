package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.volie.newsappmvvm.databinding.FragmentSavedNewsBinding
import com.volie.newsappmvvm.models.Article
import com.volie.newsappmvvm.ui.adapters.NewsAdapter
import com.volie.newsappmvvm.viewmodels.SavedNewsViewModel

class SavedNewsFragment : Fragment(), NewsAdapter.Listener {
    private var _mBinding: FragmentSavedNewsBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var mAdapter: NewsAdapter
    private lateinit var mViewModel: SavedNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = SavedNewsViewModel(requireContext())
        setupAdapter()
        swipeDelete(view)
    }

    private fun setupAdapter() {
        mAdapter = NewsAdapter(this)
        with(mBinding.rvSavedNews) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onItemClick(article: Article) {

        Bundle().putSerializable("article", article)
        val action =
            SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(article)
        Navigation.findNavController(mBinding.root).navigate(action)
    }

    private fun swipeDelete(view: View) {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = mAdapter.differ.currentList[position]
                mViewModel.deleteArticle(article)
                Snackbar.make(
                    view, "Succesfully deleted : ${article.title}",
                    Snackbar.LENGTH_LONG
                )
                    .apply {
                        setAction("Undo") {
                            mViewModel.saveUndoArticle(article)
                        }.show()
                    }
            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(mBinding.rvSavedNews)
        }
        initObserver()
    }

    private fun initObserver() {
        mViewModel.getSavedNews().observe(viewLifecycleOwner) {
            mAdapter.differ.submitList(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}