package com.riane.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.cancelRipperClick(onClick: () -> Unit) = this.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)

fun Modifier.clickableWithNoRipple(onClick: (() -> Unit)?): Modifier {
    if (onClick == null) {
        return Modifier
    }
    return composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {
                if (ClickUtils.isFastClick()) {
                    return@clickable
                }
                onClick.invoke()
            }
        )
    }
}