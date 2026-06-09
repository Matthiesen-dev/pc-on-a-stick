import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.credentials.PasswordCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.authentication.http.BasicAuthentication
import org.gradle.kotlin.dsl.configure

plugins {
    id("com.vanniktech.maven.publish")
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "devMatthiesenMaven"
            url = uri(if (version.toString().endsWith("SNAPSHOT"))
                "https://maven.matthiesen.dev/snapshots"
            else "https://maven.matthiesen.dev/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

configure<MavenPublishBaseExtension> {
    signAllPublications()
    coordinates(
        project.group.toString(),
        "${rootProject.property("archives_base_name")}-${project.name}",
        project.version.toString()
    )

    pom {
        name.set(project.property("mod_name").toString())
        description.set(project.property("mod_description").toString())
        inceptionYear.set("2026")
        url.set(project.property("github_url").toString())
        licenses {
            license {
                name.set(project.property("mod_license").toString())
                url.set(project.property("mod_license_url").toString())
                distribution.set(project.property("mod_license_url").toString())
            }
        }
        developers {
            developer {
                id.set(project.property("mod_author_id").toString())
                name.set(project.property("mod_author").toString())
                url.set(project.property("mod_author_url").toString())
            }
        }
        scm {
            url.set(project.property("github_url").toString())
            connection.set("scm:git:git://${project.property("git_url").toString()}")
            developerConnection.set("scm:git:ssh://git@${project.property("git_url").toString()}")
        }
    }
}

