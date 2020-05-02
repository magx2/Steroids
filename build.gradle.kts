plugins {
    java
    id("org.shipkit.java") version "2.3.1"
}

group = "com.github.magx2.steroids"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.validation:validation-api:2.0.1.Final")

    // TEST
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("org.mockito:mockito-all:1.10.19")
    testImplementation("org.assertj:assertj-core:3.15.0")
}

tasks.test {
    useJUnitPlatform()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}