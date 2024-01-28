package com.example.spendbase.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendbase.R
import com.example.spendbase.ui.theme.HeadlineMedium34
import com.example.spendbase.ui.theme.LabelLarge15
import com.example.spendbase.ui.theme.Neutral0
import com.example.spendbase.ui.theme.Neutral500
import com.example.spendbase.ui.theme.Neutral900

@Composable
fun BackgroundCard(
    modifier: Modifier,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    content: @Composable () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Neutral0),
        border = borderStroke
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun BackgroundCardPreview() {
    BackgroundCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        content = {
            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 5.dp
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.padding(end = 8.dp),
                        painter = painterResource(id = R.drawable.ic_usa),
                        contentDescription = null
                    )
                    Text(text = "USD account", style = LabelLarge15, color = Neutral500)
                }
                Text(text = "$1,000", style = HeadlineMedium34, color = Neutral900)
            }
        }
    )
}