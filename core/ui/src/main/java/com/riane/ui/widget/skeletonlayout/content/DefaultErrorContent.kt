//package com.riane.ui.widget.skeletonlayout.content
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.riane.ui.R
//
//@Composable
//fun DefaultErrorContent(
//    modifier: Modifier,
//    errorMsg: String = "There was a minor issue. Please try again later.",
//    onClickRetry: () -> Unit
//) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(horizontal = 32F.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(R.drawable.ic_empty_image),
//            contentDescription = errorMsg
//        )
//        Spacer(modifier = Modifier.height(16F.dp))
//        Text(
//            text = errorMsg,
//            style = MaterialTheme.typography.labelMedium,
//            color = colorResource(R.color.color_BBBBBB)
//        )
//        Spacer(modifier = Modifier.height(24F.dp))
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentWidth()
//                .width(100F.dp)
//                .height(36F.dp)
//                .background(
//                    color = colorResource(id = R.color.color_C8FA76),
//                    shape = RoundedCornerShape(50F.dp),
//                )
//                .clickableWithNoRipple(onClickRetry),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Try again",
//                fontSize = 12F.sp,
//                fontWeight = FontWeight.Bold,
//                color = colorResource(id = R.color.color_333333)
//            )
//        }
//        Spacer(modifier = Modifier.height(88F.dp))
//    }
//}