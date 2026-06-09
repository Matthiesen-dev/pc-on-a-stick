rootProject.name = "cobblemon-pc-on-a-stick"

pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.neoforged.net/releases/")
        gradlePluginPortal()
    }

    includeBuild("gradle/build-logic")
}
