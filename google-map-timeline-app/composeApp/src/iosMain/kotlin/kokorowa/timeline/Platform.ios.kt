package kokorowa.timeline


actual fun getPlatform(): KotlinPlatform = KotlinPlatform.IOS

actual fun getHttpRequest(): HttpRequest = IosHttpRequest()