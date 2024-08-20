package com.thezayin.setting.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import com.thezayin.framework.extension.functions.openLink
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun OtherListComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
            .padding(top = 20.sdp)
            .padding(horizontal = 20.sdp)
    ) {
        Text(
            text = "Others",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.sdp),
            fontSize = 14.ssp,
            fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
            color = colorResource(id = com.thezayin.values.R.color.text_color),
        )

        ShadeCard(
            cornerRadius = 10.sdp,
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(30.sdp),
                    cornerRadius = 100.sdp
                ) {
                    Image(
                        painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.sdp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "Leave a rating/review",
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    fontSize = 12.ssp,
                    modifier = Modifier
                        .padding(vertical = 20.sdp)
                        .padding(start = 15.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        ShadeCard(
            cornerRadius = 10.sdp,
            onClick = {
                context.openLink(ABOUT_US_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(30.sdp),
                    cornerRadius = 100.sdp
                ) {
                    Image(
                        painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_about),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.sdp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "About Us",
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    fontSize = 12.ssp,
                    modifier = Modifier
                        .padding(vertical = 20.sdp)
                        .padding(start = 15.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.sdp)
        )

        ShadeCard(
            cornerRadius = 10.sdp,
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(30.sdp),
                    cornerRadius = 100.sdp
                ) {
                    Image(
                        painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_app),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.sdp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "More Apps",
                    color = colorResource(id = com.thezayin.values.R.color.text_color),
                    fontFamily = FontFamily(Font(com.thezayin.font.R.font.abeezee_italic)),
                    fontSize = 12.ssp,
                    modifier = Modifier
                        .padding(vertical = 20.sdp)
                        .padding(start = 15.sdp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )
    }
}