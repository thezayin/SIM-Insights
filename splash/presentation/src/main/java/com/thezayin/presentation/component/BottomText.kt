package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BottomText(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(end = 10.sdp).size(20.sdp),
            color = androidx.compose.ui.graphics.Color.Black
        )
        Text(
            textAlign = TextAlign.Center,
            text = "SIM Insights",
            fontSize = 14.ssp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular))
        )
    }
}