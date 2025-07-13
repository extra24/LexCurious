plugins {
    id("java")
}

group = "com.lexcurious"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17) // JDK 17 설정
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // JSON Parsing
    implementation("com.google.code.gson:gson:2.10.1")

    // HTTP 클라이언트
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

}

tasks.test {
    useJUnitPlatform()
}