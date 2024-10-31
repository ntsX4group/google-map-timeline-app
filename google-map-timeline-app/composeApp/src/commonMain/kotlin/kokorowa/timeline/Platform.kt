package kokorowa.timeline

interface Platform {
    val name: String
}

interface HttpRequest {
    fun call()
}

enum class KotlinPlatform {
    Android, IOS
}

expect fun getPlatform(): KotlinPlatform

expect fun getHttpRequest(): HttpRequest