package com.example.spendbase.ui.main.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendbase.R
import com.example.spendbase.domain.getFakeCards
import com.example.spendbase.domain.getHomeTransactions
import com.example.spendbase.ui.components.BackgroundCard
import com.example.spendbase.ui.components.CardItem
import com.example.spendbase.ui.components.TransactionItem
import com.example.spendbase.ui.components.rippleClickable
import com.example.spendbase.ui.theme.Blue100
import com.example.spendbase.ui.theme.Blue500
import com.example.spendbase.ui.theme.BodyLarge17
import com.example.spendbase.ui.theme.GrayF6
import com.example.spendbase.ui.theme.HeadlineMedium28
import com.example.spendbase.ui.theme.HeadlineMedium34
import com.example.spendbase.ui.theme.HeadlineSmall17
import com.example.spendbase.ui.theme.LabelLarge15
import com.example.spendbase.ui.theme.Neutral500
import com.example.spendbase.ui.theme.Neutral800
import com.example.spendbase.ui.theme.Neutral900
import com.example.spendbase.ui.theme.TitleMedium16

@Composable
fun HomeScreen(
    modifier: Modifier,
    onCardClicked: (name: String, number: String) -> Unit,
    hasCards: MutableState<Boolean>
) {
    HomeScreenContent(
        modifier = modifier,
        onCardClicked = onCardClicked,
        hasCards = hasCards
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    onCardClicked: (name: String, number: String) -> Unit,
    hasCards: MutableState<Boolean>
) {
    LazyColumn(
        modifier = modifier
            .background(GrayF6)
            .then(Modifier.padding(start = 16.dp, end = 16.dp))
    ) {
        item {
            HomeTopBar(
                modifier = Modifier
                    .height(89.dp)
                    .fillMaxWidth()
            )

            BalanceSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            AnimatedVisibility(
                visible = !hasCards.value,
                exit = ExitTransition.None
            ) {
                BackgroundCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .rippleClickable {
                            hasCards.value = true
                        },
                    borderStroke = BorderStroke(2.dp, Blue100)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2.1f)
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = null,
                                tint = Blue500
                            )
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = "Create Card",
                                style = TitleMedium16,
                                color = Blue500
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = hasCards.value,
                enter = slideInVertically(tween(300)) { it / 2 }
            ) {
                Column {
                    CardsSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        onCardClicked = onCardClicked
                    )
                    TransactionsSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeTopBar(modifier: Modifier) {
    val dropdownState = remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { dropdownState.value = true }
        ) {
            HomeScreenDropdownMenu(
                dropdownState = dropdownState
            )
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = null,
                tint = Blue500
            )
        }
        Text(
            text = stringResource(R.string.money),
            style = HeadlineMedium28,
            color = Neutral800
        )
    }
}

@Composable
fun BalanceSection(modifier: Modifier) {
    BackgroundCard(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_usa),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.usd_account),
                style = LabelLarge15,
                color = Neutral500
            )
        }
        Text(text = "$1,000", style = HeadlineMedium34, color = Neutral900)
    }
}

@Composable
fun CardsSection(
    modifier: Modifier,
    onCardClicked: (name: String, number: String) -> Unit
) {
    BackgroundCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.my_cards),
                style = HeadlineSmall17,
                color = Neutral800
            )
            Text(
                modifier = Modifier.rippleClickable { },
                text = stringResource(R.string.see_all),
                style = LabelLarge15,
                color = Blue500
            )
        }
        getFakeCards().forEach { entity ->
            CardItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .rippleClickable { onCardClicked(entity.name, entity.number) },
                cardNumber = entity.number,
                cardName = entity.name
            )
        }
    }
}

@Composable
fun TransactionsSection(modifier: Modifier) {
    BackgroundCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.recent_transactions),
                style = HeadlineSmall17,
                color = Neutral800
            )
            Text(
                modifier = Modifier.rippleClickable { },
                text = stringResource(R.string.see_all),
                style = LabelLarge15,
                color = Blue500
            )
        }
        getHomeTransactions().forEach { entity ->
            TransactionItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                entity = entity
            )
        }
    }
}

@Composable
fun HomeScreenDropdownMenu(
    dropdownState: MutableState<Boolean>
) {
    DropdownMenu(
        modifier = Modifier.background(Color.White),
        expanded = dropdownState.value,
        onDismissRequest = { dropdownState.value = false }
    ) {
        DropdownActions.entries.forEachIndexed { index, item ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .rippleClickable { dropdownState.value = false },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 50.dp),
                        text = stringResource(item.textRes),
                        style = BodyLarge17,
                        color = Color.Black
                    )
                    Icon(
                        painter = painterResource(item.endIconRes),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                if (index != DropdownActions.entries.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        modifier = Modifier.fillMaxSize(),
        onCardClicked = { _, _ -> },
        hasCards = rememberSaveable { mutableStateOf(false) }
    )
}