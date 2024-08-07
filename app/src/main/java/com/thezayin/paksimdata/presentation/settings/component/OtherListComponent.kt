package com.thezayin.paksimdata.presentation.settings.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.framework.extension.functions.openLink
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard

@Preview
@Composable
fun OtherListComponent(
//    modifier: Modifier, navigator: DestinationsNavigator
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ConstantColor.NeumorphismLightBackgroundColor)
            .padding(top = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Others",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.abeezee_italic)),
            color = colorResource(id = R.color.text_color),
        )

        ShadeCard(
            cornerRadius = 10.dp,
            onClick = {}
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(35.dp),
                    cornerRadius = 100.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "Leave a rating/review",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        ShadeCard(
            cornerRadius = 10.dp,
            onClick = {
                context.openLink(ABOUT_US_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(35.dp),
                    cornerRadius = 100.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_about),
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "About Us",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        ShadeCard(
            cornerRadius = 10.dp,
            onClick = {
//                context.sendMail()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShadeCard(
                    modifier = Modifier.size(35.dp),
                    cornerRadius = 100.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_app),
                        contentDescription = null,
                        modifier = Modifier
                            .size(13.dp),
                        alignment = Alignment.BottomCenter
                    )
                }
                Text(
                    text = "More Apps",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
    }
}