package com.iamsafi.currency.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.iamsafi.currency.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class InjectableFragment : Fragment()

abstract class BaseFragment<VB : ViewDataBinding> : InjectableFragment() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupNavigation()
    }

    private fun setupNavigation() {
        val toolbar =
            (binding.root as ViewGroup).findViewWithTag<Toolbar>(getString(R.string.toolbar_tag))

        toolbar?.let {
            val activity = requireActivity() as AppCompatActivity
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)

        }
    }

    protected fun showGeneralError(
        @StringRes title: Int = R.string.common_error_title,
        @StringRes message: Int? = null
    ) {
        MaterialAlertDialogBuilder(requireContext())
            .setPositiveButton(getString(R.string.common_ok).uppercase()) { _, _ ->
                requireActivity().finish()
            }
            .setCancelable(false)
            .setTitle(title)
            .apply { if (message != null) setMessage(message) }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}