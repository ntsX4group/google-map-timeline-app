package kokorowa.timeline

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform