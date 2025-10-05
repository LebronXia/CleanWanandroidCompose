//package com.riane.ui.widget.skeletonlayout.content
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.dp
//import com.riane.ui.R
//
//@Composable
//fun MaterialLoadingContent(
//    modifier: Modifier = Modifier,
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.size(30.dp),
//            color = colorResource(id = R.color.color_C8FA76),
//            strokeWidth = 4F.dp
//        )
//        Spacer(modifier = Modifier.height(16F.dp))
//        Text(
//            text = "Loading",
//            style = MaterialTheme.typography.labelMedium,
//            color = colorResource(R.color.color_BBBBBB)
//        )
//        Spacer(modifier = Modifier.height(88F.dp))
//    }
//}