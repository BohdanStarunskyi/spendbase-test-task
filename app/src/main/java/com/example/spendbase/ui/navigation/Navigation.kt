package com.example.spendbase.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spendbase.ui.main.MainScreen
import com.example.spendbase.ui.main.card_details.CardDetailsScreen
import com.example.spendbase.ui.main.home.HomeScreen
import com.example.spendbase.ui.theme.GrayF6
import com.example.spendbase.ui.theme.HeadlineMedium34
import com.example.spendbase.ui.theme.Neutral900

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "main"
    ) {
        MainScreen.get(
            navGraphBuilder = this,
            onCardClicked = { name, number ->
                navController.navigate(
                    route = "${CardDetailsScreen.route}/$name/$number"
                )
            }
        )
        CardDetailsScreen.get(
            navGraphBuilder = this,
            onBackPressed = {
                navController.navigateUp()
            }
        )
    }
}

object MainScreen : Screen("main") {
    fun get(
        navGraphBuilder: NavGraphBuilder,
        onCardClicked: (name: String, number: String) -> Unit
    ) {
        navGraphBuilder.composable(MainScreen.route) {
            val hasCards = rememberSaveable { mutableStateOf(false) }
            val internalController = rememberNavController()
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                navigation = {
                    NavHost(
                        modifier = it,
                        navController = internalController,
                        startDestination = "home",
                        enterTransition = {
                            fadeIn()
                        },
                        exitTransition = {
                            fadeOut()
                        }
                    ) {
                        HomeScreen.get(this, onCardClicked, hasCards)
                        TransactionsScreen.get(this)
                        MyCardsScreen.get(this)
                        AccountScreen.get(this)
                    }
                },
                onScreenChanged = {
                    internalController.navigate(it.route)
                }
            )
        }
    }

    object HomeScreen : Screen("home") {
        fun get(
            navGraphBuilder: NavGraphBuilder,
            onCardClicked: (name: String, number: String) -> Unit,
            hasCards: MutableState<Boolean>
        ) {
            navGraphBuilder.composable(HomeScreen.route) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    onCardClicked = onCardClicked,
                    hasCards = hasCards
                )
            }
        }
    }

    object TransactionsScreen : Screen("transactions") {
        fun get(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.composable(TransactionsScreen.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayF6)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Transactions",
                        style = HeadlineMedium34,
                        color = Neutral900
                    )
                }
            }
        }
    }

    object MyCardsScreen : Screen("my_cards") {
        fun get(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.composable(MyCardsScreen.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayF6)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "My Cards",
                        style = HeadlineMedium34,
                        color = Neutral900
                    )
                }
            }
        }
    }

    object AccountScreen : Screen("account") {
        fun get(navGraphBuilder: NavGraphBuilder) {
            navGraphBuilder.composable(AccountScreen.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GrayF6)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Account",
                        style = HeadlineMedium34,
                        color = Neutral900
                    )
                }
            }
        }
    }
}

object CardDetailsScreen : Screen("card_details") {
    fun get(
        navGraphBuilder: NavGraphBuilder,
        onBackPressed: () -> Unit
    ) {
        navGraphBuilder.composable(
            route = "${CardDetailsScreen.route}/{cardName}/{cardNumber}",
            arguments = listOf(
                navArgument("cardName") { type = NavType.StringType },
                navArgument("cardNumber") { type = NavType.StringType }
            ),
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(250),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            val name = it.arguments?.getString("cardName")
            val number = it.arguments?.getString("cardNumber")
            CardDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                cardName = name ?: "",
                cardNumber = number ?: "",
                onBackButtonClicked = onBackPressed
            )
        }
    }
}
