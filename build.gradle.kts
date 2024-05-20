plugins {
    id("java")
}

group = "grovox.lerning"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.5")
    testImplementation("org.mockito:mockito-core:5.12.0")
    implementation("org.springframework:spring-webmvc:6.1.7")
    compileOnly("org.projectlombok:lombok:1.18.32")

}

tasks.test {
    useJUnitPlatform()
}