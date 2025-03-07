plugins {
    `java-library`
    `hypo-java`
    `hypo-module`
    `hypo-publish`
    `hypo-test-scenario`
}

hypoTest {
    testDataProject.set(projects.hypoAsm.hypoAsmTestData)
}

repositories {
    // for tests
    maven("https://maven.quiltmc.org/repository/release/")
}

dependencies {
    api(projects.hypoCore)
    api(libs.bundles.asm)

    testImplementation(projects.hypoTest)
}

tasks.compileTestJava {
    options.release.set(17)
}

tasks.jar {
    manifest {
        attributes(
            "Automatic-Module-Name" to "dev.denwav.hypo.asm"
        )
    }
}

hypoJava {
    javadocLibs.add(libs.annotations)
    javadocLibs.add(libs.errorprone.annotations)
    javadocLibs.addAll(libs.bundles.asm)
    javadocProjects.addAll(projects.hypoCore, projects.hypoModel)
}

hypoPublish {
    component.set(components.named("java"))
}
