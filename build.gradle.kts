plugins {
    id("java")
}

group = "com.lexqurious"
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

    //@TODO HTTP 클라이언트
    //@TODO JSON 파싱 라이브러리
}

tasks.test {
    useJUnitPlatform()
}