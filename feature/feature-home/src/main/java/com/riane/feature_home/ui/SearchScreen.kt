package com.riane.feature_home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.annotation.Destination
import com.riane.utils.log.LogUtils
import org.w3c.dom.Text

@Composable
fun SearchScreen(content: String?, navCtrl: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()){
        Text(text = content?:"")
    }

}