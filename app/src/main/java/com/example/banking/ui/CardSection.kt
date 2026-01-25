package com.example.banking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.R
import com.example.banking.ui.model.Card
import com.example.banking.ui.theme.BlueEnd
import com.example.banking.ui.theme.BlueStart
import com.example.banking.ui.theme.GreenEnd
import com.example.banking.ui.theme.GreenStart
import com.example.banking.ui.theme.OrangeEnd
import com.example.banking.ui.theme.OrangeStart
import com.example.banking.ui.theme.PurpleEnd
import com.example.banking.ui.theme.PurpleStart

val cards = listOf<Card>(
    Card(
        cardType = "VISA",
        cardNumber = "9878 7602 6786 4193",
        cardName = "Business",
        balance = 34.532,
        color = getGradient(PurpleStart, PurpleEnd)
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = "5720 9343 9483 2589",
        cardName = "Savings",
        balance = 87.234,
        color = getGradient(BlueStart, BlueEnd)
    ),
    Card(
        cardType = "VISA",
        cardNumber = "0720 4280 9349 1698",
        cardName = "School",
        balance = 2.234,
        color = getGradient(OrangeStart, OrangeEnd)
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = " 7059 2998 2738 7340",
        cardName = "Trips",
        balance = 64.365,
        color = getGradient(GreenStart, GreenEnd)
    ),
)

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Preview
@Composable
fun CardSection(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(cards) {card ->
            CardItem(card)
        }
    }
}

@Composable
fun CardItem(card: Card, modifier: Modifier = Modifier) {
    var image = painterResource(R.drawable.ic_visa)

    if (card.cardType == "MASTER CARD")
        image = painterResource(R.drawable.ic_mastercard)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(card.color)
            .size(width = 250.dp, height = 160.dp)
            .clickable {}
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                painter = image,
                contentDescription = card.cardName,
                modifier = Modifier.width(60.dp)
            )

            Column {
                Text(
                    text = card.cardName,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$ ${card.balance}",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = card.cardNumber,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}