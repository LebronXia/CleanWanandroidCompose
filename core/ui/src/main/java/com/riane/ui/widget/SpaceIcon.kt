package com.riane.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.riane.ui.CleanWanAndroidTheme
import com.riane.ui.R

@Composable
fun TimerIcon(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "",
        tint = CleanWanAndroidTheme.colors.body,
        modifier = modifier
            .width(15.dp)
            .height(15.dp)
            .clip(RoundedCornerShape(15.dp / 2))
    )
}

@Composable
fun FavouriteIcon(
    modifier: Modifier = Modifier,
    isFavourite: Boolean = false,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {

    Icon(
        imageVector = if (isFavourite && !isLoading) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = null,
        tint = if (isFavourite && !isLoading) CleanWanAndroidTheme.colors.theme else CleanWanAndroidTheme.colors.body,
        modifier = modifier
            .width(25.dp)
            .height(25.dp)
            .clickable(enabled = !isLoading) { onClick() }
            .pointerInteropFilter { false }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HotIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_hot),
                contentDescription = null,
        tint = CleanWanAndroidTheme.colors.icon,
        modifier = modifier
            .size(20.dp)
            .pointerInteropFilter { false }
    )

}
