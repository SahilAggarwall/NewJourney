plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.2.1"
    java
    application
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.kafka:kafka-clients:3.3.1")
    implementation("org.apache.kafka:kafka-streams:3.3.1")
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.37")
    implementation("org.glassfish.jersey.inject:jersey-hk2:2.37")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.37")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.mongodb:mongodb-driver-sync:4.8.0")
    implementation("dev.morphia.morphia:core:1.6.1")
    implementation("com.google.dagger:dagger:2.16")// Adjust version as needed
    implementation("org.apache.avro:avro:1.10.2")
    implementation("org.json:json:20170516")
    kapt("com.google.dagger:dagger-compiler:2.16")

}
application {
    mainClass.set("com.fretron.ApplicationKt") // Adjust to your main class
}

sourceSets {
    main {
        java.srcDir("src/main/java")
    }
}