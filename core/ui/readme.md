core-ui/
├── src/main/kotlin/
│   ├── theme/            # Material 主题配置
│   ├── component/        # 通用组件（按钮、卡片、对话框）
│   └── extension/        # Compose/View 扩展函数
代码示例：
kotlin
// 主题配置
object AppTheme {
val Colors
@Composable get() = MaterialTheme.colorScheme.copy(
primary = Color(0xFF6200EE),
secondary = Color(0xFF03DAC6)
)
}
// 通用加载组件
@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
CircularProgressIndicator(
modifier = modifier,
color = MaterialTheme.colorScheme.primary
)
}
// 错误提示组件
@Composable
fun ErrorMessage(text: String?) {
Text(
text = text ?: "Unknown error",
color = MaterialTheme.colorScheme.error
)
}