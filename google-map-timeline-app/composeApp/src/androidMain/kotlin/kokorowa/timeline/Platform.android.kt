package kokorowa.timeline

actual fun getPlatform(): KotlinPlatform = KotlinPlatform.Android

actual fun getHttpRequest(): HttpRequest = AndroidHttpRequest()