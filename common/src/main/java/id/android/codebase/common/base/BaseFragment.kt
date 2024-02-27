package id.android.codebase.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import id.android.codebase.common.extension.showSnackbar
import id.android.codebase.common.utils.observeEvent
import id.android.codebase.navigation.NavigationCommand

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected var binding: V? = null
        private set

    abstract val viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getCachedBinding() ?: DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.setVariable(BR.viewModel, viewModel)
        cacheBinding(binding)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBaseBinding(viewModel)
        main()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        cacheBinding(null)
        super.onDestroy()
    }

    open fun getCachedBinding(): V? = null
    open fun cacheBinding(binding: V?) = Unit

    abstract fun main()

    open fun setupObserver() = Unit
    open fun setupListener() = Unit

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    private fun observeBaseBinding(viewModel: VM) {
        viewModel.navigation.observeEvent(viewLifecycleOwner, { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions, getExtras())
                is NavigationCommand.Back -> findNavController().navigateUp()
            }
        })
        viewModel.snackBarError.observeEvent(viewLifecycleOwner, { res ->
            context?.let { showSnackbar(it.getString(res), Snackbar.LENGTH_LONG) }
        })
    }

    fun hideSystemUI() {
        activity?.let {
            WindowCompat.setDecorFitsSystemWindows(it.window, false)
            binding?.let { binding ->
                WindowInsetsControllerCompat(it.window, binding.root).let { controller ->
                    controller.hide(WindowInsetsCompat.Type.systemBars())
                    controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
        }
    }

    fun showSystemUI() {
        activity?.let {
            WindowCompat.setDecorFitsSystemWindows(it.window, true)
            binding?.let { binding ->
                WindowInsetsControllerCompat(it.window, binding.root).show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    protected fun safeNavigateTo(directions: NavDirections) {
        val currentFragmentLabel = findNavController().currentDestination?.label
        if (currentFragmentLabel == this::class.simpleName) {
            try {
                findNavController().navigate(directions)
            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }
    }

}
