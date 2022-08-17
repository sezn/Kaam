/**
 * buildSrc is the first module compiled during a Gradle build,
 * and its source artifacts are available throughout your entire build script
 */
object Versions {
    val versionName = "7.0.15"
    private val versionCodeBase = 70150 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 3

    const val COMPILE_SDK = 31
    const val TARGET_SDK = 30
    const val MIN_SDK = 21

    const val HILT = "2.43.2"
    const val COMPOSE = "1.2.1"
    const val COROUTINES = "1.6.3"
    const val LIFECYCLE = "2.5.0"
}