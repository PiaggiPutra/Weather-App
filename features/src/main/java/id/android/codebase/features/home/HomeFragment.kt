package id.android.codebase.features.home

import id.android.codebase.common.base.BaseFragment
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by inject()

    override fun getLayoutResId() = R.layout.fragment_home

    override fun main() {

    }

}
