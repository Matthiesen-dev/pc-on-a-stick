import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

val resolvedModVersion = providers.environmentVariable("RELEASE_VERSION")
    .orElse(providers.gradleProperty("mod_version"))
    .orNull
    ?: error("Set RELEASE_VERSION or mod_version (e.g. in gradle.properties / -Pmod_version=...)")

group = property("maven_group").toString()
version = resolvedModVersion

repositories {
    mavenCentral  {
        content {
            excludeGroup("dev.matthiesen")
        }
    }
    maven("https://artefacts.cobblemon.com/releases/")
    maven("https://repo.spongepowered.org/repository/maven-public")
    maven("https://maven.matthiesen.dev/releases") {
        name = "devMatthiesenMavenReleases"
    }
    maven("https://maven.matthiesen.dev/snapshots") {
        name = "devMatthiesenMavenSnapshots"
    }
    maven("https://api.modrinth.com/maven")
    maven("https://maven.theillusivec4.top/")
    // for development builds
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots1"
        mavenContent {
            snapshotsOnly()
            excludeGroup("dev.matthiesen")
        }
    }
    maven("https://central.sonatype.com/repository/maven-snapshots/") {
        name = "central-snapshots"
        mavenContent {
            snapshotsOnly()
            excludeGroup("dev.matthiesen")
        }
    }
    maven("https://maven.impactdev.net/repository/development/") {
        content {
            excludeGroup("dev.matthiesen")
        }
    }
}

dependencies {
    add("testImplementation", "org.junit.jupiter:junit-jupiter-api:5.1.0")
    add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:5.1.0")
}

configure<JavaPluginExtension> {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}


