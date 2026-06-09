import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.task.RemapSourcesJarTask
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.named

plugins {
    id("matthiesen.project-conventions")
}

pluginManager.withPlugin("dev.architectury.loom") {
    configure<LoomGradleExtensionAPI> {
        enableTransitiveAccessWideners.set(true)
        silentMojangMappingsLicense()
    }

    tasks.named<RemapSourcesJarTask>("remapSourcesJar") {
        archiveBaseName.set("${rootProject.property("archives_base_name")}-${project.name}")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("sources")
    }
}

