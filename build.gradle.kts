plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.freedcamp"
version = "1.0-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.codeborne:selenide:7.9.3")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("io.qameta.allure:allure-junit5:2.29.0")
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.aeonbits.owner:owner:1.0.12")
    implementation ("org.jsoup:jsoup:1.17.2")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.aeonbits.owner:owner:1.0.12")
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")
    testImplementation("io.qameta.allure:allure-selenide:2.24.0")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.17")
}

allure {
    version.set("2.24.0")
    useJUnit5 {
        adapterVersion.set("2.24.0")
    }
}

tasks.test {
    useJUnitPlatform()
}
