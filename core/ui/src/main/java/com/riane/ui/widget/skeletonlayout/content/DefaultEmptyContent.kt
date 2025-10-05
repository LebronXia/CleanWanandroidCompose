//package com.riane.ui.widget.skeletonlayout.content
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.riane.ui.R
//
//@Composable
//fun DefaultEmptyContent(
//    modifier: Modifier,
//    iconRes: Int,
//    msg: String
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(iconRes),
//            contentDescription = "Loading"
//        )
//        Spacer(modifier = Modifier.height(8F.dp))
//        Text(
//            text = msg,
//            style = MaterialTheme.typography.labelMedium,
//            color = colorResource(R.color.color_999999).copy(
//                alpha = .6F
//            ),
//            textAlign = TextAlign.Center
//        )
//        Spacer(modifier = Modifier.height(88F.dp))
//    }
//}