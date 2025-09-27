plugins {
    id("java")
}

group = "br.edu.puc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mongodb:mongodb-driver-sync")
    implementation(platform("org.mongodb:mongodb-driver-bom:5.6.0"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}