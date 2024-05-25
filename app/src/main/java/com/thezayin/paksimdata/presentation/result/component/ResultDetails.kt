package com.thezayin.paksimdata.presentation.result.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.widgets.ShadeCard
import com.thezayin.paksimdata.domain.model.SimDataModel

@Composable
fun ResultDetails(
    modifier: Modifier,
    simDetails: SimDataModel
) {
    ShadeCard(
        modifier = modifier
            .fillMaxWidth(),
        cornerRadius = 10.dp,
        backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 35.dp)
                .background(ConstantColor.NeumorphismLightBackgroundColor)
        ) {
            Text(
                text = "Name",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                color = colorResource(id = R.color.text_color_light),
            )
            Text(
                text = if (simDetails.name.length < 2) "Data not found in server" else simDetails.name,
                modifier = Modifier.padding(top = 15.dp),
                fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
            )
            Text(
                text = "CNIC:",
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 20.dp),
                fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                color = colorResource(id = R.color.text_color_light),
            )
            Text(
                text = if (simDetails.cnic.length < 2) "Data not found in server" else simDetails.cnic,
                modifier = Modifier.padding(top = 15.dp),
                fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
            )
            Text(
                text = "Address:",
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                color = colorResource(id = R.color.text_color_light),
            )
            Text(
                text = if (simDetails.address.length < 2) "Data not found in server" else simDetails.address,
                modifier = Modifier.padding(top = 15.dp),
                fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
            )
            Text(
                text = "Number:",
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                color = colorResource(id = R.color.text_color_light),
            )
            Text(
                text = if (simDetails.number.length < 2) "Data not found in server" else simDetails.number,
                modifier = Modifier.padding(top = 15.dp),
                fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                color = colorResource(id = R.color.black),
                fontSize = 24.sp,
            )

        }
    }
}