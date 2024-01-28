package com.example.spendbase.ui.main.card_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendbase.R
import com.example.spendbase.domain.entites.transactions.TransactionEntity
import com.example.spendbase.domain.getOct11Transactions
import com.example.spendbase.domain.getOct12Transactions
import com.example.spendbase.ui.components.BackgroundCard
import com.example.spendbase.ui.components.TransactionItem
import com.example.spendbase.ui.components.noRippleClickable
import com.example.spendbase.ui.theme.Blue500
import com.example.spendbase.ui.theme.BodyLarge17
import com.example.spendbase.ui.theme.GrayF6
import com.example.spendbase.ui.theme.HeadlineSmall17
import com.example.spendbase.ui.theme.LabelMedium13
import com.example.spendbase.ui.theme.LabelSmall12
import com.example.spendbase.ui.theme.Neutral200
import com.example.spendbase.ui.theme.Neutral500
import com.example.spendbase.ui.theme.Neutral800

@Composable
fun CardDetailsScreen(
    modifier: Modifier,
    cardName: String,
    cardNumber: String,
    onBackButtonClicked: () -> Unit
) {
    CardDetailsScreenContent(
        modifier = modifier,
        cardName = cardName,
        cardNumber = cardNumber,
        onBackButtonClicked = onBackButtonClicked
    )
}

@Composable
fun CardDetailsScreenContent(
    modifier: Modifier,
    cardName: String,
    cardNumber: String,
    onBackButtonClicked: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CardDetailsScreenTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 7.dp),
                onBackButtonClicked = onBackButtonClicked,
                cardName = cardName,
                cardNumber = cardNumber
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(GrayF6)
                .fillMaxSize()
                .padding(it)
                .then(Modifier.padding(horizontal = 16.dp))
        ) {
            item {
                CardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3.5f)
                        .padding(start = 60.dp, end = 60.dp, top = 20.dp)
                )

                DayTransactionSection(
                    modifier = Modifier.padding(top = 24.dp),
                    date = "${stringResource(R.string.oct)} 12",
                    transactions = getOct12Transactions()
                )

                DayTransactionSection(
                    modifier = Modifier.padding(top = 24.dp),
                    date = "${stringResource(R.string.oct)} 11",
                    transactions = getOct11Transactions()
                )
            }
        }
    }
}

@Composable
fun CardDetailsScreenTopBar(
    modifier: Modifier,
    onBackButtonClicked: () -> Unit,
    cardName: String,
    cardNumber: String
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .noRippleClickable { onBackButtonClicked() }
                .align(Alignment.CenterStart)
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(id = R.drawable.ic_icon_back),
                contentDescription = null,
                tint = Blue500
            )
            Text(
                text = stringResource(id = R.string.home),
                style = BodyLarge17,
                color = Blue500
            )
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cardName,
                style = HeadlineSmall17,
                color = Color.Black
            )
            Text(
                text = "•• $cardNumber",
                style = LabelMedium13,
                color = Neutral500
            )
        }
    }
}

@Composable
fun CardSection(modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Neutral800
        ),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_medium_logo),
                contentDescription = null
            )
            Image(
                painter = painterResource(R.drawable.ic_mastercard),
                contentDescription = null
            )
        }
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Neutral200)
            .alpha(0f)
    )
    BackgroundCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp, end = 60.dp, top = 15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_eye),
                        contentDescription = null,
                        tint = Neutral800
                    )
                }
                Text(
                    text = stringResource(R.string.details),
                    style = LabelSmall12,
                    color = Neutral800
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lock),
                        contentDescription = null,
                        tint = Neutral800
                    )
                }
                Text(
                    text = stringResource(R.string.lock),
                    style = LabelSmall12,
                    color = Neutral800
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_termainate),
                        contentDescription = null,
                        tint = Neutral800
                    )
                }
                Text(
                    text = stringResource(R.string.terminate),
                    style = LabelSmall12,
                    color = Neutral800
                )
            }
        }
    }
}

@Composable
fun DayTransactionSection(
    modifier: Modifier,
    date: String,
    transactions: List<TransactionEntity>
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = date,
            style = LabelMedium13,
            color = Neutral500
        )
        BackgroundCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            transactions.forEach { entity ->
                TransactionItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    entity = entity
                )
            }
        }
    }
}

@Preview
@Composable
fun CardDetailsScreenPreview() {
    CardDetailsScreenContent(
        modifier = Modifier.fillMaxSize(),
        cardName = "Travel",
        cardNumber = "•• 7544",
        onBackButtonClicked = {}
    )
}