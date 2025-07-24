package com.riane.cleanwanandroidcompose.ui

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.riane.cleanwanandroidcompose.BottomNavigationItem
import com.riane.cleanwanandroidcompose.MainAppState
import com.riane.cleanwanandroidcompose.R
import com.riane.ui.WhiteBackground
import com.riane.utils.clickableWithNoRipple

@Composable
fun AppBottomBar(navControl: NavHostController, mainAppState: MainAppState) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = WhiteBackground,
                shape = RoundedCornerShape(topStart = 12F.dp, topEnd = 12F.dp)
            ).navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val selected = false
        Log.d("xm", mainAppState.mainTopLevelDestinations.size.toString()?: "0")
        mainAppState.mainTopLevelDestinations
            .forEach { des ->
                BottomNavigationItem(des, mainAppState::navigateToMainTopLevelDestination,{
                }, isSelected = selected, Modifier.weight(1F))
            }

    }
}

@Composable
fun BottomNavigationItem(item: BottomNavigationItem,
                         navigateToTopLevelDestination: (BottomNavigationItem, (Boolean) -> Unit) -> Unit,
                         onClick: () -> Unit,
                         isSelected: Boolean,
                         modifier: Modifier = Modifier
                         ){

    Column(
        modifier = modifier.fillMaxHeight()
            .clickableWithNoRipple{
                navigateToTopLevelDestination(item, {})
           },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            AnimatedVisibilityEx(visible = isSelected) {
                Image(
                    modifier = Modifier.size(40F.dp),
                    painter = painterResource(id = item.actionIcon),
                    contentDescription = item.title,
                )
            }

            AnimatedVisibilityEx(visible = !isSelected) {

                Image(
                    modifier = Modifier.size(40F.dp),
                    painter = painterResource(id = item.unActiveIcon),
                    contentDescription = item.title
                )
            }
        }

        Text(
            text = item.title,
            fontSize = 10F.sp,
            color = if (isSelected){
                Color.Black
            } else {
                Color(0xFF666666)
            }
        )

    }
}


@Composable
internal fun AnimatedVisibilityEx(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter : EnterTransition = fadeIn() + scaleIn(),
    exit: ExitTransition = fadeOut() + scaleOut(),
    content: @Composable AnimatedVisibilityScope.() -> Unit) {

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enter,
        exit = exit,
        content = content
    );
}


