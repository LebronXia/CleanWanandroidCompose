职责：统一管理导航路由和参数传递
代码示例：
kotlin
// 路由定义
sealed class Route(val path: String) {
object Home : Route("home")
object Profile : Route("profile/{userId}") {
fun createRoute(userId: String) = "profile/$userId"
}
}
// 导航参数解析
fun NavController.navigateToProfile(userId: String) {
navigate(Route.Profile.createRoute(userId))
}