//package com.riane.ui.widget.skeletonlayout
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.Stable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import com.riane.ui.widget.skeletonlayout.content.DefaultEmptyContent
//import com.riane.ui.widget.skeletonlayout.content.DefaultErrorContent
//import com.riane.ui.widget.skeletonlayout.content.MaterialLoadingContent
//import com.riane.ui.R
//
//@Composable
//fun SkeletonLayout(
//    modifier: Modifier = Modifier,
//    state: SkeletonState,
//    defaultLoadingModifier: Modifier = Modifier,
//    defaultEmptyModifier: Modifier = Modifier,
//    defaultErrorModifier: Modifier = Modifier,
//    contentAlignment: Alignment = Alignment.TopCenter,
//    defaultErrorOnRetry : () -> Unit = { },
//    loadingContent: @Composable () -> Unit = { MaterialLoadingContent(defaultLoadingModifier) },
//    errorContent: @Composable (String) -> Unit = { errorMsg ->
//        DefaultErrorContent(
//            modifier = defaultErrorModifier,
//            errorMsg = errorMsg,
//            onClickRetry = defaultErrorOnRetry
//        )
//    },
//    emptyContent: @Composable (Int, String) -> Unit = { iconResId, emptyMsg ->
//        DefaultEmptyContent(
//            modifier = defaultEmptyModifier,
//            iconRes = iconResId,
//            msg = emptyMsg
//        )
//    },
//    content: @Composable () -> Unit
//) {
//    Box(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = contentAlignment
//    ) {
//        when (val uiState = state.skeletonUiState.value) {
//            is SkeletonUiState.Show -> loadingContent()
//            is SkeletonUiState.Empty -> emptyContent(uiState.iconResId, uiState.msg)
//            is SkeletonUiState.Error -> errorContent(uiState.errorMsg)
//            else -> content()
//        }
//    }
//}
//
//data class SkeletonState(
//    @Stable val skeletonUiState: MutableState<SkeletonUiState>,
//) {
//
//    fun hide() {
//        skeletonUiState.value = SkeletonUiState.Hide
//    }
//
//    fun show() {
//        skeletonUiState.value = SkeletonUiState.Show
//    }
//
//    fun empty(
//        iconResId: Int = R.drawable.ic_search,//ic_empty_chats_image
//        msg: String = "Empty Content"
//    ) {
//        skeletonUiState.value = SkeletonUiState.Empty(iconResId, msg)
//    }
//
//    fun error(
//        errorMsg: String = "There was a minor issue. Please try again later."
//    ) {
//        skeletonUiState.value = SkeletonUiState.Error(errorMsg)
//    }
//
//}