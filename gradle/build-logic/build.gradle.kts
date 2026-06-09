plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.architectury.dev/")
    maven("https://maven.fabricmc.net/")
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    implementation(libs.kotlin.gradle)
    implementation(libs.architectury.loom)
    implementation(libs.architectury.plugin)
    implementation(libs.shadow.gradle)
    implementation(libs.publish)
}

