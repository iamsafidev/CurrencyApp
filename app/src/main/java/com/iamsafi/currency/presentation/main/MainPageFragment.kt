package com.iamsafi.currency.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.iamsafi.currency.data.datastore.remote.model.Failure
import com.iamsafi.currency.data.models.Currency
import com.iamsafi.currency.databinding.FragmentMainPageBinding
import com.iamsafi.currency.presentation.adapters.CurrencyListAdapter
import com.iamsafi.currency.presentation.adapters.SpinnerAdapter
import com.iamsafi.currency.presentation.base.BaseFragment
import com.iamsafi.currency.presentation.model.ResultState

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
            amount.observe(viewLifecycleOwner, ::convertAmountToSelectedCurrency)
        }
    }

    private fun convertAmountToSelectedCurrency(amount: String) {
        var parsed = 1.0
        if (amount.isNotEmpty()) {
            parsed = amount.toDouble()
        }
        binding.adapter?.convert(parsed)
    }

    private fun handleCurrencyData(result: ResultState<List<Currency>, Failure>) {
        when (result) {
            is ResultState.Success -> {
                with(binding) {
                    result.data?.let { data ->
                        adapter?.setDataList(data)
                        spinnerAdapter?.setDataList(data)

                        currencyListSpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>,
                                    view: View,
                                    pos: Int,
                                    id: Long
                                ) {
                                    adapter?.updateSelectedCurrencyRate(data[pos].rate)
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {}
                            }
                        //TODO: Implement the LocalDB
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