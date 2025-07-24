package com.riane.featture_profile.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.riane.featture_profile.NestedScrollMeState
import com.riane.feature.profile.R
import com.riane.ui.CleanWanAndroidTheme
import com.riane.ui.Red
import com.riane.ui.WhiteBackground
import com.riane.ui.component.CommonRefreshState
import com.riane.ui.component.WanAndroidTabRow
import com.riane.ui.component.rememberCommonRefreshState
import com.riane.utils.cancelRipperClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun ProfileScreen() {

    Box(

        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Text("我的")
    }
}

@Composable
private fun NestedScrollMe() {
    val randomColor = Color.Blue

//    SubcomposeLayout(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Green)
//    ) { constraints ->
//
//    }

}

@Composable
private fun MeToolBar(randomColor: Color) {
    val fraction = remember {
        mutableFloatStateOf(
            0f
        )
    }
    val headImageBottom = with(LocalDensity.current) {
        96.dp.toPx()
    }

    val dynamicColor = remember {
        derivedStateOf { randomColor.copy(alpha = fraction.floatValue) }
    }

    val offsetY by animateFloatAsState(
        targetValue = if (fraction.floatValue == 1f) 0f else with(LocalDensity.current) { 50.dp.toPx() },
        label = "SmallHeadImgAnimate"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(dynamicColor.value)
            .windowInsetsPadding(WindowInsets.statusBars)
            .clipToBounds()
    ) {

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .alpha(1f),
            onClick =
            clickMenu()
        ) {

            Icon(
                imageVector = Icons.Filled.Menu, contentDescription = "menu",
                tint = Color.White
            )
        }

        IconButton(modifier = Modifier
            .align(Alignment.CenterEnd)
            .alpha(1f),

            onClick = {
                clickMenu()
            }) {
            Icon(
                imageVector = Icons.Filled.Share, contentDescription = "share",
                tint = Color.White
            )
        }

        AsyncImage(
            model = R.drawable.icon_hanbao,
            contentDescription = "smallHeadImg",
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    translationY = offsetY
                }

                .size(24.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(1.dp, Color.White, RoundedCornerShape(24.dp))
        )
    }
}

@Composable
private fun RefreshContent(
    coroutineScope: CoroutineScope,
    state: NestedScrollMeState,
    selectedIndex: Int,
    updateSelected: (Int) -> Unit,
    isSearchMode: Boolean
){

    val refreshState = rememberCommonRefreshState()
}

@Composable
private fun MeTabContent(randomColor: Color, topSpace: Dp, bottomSpace: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(0f to Color.Transparent, 0.7f to randomColor))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp + topSpace,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 6.dp + bottomSpace
                )
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    AsyncImage(
                        model = R.drawable.icon_hanbao,
                        contentDescription = "haedImg",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(80.dp))
                            .border(2.dp, Color.White, RoundedCornerShape(80.dp))
                    )

                    Icon(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(24.dp)
                            .background(Color.Black, RoundedCornerShape(24.dp)),
                        tint = Color(0xFFFFEB3B),
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "share"
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Loren",
                        color = Color.White,
                        style = CleanWanAndroidTheme.textStyle.titleLarge
                    )
                    Text(
                        text = "小红书：123456789",
                        color = Color.LightGray,
                        style = CleanWanAndroidTheme.textStyle.bodySmall
                    )

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "小白日常分享",
                color = Color.White,
                style = CleanWanAndroidTheme.textStyle.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                TextAndNumItem(13, "关注")
                Spacer(modifier = Modifier.width(16.dp))
                TextAndNumItem(13, "粉丝")
                TextAndNumItem(342, "获赞与收藏")
            }
        }

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MeFunctionBar(
    isSearchMode: Boolean,
    selectedIndex: Int
) {
    var userInput = remember { mutableStateOf("") }
    val width = LocalView.current.width
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val imeVisible = WindowInsets.isImeVisible
    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            //onSearchClick(false)
            focusManager.clearFocus()
        }
    }

    val searchAnimate by animateFloatAsState(
        targetValue = if (isSearchMode) 1f else 0f,
        label = "SearchAnimate"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(WhiteBackground, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
    ) {

        WanAndroidTabRow(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center),
            selectedIndex = selectedIndex,
            indicator = {
                Box(
                    modifier = Modifier
                        .size(20.dp, 3.dp)
                        .background(Red, RoundedCornerShape(3.dp))
                )
            }
        ) {
            stringArrayResource(id = R.array.me_tab).forEachIndexed { index, s ->
                Box(
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .padding(horizontal = 16.dp)
                        .cancelRipperClick {

                        },
                    contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = s,
                            style = CleanWanAndroidTheme.textStyle.titleSmall,
                            color = if (selectedIndex == index) Color.Black else Color(0xFF666666)
                        )
                }
            }
        }

        Icon(
            modifier = Modifier
                .cancelRipperClick {
                    focusRequester.requestFocus()
                }
                .align(Alignment.CenterEnd)
                .size(40.dp)
                .padding(10.dp),
            imageVector = Icons.Filled.Search,
            contentDescription = "search",
            tint = Color(0xFF666666)
        )

        //搜索输入框
        Row(
            modifier = Modifier
                .graphicsLayer {  //图形变换层 在组件上应用高性能的图形变换和视觉效果
                    alpha = searchAnimate
                    translationX = width - width * searchAnimate
                }
                .fillMaxSize()
                .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .weight(1f)
                    .background(Color(0xFFF5F6F7), RoundedCornerShape(32.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF666666)
                )

                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {

                        },
                    value = userInput.value,
                    cursorBrush = SolidColor(Color(0xFFFF2E4D)),
                    textStyle = CleanWanAndroidTheme.textStyle.bodySmall,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    onValueChange = {

                    }
                ){ innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart){
                        if (userInput.value.isBlank()){
                            Text(text = "搜索我的笔记/收藏/赞过", style = CleanWanAndroidTheme.textStyle.bodySmall, color = Color(0xFF666666))
                        }
                        innerTextField()
                    }

                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.cancelRipperClick {
                    focusManager.clearFocus()
                },
                text = "取消",
                style = CleanWanAndroidTheme.textStyle.titleSmall,
                color = Color(0xFF666666)
            )
        }
    }
}

@Composable
private fun MeViewPager(
    state: NestedScrollMeState,
    refreshState: CommonRefreshState,
    isSearchMode: Boolean
) {
    val pageCount = stringArrayResource(id = R.array.me_tab).size
    val pageState = rememberPagerState(initialPage = 0){
        pageCount
    }

    HorizontalPager(
        state = pageState,
        modifier = Modifier
            .fillMaxSize()
            .alpha(if (isSearchMode)  0f else 1f)
            .pointerInput(isSearchMode){
                if (isSearchMode){
                    awaitEachGesture {
                        while (true){
                            val event = awaitPointerEvent(PointerEventPass.Initial)
                            event.changes.forEach{ it.consume()}
                        }
                    }
                }
            }
    ) {
        LaunchedEffect(refreshState.isRefreshing) {
            if (refreshState.isRefreshing){
                delay(1000)
                refreshState.isRefreshing = false
            }
        }
        Text(
            text = "Me $it",
            modifier = Modifier.fillMaxSize()
                .scrollable(state = state.scrollState, Orientation.Vertical),
            textAlign = TextAlign.Center
        )

    }
}

//@Composable
//private fun MeNoteComponent(state: NestedScrollSource, refreshState: CommonRefreshState,){
//
//
//}

@Composable
private fun TextAndNumItem(num: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$num",
            color = Color.White,
            style = CleanWanAndroidTheme.textStyle.bodySmall,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, color = Color.White, style = CleanWanAndroidTheme.textStyle.bodySmall)
    }
}

fun clickMenu(): () -> Unit {
    return {}
}
