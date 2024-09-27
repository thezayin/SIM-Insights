package com.thezayin.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.common.neumorphic.ConstantColor
import com.thezayin.common.neumorphic.widgets.ShadeCard
import ir.kaaveh.sdpcompose.sdp

@Preview
@Composable
fun DeleteHistory(
    modifier: Modifier = Modifier,
    onDeleted: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 15.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        ShadeCard(
            modifier = Modifier.size(40.sdp).clickable {
                onDeleted()
                Log.d("jejeDeleteHistory", "Delete History")
                                                       },
            cornerRadius = 40.sdp,
            backgroundColor = ConstantColor.NeumorphismLightBackgroundColor
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(20.sdp),
                    contentDescription = "Delete History",
                    painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_delete),
                )
            }
        }
    }
}