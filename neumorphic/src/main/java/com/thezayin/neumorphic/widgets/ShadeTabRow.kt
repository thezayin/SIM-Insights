package com.thezayin.neumorphic.widgets

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.thezayin.neumorphic.ConstantColor
import com.thezayin.neumorphic.LightSource
import com.thezayin.neumorphic.ext.backgroundShadow
import com.thezayin.neumorphic.ext.foregroundShadow

@Composable
fun ShadeTabRow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    cornerRadius: Dp = 0.dp,
    selectedTabIndex: Int = 0,//Default is 0
    paddingDp: Dp = 5.dp,
    sliderColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    shadowColorLightSlider: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDarkSlider: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    tabs: @Composable @UiComposable () -> Unit,
) {
    val TAG = "ShadeTabRow"
    var tabLayoutWidth by remember { mutableFloatStateOf(0f) } //width of tabLayout
    val paddingDpCurrent = paddingDp
    val paddingPx = with(LocalDensity.current) { paddingDp.toPx() } //padding of tabLayout
    var sliderWidth by remember { mutableFloatStateOf(0f) }
    var sliderHeight by remember { mutableFloatStateOf(0f) }
    val offsetSlider by animateFloatAsState(
        targetValue = sliderWidth * selectedTabIndex,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        finishedListener = {
        }, label = ""
    )

    Card(
        modifier = Modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(paddingDpCurrent)
        ) {
            //slider
            Card(
                modifier = Modifier
                    .offset { IntOffset(offsetSlider.toInt(), 0) }
                    .foregroundShadow(
                        shadowColorLightSlider,
                        shadowColorDarkSlider,
                        blurRadius,
                        lightSource,
                        offset,
                        cornerRadius,
                    ),
                shape = RoundedCornerShape(cornerRadius),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Box(
                    Modifier
                        .background(sliderColor)
                        .size(
                            (sliderWidth / LocalDensity.current.density).dp,
                            (sliderHeight / LocalDensity.current.density).dp
                        )
                )

            }
            //Tabs
            SubcomposeLayout(Modifier.fillMaxWidth()) { constraints ->
                val tabRowWidth = constraints.maxWidth
                val tabMeasurables = subcompose(TabSlots.Tabs, tabs)
                val tabCount = tabMeasurables.size
                val tabWidth = (tabRowWidth / tabCount)
                sliderWidth = tabWidth.toFloat()
                val tabPlaceables = tabMeasurables.map {
                    it.measure(constraints.copy(minWidth = tabWidth, maxWidth = tabWidth))
                }

                val tabRowHeight = tabPlaceables.maxByOrNull { it.height }?.height ?: 0
                sliderHeight = tabRowHeight.toFloat()
                val tabPositions = List(tabCount) { index ->
                    TabPosition(tabWidth.toDp() * index, tabWidth.toDp())
                }
                tabPositions.forEach {
                    Log.e(TAG, "tabPosition left:${it.left} right:${it.right} width:${it.width}")
                }

                layout(tabRowWidth, tabRowHeight) {
                    tabPlaceables.forEachIndexed { index, placeable ->
                        placeable.placeRelative(index * tabWidth, 0)
                    }

                }
            }
        }

    }

}

@Immutable
class TabPosition internal constructor(val left: Dp, val width: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width)"
    }
}

private enum class TabSlots {
    Tabs,
    Divider,
    Indicator
}

@Composable
fun ShadeTab(
    height: Dp = 38.dp,
    onSelected: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .height(height)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onSelected.invoke() }
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}