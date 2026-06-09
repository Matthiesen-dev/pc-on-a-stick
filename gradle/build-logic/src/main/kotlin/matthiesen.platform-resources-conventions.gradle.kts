import org.gradle.kotlin.dsl.named
import org.gradle.language.jvm.tasks.ProcessResources

plugins {
    id("matthiesen.minecraft-module-conventions")
}

tasks.named<ProcessResources>("processResources") {
    inputs.property("mod_id", project.property("mod_id").toString())
    inputs.property("version", project.version)
    inputs.property("mod_name", project.property("mod_name").toString())
    inputs.property("mod_description", project.property("mod_description").toString())
    inputs.property("mod_license", project.property("mod_license").toString())
    inputs.property("mod_author", project.property("mod_author").toString())
    inputs.property("github_url", project.property("github_url").toString())
    inputs.property("modrinth_url", project.property("modrinth_url").toString())
}

