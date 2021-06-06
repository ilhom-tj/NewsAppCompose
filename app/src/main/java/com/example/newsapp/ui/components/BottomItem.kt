package com.example.newsapp.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.ui.theme.LightColorPalette
import com.example.newsapp.ui.theme.NewsAppTheme

sealed class BottomItem(val route : String , val title : String , val icon : ImageVector) {
    object Home : BottomItem("home","Home", Icons.Filled.Home)
    object Search : BottomItem("search","Search", Icons.Filled.Search)
    object Saved : BottomItem("saved","Saved", Icons.Filled.Favorite)
    object Setting : BottomItem("setting","Setting", Icons.Filled.Settings)
}
@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(
        BottomItem.Home,
        BottomItem.Search,
        BottomItem.Saved,
        BottomItem.Setting,
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = LightColorPalette.primary
    ){
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                alwaysShowLabel = false

            )
        }
    }
}