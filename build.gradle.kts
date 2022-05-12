plugins {
    kotlin("jvm") version "1.5.10"
    id("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

sourceSets {
    create("componentTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val componentTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["componentTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.mockk:mockk:1.12.3")

    componentTestImplementation(sourceSets["test"].output)
    componentTestImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

val componentTestTask = tasks.create("componentTest", Test::class) {
    description = "Runs the component testes."
    group = "verification"

    testClassesDirs = sourceSets["componentTest"].output.classesDirs
    classpath = sourceSets["componentTest"].runtimeClasspath

    useJUnitPlatform()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    reports {
        xml.required
        html.required
    }

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "school/cesar/criptocorretora/entidades/**",
                    "school/cesar/criptocorretora/excecoes/**",
                    "school/cesar/criptocorretora/services/CompraService.kt"
                )
            }
        }))
    }
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = "0.85".toBigDecimal()
                counter = "LINE"
            }
            limit {
                minimum = "0.85".toBigDecimal()
                counter = "BRANCH"
            }
        }
    }

    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "school/cesar/criptocorretora/entidades/**",
                    "school/cesar/criptocorretora/excecoes/**",
                    "school/cesar/criptocorretora/services/CompraService.kt"
                )
            }
        }))
    }
}

tasks.test {
    finalizedBy(
        "jacocoTestReport",
        "jacocoTestCoverageVerification",
        "componentTest"
    )
}