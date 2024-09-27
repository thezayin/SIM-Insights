package com.thezayin.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import com.thezayin.domain.model.HistoryModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun RecentPeak(
    modifier: Modifier = Modifier, list: List<HistoryModel>?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp)
    ) {
        Text(
            text = "History",
            modifier = Modifier.padding(top = 15.sdp, bottom = 10.sdp),
            fontSize = 12.ssp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
            color = colorResource(id = com.thezayin.values.R.color.text_color),
        )
        ShadeCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.sdp),
            cornerRadius = 10.sdp,
            backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.sdp)
            ) {
                if (list.isNullOrEmpty().not()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .padding(vertical = 20.sdp)
                    ) {
                        if (list != null) {
                            items(list.reversed()) { historyItem ->
                                HistoryCard(
                                    number = historyItem.phoneNumber,
                                    resultSuccess = historyItem.searchSuccess
                                )
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(
                            text = "No recent searches",
                            modifier = Modifier.padding(top = 15.sdp),
                            fontSize = 12.ssp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_regular)),
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                        )
                    }
                }
            }
        }
    }
}