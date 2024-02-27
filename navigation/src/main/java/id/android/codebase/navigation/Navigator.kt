package id.android.codebase.navigation

import androidx.navigation.NavController

class Navigator(private val navController: NavController) {

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.HomeFlow -> navController.navigate(NavGraphBaseDirections.actionGlobalHomeFlow())
        NavigationFlow.LoginFlow -> navController.navigate(NavGraphBaseDirections.actionGlobalLoginFlow())
    }

}
