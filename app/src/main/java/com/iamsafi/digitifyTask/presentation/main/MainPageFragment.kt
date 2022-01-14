package com.iamsafi.digitifyTask.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.iamsafi.digitifyTask.databinding.FragmentMainPageBinding
import com.iamsafi.digitifyTask.presentation.base.BaseFragment

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainPageBinding
        get() = FragmentMainPageBinding::inflate

}