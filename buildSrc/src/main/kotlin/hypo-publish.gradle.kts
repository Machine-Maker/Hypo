plugins {
    signing
    `maven-publish`
}

val extension: HypoPublishingExtension = extensions.create("hypoPublish", HypoPublishingExtension::class)

publishing {
    publications {
        register<MavenPublication>("maven") {
            afterEvaluate {
                extension.component.orNull?.let { c ->
                    from(c)
                }
            }

            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            withoutBuildIdentifier()

            pom {
                val repoUrl = "https://github.com/DenWav/Hypo"

                name.set("Hypo")
                description.set("An extensible and pluggable Java bytecode analytical model")
                url.set(repoUrl)
                inceptionYear.set("2021")

                licenses {
                    license {
                        name.set("LGPL-3.0-only")
                        url.set("$repoUrl/blob/main/COPYING.lesser")
                        distribution.set("repo")
                    }
                }

                issueManagement {
                    system.set("GitHub")
                    url.set("$repoUrl/issues")
                }

                developers {
                    developer {
                        id.set("DenWav")
                        name.set("Kyle Wood")
                        email.set("kyle@denwav.dev")
                        url.set("https://github.com/DenWav")
                    }
                }

                scm {
                    url.set(repoUrl)
                    connection.set("scm:git:$repoUrl.git")
                    developerConnection.set(connection)
                }
            }
        }
    }
}

// Don't configure signing unless this is present
val sonatypeUsername = providers.gradleProperty("sonatypeUsername")
val sonatypePassword = providers.gradleProperty("sonatypePassword")

val gpgSigningKey = providers.environmentVariable("GPG_SIGNING_KEY")
val gpgPassphrase = providers.environmentVariable("GPG_PASSPHRASE")

if (sonatypeUsername.isPresent && sonatypePassword.isPresent) {
    signing {
        setRequired {
            !isSnapshot && gradle.taskGraph.hasTask("publishToSonatype")
        }

        if (gpgSigningKey.isPresent && gpgPassphrase.isPresent) {
            useInMemoryPgpKeys(gpgSigningKey.get(), gpgPassphrase.get())
        } else {
            useGpgCmd()
        }

        sign(publishing.publications["maven"])
    }
}
