plugins {
    id("java")
    id("java-library")
    kotlin("jvm") version ("2.2.20")

    id("dev.architectury.loom") version ("1.11-SNAPSHOT")
    id("architectury-plugin") version ("3.4-SNAPSHOT")
}

group = "dev.matthiesen"
version = "1.2.0"

architectury {
    platformSetupLoomIde()
    neoForge()
}

loom {
    silentMojangMappingsLicense()
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://maven.neoforged.net/releases/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
    maven("https://artefacts.cobblemon.com/releases/")
    maven("https://api.modrinth.com/maven")
    maven("https://maven.theillusivec4.top/")
}

val minecraftVersion = providers.gradleProperty("minecraft_version").get()
val neoForgeVersion = providers.gradleProperty("neoforge_version").get()
val cobblemonVersion = providers.gradleProperty("cobblemon_version").get()
val kotlinForForgeVersion = providers.gradleProperty("kotlinforforge_version").get()
val craftingStickVersion = providers.gradleProperty("crafting_stick_version").get()
val curiosVersion = providers.gradleProperty("curios_version").get()

dependencies {
    minecraft("net.minecraft:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
    neoForge("net.neoforged:neoforge:${neoForgeVersion}")

    modCompileOnly("com.cobblemon:mod:${cobblemonVersion}")
    modImplementation("com.cobblemon:neoforge:${cobblemonVersion}")
    //Needed for cobblemon
    implementation("thedarkcolour:kotlinforforge-neoforge:${kotlinForForgeVersion}") {
        exclude("net.neoforged.fancymodloader", "loader")
    }

    //Crafting on a Stick mod
    modImplementation("maven.modrinth:crafting-on-a-stick:${craftingStickVersion}")

    //Curios
    compileOnly("top.theillusivec4.curios:curios-neoforge:${curiosVersion}:api")
    runtimeOnly("top.theillusivec4.curios:curios-neoforge:${curiosVersion}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks {
    test {
        useJUnitPlatform()
    }

    processResources {
        inputs.property("version", project.version)

        filesMatching("META-INF/neoforge.mods.toml") {
            expand(project.properties)
        }
    }

    java {
        withSourcesJar()
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    compileJava {
        options.release = 21
    }
}
