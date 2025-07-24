plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.minesweeper"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.minesweeper.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }

    // Optional: include class files from dependencies (if not using shadowJar)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}