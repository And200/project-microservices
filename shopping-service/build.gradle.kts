plugins {
	java
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "co.cun.edu"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.5"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
	// https://mvnrepository.com/artifact/com.sun.jersey.contribs/jersey-apache-client4
	implementation("com.sun.jersey.contribs:jersey-apache-client4:1.19.4")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-sleuth
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.6")
	// https://mvnrepository.com/artifact/javax.validation/validation-api
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("de.codecentric:spring-boot-admin-starter-client:2.5.2")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop
	implementation("org.springframework.boot:spring-boot-starter-aop:3.0.2")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-circuitbreaker-resilience4j
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.0.0")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
