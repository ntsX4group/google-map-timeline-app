package kokorowa.timeline


class LoginInfo {
    var userId: String = ""
    var password: String = ""
}

class LoginResult {

}
interface HttpRequest {

    fun sendTimeLine()

    fun validateLogin(login: LoginInfo) : LoginResult
}

enum class KotlinPlatform {
    Android, IOS
}

expect fun getPlatform(): KotlinPlatform

expect fun getHttpRequest(): HttpRequest