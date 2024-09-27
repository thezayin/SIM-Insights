package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun HistoryDetails(
    number: String = "0312-1234567",
    name: String? = "John Doe",
    cnic: String? = "12345-1234567-1",
    address: String? = "House # 123, Street # 123, Sector 123, Islamabad",
    resultSuccess: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShadeCard(
            modifier = Modifier
                .fillMaxWidth(),
            cornerRadius = 10.sdp,
            backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.sdp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = number,
                        fontSize = 12.ssp,
                        color = colorResource(id = com.thezayin.values.R.color.text_color),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    )
                    ShadeCard(
                        cornerRadius = 40.sdp,
                        modifier = Modifier
                            .width(70.sdp)
                            .height(20.sdp)
                    )
                    {
                        Text(
                            text = if (resultSuccess) "Success" else "Failed",
                            fontSize = 12.ssp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                        )
                    }
                }
                if (resultSuccess) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.sdp),
                        verticalArrangement = Arrangement.spacedBy(5.sdp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = name ?: "Name not found",
                            fontSize = 12.ssp,
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                        )
                        Text(
                            text = cnic ?: "CNIC not found",
                            fontSize = 12.ssp,
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                        )
                        Text(
                            text = address ?: "Address not found",
                            fontSize = 12.ssp,
                            color = colorResource(id = com.thezayin.values.R.color.text_color),
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                        )
                    }
                } else {
                    Text(
                        modifier = Modifier.padding(10.sdp),
                        text = "Result Not found in database",
                        fontSize = 12.ssp,
                        color = colorResource(id = com.thezayin.values.R.color.text_color),
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    )
                }
            }
        }
    }
}