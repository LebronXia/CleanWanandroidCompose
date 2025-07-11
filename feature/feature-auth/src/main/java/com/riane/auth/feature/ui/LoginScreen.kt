package com.riane.auth.feature.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.riane.auth.feature.viewmodel.AuthViewModel
import com.riane.feature.auth.R
import com.riane.ui.PurpleGrey40
import com.riane.ui.color_white_transparent_1A
import com.riane.ui.component.LoginEditView

@Composable
internal fun LoginScreen(
    onTopicClick: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var medicalNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //管理界面焦点（Focus）的全局对象，
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {


    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            //填表单区域
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                LoginEditView(
                    text = medicalNumber,
                    onValueChanged = {
                        medicalNumber = it.trim()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = color_white_transparent_1A, shape = RoundedCornerShape(16.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                    hintText = "请输入手机号码",
                    startIcon = R.drawable.icon_user,
                    iconSpacing = 16.dp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "输入密码",
                    icon = Icons.Default.Lock,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }

            Button(
                onClick = {
                    Log.d("login", "点击登录$medicalNumber + $password")
                    viewModel.login(medicalNumber, password)
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(

                )
            ) {

                Text("开始登录", style = MaterialTheme.typography.labelLarge)
            }

        }

    }

}


//VisualTransformation 应用输入框的显示模式
//KeyboardOptions 设置键盘类型
//ImageVector 矢量图
@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val borderColor = if (value.isNotEmpty())
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldViewPreView() {
    var text by remember { mutableStateOf("") }
    LoginEditView(
        text = text,
        onValueChanged = {
            text = it.trim()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 20.dp, end = 16.dp)
            .height(50.dp)
            .background(color = color_white_transparent_1A, shape = RoundedCornerShape(16.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
        hintText = "请输入手机号码",
        startIcon = R.drawable.icon_user,
        iconSpacing = 16.dp,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )

}





