plugins {
    kotlin("js") version "1.6.20"
}

group = "me.max"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.0.0-pre.331-kotlin-1.6.20")

    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.0.0-pre.331-kotlin-1.6.20")

    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:18.0.0-pre.331-kotlin-1.6.20")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.331-kotlin-1.6.20")

    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.331-kotlin-1.6.20")

    implementation("org.jetbrains.kotlin-wrappers:kotlin-csstype:3.0.11-pre.331-kotlin-1.6.20")

}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true

            }
        }
    }
}