plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.feira"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	maven(url = "https://jitpack.io")
}

val postgreeSQLVersion = "42.6.0"
val flywayVersion = "9.2.2"
val testContainersVersion = "3.2.2"
val testContainersJunitVersion = "1.19.1"
val postgresSqlTestContainersVersion = "1.19.1"
val mockkVersion = "1.13.3"
val springMockkVersion = "4.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.postgresql:postgresql:$postgreeSQLVersion")
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.auth0:java-jwt:4.4.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.testcontainers:postgresql:$postgresSqlTestContainersVersion")
	testImplementation("org.springframework.boot:spring-boot-testcontainers:$testContainersVersion")
	testImplementation("org.testcontainers:junit-jupiter:$testContainersJunitVersion")
	testImplementation("io.mockk:mockk:$mockkVersion")
	testImplementation("com.ninja-squad:springmockk:$springMockkVersion")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
