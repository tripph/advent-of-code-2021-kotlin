plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}


tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }
    dependencies {
        testImplementation("org.junit.platform:")
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
