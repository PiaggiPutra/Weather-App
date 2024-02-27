package id.android.codebase.navigation

sealed class NavigationFlow {
    object LoginFlow : NavigationFlow()
    object HomeFlow : NavigationFlow()
}
