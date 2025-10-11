package com.riane.feature_home.danmu

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DanmuScreen(viewModel: DanmuViewModel = DanmuViewModel()) {

    val danmus by viewModel.danmus.collectAsState()
    var inputText by remember { mutableStateOf("") }
    val textLayout = rememberTextMeasurer()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            danmus.forEach { item ->
                val text = textLayout.measure(
                    text = AnnotatedString(item.text), style = TextStyle(
                        color = item.color,
                        fontSize = item.fontSize,
                    )
                )
                drawText(text, topLeft = Offset(size.width * item.offsetX, size.height * item.offsetY))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = inputText, onValueChange = { inputText = it }, modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.sendLocalDanmu(inputText)
                    inputText = ""
                }) {
                Text("发送弹幕")
            }
        }
    }

}