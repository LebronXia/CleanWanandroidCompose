package com.riane.feature_home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riane.domian_home.model.ArticleBean
import com.riane.ui.CleanWanAndroidTheme
import com.riane.ui.component.TagView
import com.riane.ui.widget.FavouriteIcon
import com.riane.ui.widget.HotIcon
import com.riane.ui.widget.TimerIcon
import com.riane.ui.widget.button.LabelTextButton
import com.riane.ui.widget.text.MediumTitle
import com.riane.ui.widget.text.MiniTitle
import com.riane.ui.widget.text.TextContent

@Composable
fun MultiStateItemView(
    modifier: Modifier = Modifier,
    data: ArticleBean,
    isTop: Boolean = true,
    onSelected: () -> Unit = {},
    onCollectClick: (articleId: Int) -> Unit = {},
    onUserClick: (userId: Int) -> Unit = {},
    isLoading: Boolean = false
) {

    Card(
        modifier = modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable(enabled = isLoading) {
                onSelected.invoke()
            },
        colors = CardDefaults.cardColors(
            containerColor = CleanWanAndroidTheme.colors.background
        ),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row {
                    Text(
                        text = getFirstCharFromName(data),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .clip(RoundedCornerShape(20.dp / 2))
                            .background(color = CleanWanAndroidTheme.colors.title),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    val titleModifier = if(isLoading) Modifier.width(80.dp) else Modifier.wrapContentWidth()
                    MediumTitle(
                        title = getAuthorName(data),
                        modifier = titleModifier.padding(start = 5.dp),
                        isLoading = isLoading
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                    if (isTop) {
                        HotIcon()
                    }
                    if (data.fresh) {
                        TagView(
                            tagText = "最新",
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .align(Alignment.Bottom)
                        )
                    }
                }

                Row {
                    TimerIcon(
                        modifier = Modifier.padding(end = if(isLoading) 5.dp else 0.dp),
                        isLoading = isLoading)
                    val dateModifier = if (isLoading) Modifier.width(80.dp) else Modifier.wrapContentWidth()
                    MiniTitle(text = "", modifier = dateModifier, isLoading = isLoading)
                }

            }

            TextContent(text = data.title ?:"", maxLines = 3, modifier = Modifier.fillMaxWidth().wrapContentHeight()
                .padding(top = 10.dp, bottom = 20.dp), isLoading = isLoading
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.wrapContentWidth()) {
                    LabelTextButton(
                        text = data.superChapterName ?: "热门",
                        isLoading = isLoading
                    )

                    Spacer(modifier = Modifier.width(5.dp))
                    LabelTextButton(
                        text = data.chapterName ?: "android",
                        isLoading = isLoading
                    )
                }

                FavouriteIcon(
                    isFavourite = data.collect,
                    onClick = {
                        onCollectClick.invoke(data.id)
                    },
                    isLoading = isLoading
                )
            }
            //

        }
    }
}

fun getAuthorName(data: ArticleBean?): String {
    return if (data?.shareUser.isNullOrEmpty()) {
        data?.author ?: ""
    } else {
        data?.shareUser ?: ""
    }
}

fun getFirstCharFromName(data: ArticleBean?): String {
    val author = getAuthorName(data)
    return if (author.isNotEmpty()) author.trim().substring(0, 1) else "?"
}

