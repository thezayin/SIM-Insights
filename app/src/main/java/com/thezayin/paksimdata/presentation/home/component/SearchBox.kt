package com.thezayin.paksimdata.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.Convexity
import com.thezayin.neumorphic.widgets.ShadeCard
import com.thezayin.neumorphic.widgets.ShadeSearchBar
import com.thezayin.paksimdata.presentation.home.HomeViewModel

@Composable
fun SearchBox(
    modifier: Modifier, viewModel: HomeViewModel
) {
    val number = remember { mutableStateOf(TextFieldValue()) }
    val showWarning = remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
            .padding(horizontal = 25.dp)
            .padding(top = 60.dp, bottom = 20.dp),
    ) {
        Text(
            text = "Enter Number",
            textAlign = TextAlign.Center,
            color = colorResource(id = com.thezayin.core.R.color.text_color),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_italic)),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
            ShadeSearchBar(
                value = number.value,
                onValueChange = {
                    number.value = it
                },
                cornerRadius = 25.dp,
                convexity = Convexity.CONCAVE,
                placeholderText = "03211234567",
                fontSize = 14.sp,
            )
            if (showWarning.value) {
                Text(
                    text = "Please enter a valid number",
                    color = colorResource(id = com.thezayin.core.R.color.red),
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_italic)),
                )
            }
            ShadeCard(
                onClick = {
                    if (number.value.text.length == 11) {
                        viewModel.searchNumber(number.value.text)
                        showWarning.value = false
                    } else {
                        showWarning.value = true
                    }
                }, cornerRadius = 40.dp, modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Search",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = com.thezayin.core.R.color.black),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(com.thezayin.core.R.font.abeezee_italic)),
                )
            }
        }
    }
}