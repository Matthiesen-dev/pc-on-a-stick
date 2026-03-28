plugins {
    id("java")
    id("java-library")
    kotlin("jvm") version ("2.2.20")

    id("dev.architectury.loom") version ("1.11-SNAPSHOT")
    id("architectury-plugin") version ("3.4-SNAPSHOT")
}


group = "xyz.station48"
version = "1.0.0"

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
}

dependencies {
    minecraft("net.minecraft:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())
    neoForge("net.neoforged:neoforge:21.1.182")

    modCompileOnly("com.cobblemon:mod:1.7.3+1.21.1")
    modImplementation("com.cobblemon:neoforge:1.7.3+1.21.1")
    //Needed for cobblemon
    implementation("thedarkcolour:kotlinforforge-neoforge:5.10.0") {
        exclude("net.neoforged.fancymodloader", "loader")
    }

    //Crafting on a Stick mod
    modImplementation("maven.modrinth:crafting-on-a-stick:1.21.0.4")

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
