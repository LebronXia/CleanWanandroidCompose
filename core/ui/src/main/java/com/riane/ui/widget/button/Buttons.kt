package com.riane.ui.widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riane.ui.CleanWanAndroidTheme

@Composable
fun LabelTextButton(
    text: String,
    modifier: Modifier = Modifier,
    isSelect: Boolean = true,
    specTextColor: Color? = null,
    cornerValue: Dp = 25.dp / 2,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {

    Text(
        text = "", modifier = modifier
            .height(25.dp)
            .clip(
                shape = RoundedCornerShape(cornerValue)
            )
            .background(color = if(isSelect && !isLoading) CleanWanAndroidTheme.colors.theme else CleanWanAndroidTheme.colors.title)
            .padding(horizontal = 18.dp, vertical = 3.dp)
            .combinedClickable(enabled = !isLoading, onClick = onClick, onLongClick = onLongClick),

        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        color = specTextColor ?: if (isSelect) Color.White else CleanWanAndroidTheme.colors.body,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

}