package com.example.meteomania.ui.search

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meteomania.R
import com.example.meteomania.databinding.FragmentSearchBinding

//IMPORTANT: il faut passer le layour au constructeur de la classe mère FragmentSearch
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Notre code pour le fragment
    }


}