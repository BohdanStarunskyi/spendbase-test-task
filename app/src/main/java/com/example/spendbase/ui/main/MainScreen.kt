package com.example.spendbase.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spendbase.ui.components.BottomNavBar
import com.example.spendbase.ui.navigation.Screen

@Composable
fun MainScreen(
    modifier: Modifier,
    navigation: @Composable (modifier: Modifier) -> Unit,
    onScreenChanged: (screen: Screen) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                onScreenChanged = onScreenChanged
            )
        }
    ) {
        navigation(modifier.padding(it))
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        modifier = Modifier.fillMaxSize(),
        navigation = {},
        onScreenChanged = {}
    )
}