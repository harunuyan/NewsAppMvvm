package com.volie.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.volie.newsappmvvm.R
import com.volie.newsappmvvm.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment() {
    private var _mBinding : FragmentSearchNewsBinding? = null
    private val mBinding get() = _mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSearchNewsBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}