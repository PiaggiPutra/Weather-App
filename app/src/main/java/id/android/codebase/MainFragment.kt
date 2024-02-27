package id.android.codebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.android.codebase.databinding.FragmentMainBinding
import id.android.codebase.navigation.NavigationFlow
import id.android.codebase.navigation.Navigator

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var navigator: Navigator? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        navigator = Navigator(findNavController())
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLoggedIn()
    }

    private fun checkLoggedIn() {
        val isLoggedIn = (0..1).random() == 1
        val flow = if (isLoggedIn) NavigationFlow.HomeFlow else NavigationFlow.LoginFlow
        navigator?.navigateToFlow(flow)
    }
}
