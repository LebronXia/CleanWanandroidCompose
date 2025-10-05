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
//import androidx.compose.ui.unit.dp
//import com.riane.ui.R
//
//@Composable
//fun DefaultLoadingContent(
//    modifier: Modifier = Modifier,
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(R.drawable.ic_empty_image),
//            contentDescription = "Loading"
//        )
//        Spacer(modifier = Modifier.height(16F.dp))
//        Text(
//            text = "Loading...Please wait",
//            style = MaterialTheme.typography.labelMedium,
//            color = colorResource(R.color.color_BBBBBB)
//        )
//        Spacer(modifier = Modifier.height(88F.dp))
//    }
//}