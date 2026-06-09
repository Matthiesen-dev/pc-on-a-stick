plugins {
    alias(libs.plugins.architectury.loom)
    alias(libs.plugins.architectury.plugin)
    id("matthiesen.platform-resources-conventions")
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://maven.neoforged.net/releases/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")

}

dependencies {
    minecraft(libs.minecraft.net)
    mappings(loom.officialMojangMappings())
    neoForge(libs.neoforge)

    modCompileOnly(libs.bundles.modCompileOnly)
    modImplementation(libs.bundles.modImplementation)
    modImplementation(libs.bundles.modImplementationNoTransitive) { isTransitive = false }
    implementation(libs.kotlinforforge) {
        exclude("net.neoforged.fancymodloader", "loader")
    }
    compileOnly(libs.bundles.compileOnly)
    compileOnly(variantOf(libs.curios.neoforge) { classifier("api") })
    runtimeOnly(libs.bundles.runtimeOnly)

    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

tasks {
    processResources {
        filesMatching("META-INF/neoforge.mods.toml") {
            expand(project.properties)
        }
        filesMatching("pack.mcmeta") {
            expand(project.properties)
        }
    }
}

tasks.register<Copy>("copyJars") {
    group = "build"
    description = "Copies JAR files from build to output directory"

    from("./build/libs/") {
        include("*.jar")
        exclude("*-dev-shadow.jar")
    }
    into("./output/")

    doFirst {
        delete(fileTree("./output/") {
            include("**/*")
        })
        file("./output/").mkdirs()
    }
}

