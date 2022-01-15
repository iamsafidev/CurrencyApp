package com.iamsafi.digitifyTask.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.iamsafi.digitifyTask.data.datastore.remote.model.Failure
import com.iamsafi.digitifyTask.data.models.Currency
import com.iamsafi.digitifyTask.databinding.FragmentMainPageBinding
import com.iamsafi.digitifyTask.presentation.adapters.CurrencyListAdapter
import com.iamsafi.digitifyTask.presentation.adapters.SpinnerAdapter
import com.iamsafi.digitifyTask.presentation.base.BaseFragment
import com.iamsafi.digitifyTask.presentation.model.ResultState

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainPageBinding
        get() = FragmentMainPageBinding::inflate

    private val mainPageViewModel: MainPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        with(mainPageViewModel) {
            currencyData.observe(viewLifecycleOwner) {
                handleCurrencyData(it)
            }
        }
    }

    private fun handleCurrencyData(result: ResultState<List<Currency>, Failure>) {
        when (result) {
            is ResultState.Success -> {
                with(binding) {
                    result.data?.let { data ->
                        adapter?.setDataList(data)
                        spinnerAdapter?.setDataList(data)
                    }
                }
            }
            is ResultState.Error -> showGeneralError()
        }
    }

    private fun setupUI() {
        binding.apply {
            this.adapter = CurrencyListAdapter()
            this.recyclerView.layoutManager = GridLayoutManager(context, 3)
            this.spinnerAdapter = SpinnerAdapter(requireContext())
            this.viewModel = mainPageViewModel
        }
    }
}