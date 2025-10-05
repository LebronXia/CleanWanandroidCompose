package com.riane.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.riane.ui.CleanWanAndroidTheme
import com.riane.ui.CleanWanandroidComposeTheme
import com.riane.ui.Purple40
import com.riane.ui.Purple80
import com.riane.ui.R
import com.riane.ui.widget.text.MiniTitle
import com.riane.utils.log.LogUtils

@Composable
fun TextTabBar(
    index: Int,
    tabTexts: List<TabTitle>,
    modifier: Modifier = Modifier,
    contentAlign: Alignment = Alignment.Center,
    bgColor: Color = Color.Blue,
    contentColor: Color = Color.White,
    onTabSelected: ((index: Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(bgColor)
            .horizontalScroll(state = rememberScrollState())
    ) {


        LogUtils.d("tabTexts size is:" + tabTexts.size)
        Row(
            modifier = Modifier.align(contentAlign)
        ) {
            tabTexts.forEachIndexed { i, tabTitle ->
                Text(
                    text = tabTitle.text,
                    fontSize = if (index == i) 20.sp else 15.sp,
                    fontWeight = if (index == i) FontWeight.SemiBold else FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp)
                        .clickable { onTabSelected?.invoke(i) },
                    color = contentColor
                )

            }

        }


    }

}

data class TabTitle(
    val index: Int,
    val text: String,
    val cachePosition: Int = 0,
    var selected: Boolean = false
)

@Composable
fun HomeSearchBar(
    onSearchClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(Purple80)
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(30.dp)
                .align(alignment = Alignment.CenterVertically)
                .weight(1f)
                .background(color = Purple40, shape = RoundedCornerShape(12.5.dp))
                .clickable{
                    onSearchClick()
                }

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "搜索",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            ) {

                Text(text = "搜索关键词以空格形式隔开", fontSize = 13.sp, color = Color.Black)

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun FilledTagPreview() {
    CleanWanandroidComposeTheme {
        HomeSearchBar(
            onSearchClick = {
                //RouteUtils.navTo(navCtrl, RouteName.ARTICLE_SEARCH + "/111")
            })
    }
}

@Composable
fun TagView(
    modifier: Modifier = Modifier,
    tagText: String,
    tagBgColor: Color = CleanWanAndroidTheme.colors.background,
    borderColor: Color = CleanWanAndroidTheme.colors.theme,
    tagTextColor: Color = CleanWanAndroidTheme.colors.body,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(color = tagBgColor)
            .clip(RoundedCornerShape(2.dp))
            .border(width = 1.dp, color = borderColor)

    ) {

        MiniTitle(
            text = tagText,
            color = tagTextColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    horizontal = 5.dp, vertical = 0.dp
                )
        )

    }

}