package com.amirhusseinsoori.grpckotlin.navigation

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.component.getArgByGson
import com.amirhusseinsoori.grpckotlin.ui.intro.Intro
import com.amirhusseinsoori.grpckotlin.ui.movie.Movie
import com.amirhusseinsoori.grpckotlin.ui.MovieViewModel
import com.amirhusseinsoori.grpckotlin.ui.details.ScreenDetails
import com.amirhusseinsoori.grpckotlin.ui.search.Search
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InitialNavGraph() {

    val navController: NavHostController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = NavRoute.IntroRoute.route) {
        addIntro(navController)
        addMainNavigation(navController)
        addSearchNavigation(navController)
        detailsNavigation(navController)

    }

}

@ExperimentalAnimationApi
fun NavGraphBuilder.addIntro(navController: NavController) {
    composable(NavRoute.IntroRoute.route,
        enterTransition = {
            when (initialState.destination.route) {
                NavRoute.MainRoute.route ->
                    slideInHorizontally(
                        initialOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                else -> null
            }
        },
        exitTransition = {
            when (initialState.destination.route) {
                NavRoute.MainRoute.route ->
                    slideOutHorizontally(
                        targetOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
                else -> null
            }
        },
        popEnterTransition = {
            when (initialState.destination.route) {
                NavRoute.MainRoute.route ->
                    slideInHorizontally(
                        initialOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                else -> null
            }
        }
    ) {
        Intro(navController = navController)

    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addMainNavigation(
    navController: NavController
) {

    composable(
        route = NavRoute.MainRoute.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
    ) {
        val viewModel: MovieViewModel = hiltViewModel()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            Movie(viewModel, navController = navController)
        }
    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addSearchNavigation(
    navController: NavController
) {

    composable(
        route = NavRoute.SearchRoute.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
    ) {
        val viewModel: MovieViewModel = hiltViewModel()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Search(viewModel,navController)
        }
    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.detailsNavigation(
    navController: NavController
) {
    composable(
        route = NavRoute.DetailRoute.route + "/{DomainMoviesItem}",
        arguments = listOf(
            navArgument("DomainMoviesItem") { type = NavType.StringType }
        ),
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 100,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(100))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 100,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(100))
        },
    ) { backStackEntry ->
        val dto = backStackEntry.arguments?.getString("DomainMoviesItem") ?: ""
        val movieItems = getArgByGson<DomainMoviesItem>(dto)
        ScreenDetails(movieItems)
    }


}

