package com.example.banking.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.model.PaymentDetail
import com.example.banking.ui.model.PaymentDirection
import com.example.banking.ui.model.Person

val peopleList = listOf(
    Person("Abhinav", PaymentDetail("500", "12 Oct 2026", "10:30 AM", "Success", PaymentDirection.TO)),
    Person("Priyanshu", PaymentDetail("1200", "11 Oct 2026", "02:15 PM", "Success", PaymentDirection.FROM)),
    Person("Eshan", PaymentDetail("250", "10 Oct 2026", "09:45 AM", "Success", PaymentDirection.TO)),
    Person("Ashok", PaymentDetail("3000", "09 Oct 2026", "06:00 PM", "Pending", PaymentDirection.TO)),
    Person("Rajib", PaymentDetail("150", "08 Oct 2026", "11:20 AM", "Success", PaymentDirection.FROM)),
    Person("Bishal", PaymentDetail("450", "07 Oct 2026", "01:10 PM", "Success", PaymentDirection.TO)),
    Person("Odisse", PaymentDetail("900", "06 Oct 2026", "12:00 PM", "Success", PaymentDirection.FROM)),
    Person("Sagar", PaymentDetail("100", "05 Oct 2026", "04:30 PM", "Failed", PaymentDirection.TO)),
    Person("Ankit", PaymentDetail("2000", "04 Oct 2026", "08:15 AM", "Success", PaymentDirection.TO)),
    Person("Rahul", PaymentDetail("750", "03 Oct 2026", "03:50 PM", "Success", PaymentDirection.FROM))
)

@Composable
fun PeopleSection() {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .animateContentSize()
    ) {
        // Row 1: Always visible
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 3) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    if (i < peopleList.size) {
                        PersonItem(peopleList[i].name, peopleList[i].color)
                    } else {
                        Spacer(modifier = Modifier.size(56.dp))
                    }
                }
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                if (isExpanded && peopleList.size > 3) {
                    PersonItem(peopleList[3].name, peopleList[3].color)
                } else {
                    ToggleMoreItem(isExpanded) { isExpanded = !isExpanded }
                }
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 4 until 7) {
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            if (i < peopleList.size) {
                                PersonItem(peopleList[i].name, peopleList[i].color)
                            } else {
                                Spacer(modifier = Modifier.size(56.dp))
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        ToggleMoreItem(isExpanded) { isExpanded = !isExpanded }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonItem(name: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 11.sp,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ToggleMoreItem(isExpanded: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Show Less" else "Show More",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isExpanded) "Less" else "More",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}
