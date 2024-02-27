package id.android.codebase.features.login

import id.android.codebase.common.base.BaseFragment
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentLoginBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by sharedViewModel()

    override fun getLayoutResId() = R.layout.fragment_login

    override fun main() {
        setupObserver()
        viewModel.getData()
    }

    override fun setupObserver() {

    }

}
