package com.example.spendbase.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendbase.domain.entites.transactions.TransactionEntity
import com.example.spendbase.domain.entites.transactions.TransactionStatusesEnum
import com.example.spendbase.domain.getFakeTransaction
import com.example.spendbase.ui.theme.Green00
import com.example.spendbase.ui.theme.LabelLarge15
import com.example.spendbase.ui.theme.LabelMedium13
import com.example.spendbase.ui.theme.Neutral50
import com.example.spendbase.ui.theme.Neutral500
import com.example.spendbase.ui.theme.Neutral800
import com.example.spendbase.ui.theme.Neutral900
import com.example.spendbase.ui.theme.Red500
import com.example.spendbase.ui.theme.SemiBold10
import com.example.spendbase.ui.theme.TitleMedium16

@Composable
fun CardItem(modifier: Modifier, cardNumber: String, cardName: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .size(
                    width = 48.dp,
                    height = 32.dp
                )
                .padding(end = 12.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Neutral800)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    text = cardNumber,
                    style = SemiBold10,
                    color = Color.White
                )
            }
        }
        Text(
            text = cardName,
            style = LabelLarge15,
            color = Neutral900
        )
    }
}

@Composable
fun TransactionItem(
    modifier: Modifier,
    entity: TransactionEntity
) {
    Row(
        modifier = modifier.rippleClickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(90.dp))
                    .size(40.dp),
                colors = CardDefaults.cardColors(containerColor = Neutral50)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(1f),
                    painter = painterResource(entity.startIconResource),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            if (entity.status == TransactionStatusesEnum.DECLINED) {
                Surface(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd),
                    color = Red500,
                    shape = RoundedCornerShape(90),
                    content = {}
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = entity.title, style = LabelLarge15, color = Neutral800)
            if (entity.status == TransactionStatusesEnum.DONE) {
                Text(text = entity.cardNumber, style = LabelMedium13, color = Neutral500)
            } else {
                Text(text = "Declined", style = LabelMedium13, color = Red500)
            }
        }


        val textColor = if (entity.status == TransactionStatusesEnum.DECLINED) {
            Neutral500
        } else if (entity.amount > 0) {
            Green00
        } else {
            Neutral800
        }
        val text = if (entity.amount < 0) {
            "-$${String.format("%,d", entity.amount * -1)}"
        } else {
            "$${String.format("%,d", entity.amount)}"
        }

        Text(
            modifier = Modifier.padding(end = 7.5f.dp),
            text = text,
            style = TitleMedium16,
            color = textColor
        )
        if (entity.endIconResource == null) {
            Spacer(modifier = Modifier.size(20.dp))
        } else {
            Image(painter = painterResource(entity.endIconResource), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    CardItem(
        modifier = Modifier.fillMaxWidth(),
        cardNumber = "4141",
        cardName = "Virtual card"
    )
}

@Preview
@Composable
fun TransactionPreview() {
    TransactionItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        entity = getFakeTransaction()
    )
}