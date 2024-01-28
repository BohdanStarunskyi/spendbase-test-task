package com.example.spendbase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendbase.R
import com.example.spendbase.ui.navigation.MainScreen
import com.example.spendbase.ui.navigation.Screen
import com.example.spendbase.ui.theme.Blue500
import com.example.spendbase.ui.theme.Neutral0
import com.example.spendbase.ui.theme.Neutral400
import com.example.spendbase.ui.theme.Regular10

@Composable
fun BottomNavBar(
    modifier: Modifier,
    onScreenChanged: (screen: Screen) -> Unit
) {
    var currentSelectedScreen by rememberSaveable { mutableStateOf(NavBarButtons.HOME) }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Neutral0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NavBarButtons.entries.forEach {
                NavbarButton(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    button = it,
                    onClick = { screen ->
                        if (currentSelectedScreen != it) {
                            currentSelectedScreen = it
                            onScreenChanged(screen)
                        }
                    },
                    isActive = currentSelectedScreen == it
                )
            }
        }
    }
}

@Composable
fun NavbarButton(
    modifier: Modifier,
    button: NavBarButtons,
    onClick: (screen: Screen) -> Unit,
    isActive: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val color = if (isActive) Blue500 else Neutral400
        IconButton(
            onClick = {
                onClick(button.screen)
            }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(button.iconRes),
                contentDescription = null,
                tint = color
            )
        }
        Text(text = stringResource(button.titleRes), style = Regular10, color = color)
    }
}

enum class NavBarButtons(
    val iconRes: Int,
    val titleRes: Int,
    val screen: Screen
) {
    HOME(iconRes = R.drawable.ic_home, titleRes = R.string.home, screen = MainScreen.HomeScreen),
    TRANSACTIONS(
        iconRes = R.drawable.ic_transactions,
        titleRes = R.string.transactions,
        screen = MainScreen.TransactionsScreen
    ),
    MY_CARDS(
        iconRes = R.drawable.ic_card,
        titleRes = R.string.my_cards,
        screen = MainScreen.MyCardsScreen
    ),
    ACCOUNTS(
        iconRes = R.drawable.ic_profile,
        titleRes = R.string.account,
        screen = MainScreen.AccountScreen
    )
}

@Preview
@Composable
fun NavBarPreview() {
    BottomNavBar(modifier = Modifier.fillMaxWidth(), onScreenChanged = {})
}