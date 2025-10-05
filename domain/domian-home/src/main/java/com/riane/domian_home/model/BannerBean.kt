package com.riane.domian_home.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerBean(
    var desc: String?,
    var id: Int,
    var imagePath: String?,
    var isVisible: Int,
    var order: Int,
    var title: String?,
    var type: Int,
    var url: String?
)