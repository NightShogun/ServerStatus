import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.0")
}

group = "xyz.skylar11d.minecraftp.serverstatus"
version = "1.0"

tasks.compileJava {
    options.encoding = "UTF-8"
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-releases/")
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.11.0")
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("com.github.retrooper.packetevents:spigot:2.3.0")
    compileOnly("org.reflections:reflections:0.9.12")
}

tasks.withType<ProcessResources> {
    from(sourceSets.main.get().resources) {
        include("plugin.yml")
        filter { line -> line.replace("%project_version%", project.version.toString()) }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("ServerStatus")
    archiveVersion.set("${project.version}")
    archiveClassifier.set("SNAPSHOT") // This classifier is added to the JAR name
    destinationDirectory.set(file("/home/skylar/Desktop/minecraft-net/lounge/plugins"))

}

tasks.named("assemble") { //performs only the production tasks not the test unlike the build tasks aggregator
    dependsOn(tasks.named("shadowJar"))
}


apply(plugin = "java")
apply(plugin = "com.github.johnrengelman.shadow")