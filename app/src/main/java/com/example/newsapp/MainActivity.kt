package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.components.*
import com.example.newsapp.ui.screen.Home
import com.example.newsapp.ui.screen.NewsListScreen
import com.example.newsapp.viewmodels.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val newsViewModel by viewModels<NewsViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modifier = Modifier.padding(16.dp)
        newsViewModel.scratchNews()
        setContent {
            val navController = rememberNavController()
            Scaffold(
                topBar = { NewsToolBar(modifier = modifier) },
                bottomBar = { BottomNavBar(navController = navController) }
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable(BottomItem.Home.route) {
                        Home(modifier, newsViewModel,navController)
                    }
                    composable(BottomItem.Search.route) {

                    }
                    composable(BottomItem.Saved.route) {

                    }
                    composable(BottomItem.Setting.route) {

                    }
                    composable("newsList/{category}",
                        arguments = listOf(
                            navArgument("category") { type = NavType.StringType }
                        )) {
                        val category = it.arguments?.getString("category")
                        NewsListScreen(
                            category = category.toString(),
                            viewModel = newsViewModel
                        )
                    }
                }

            }
        }
    }
}

