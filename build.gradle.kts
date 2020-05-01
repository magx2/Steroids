plugins {
    java
    id("org.shipkit.java") version "2.3.1"
}

group = "com.github.magx2.steroids"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}