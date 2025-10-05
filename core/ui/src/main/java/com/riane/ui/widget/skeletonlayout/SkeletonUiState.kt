package com.riane.ui.widget.skeletonlayout

sealed interface SkeletonUiState {

    data object Hide: SkeletonUiState
    data object Show: SkeletonUiState
    data class Empty(
        val iconResId: Int,
        val msg: String
    ): SkeletonUiState
    data class Error(
        val errorMsg: String
    ): SkeletonUiState

}