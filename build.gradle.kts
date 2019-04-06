import io.ebean.gradle.EnhancePluginExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("io.ebean:ebean-gradle-plugin:11.26.1")
  }
}

  repositories {
    mavenCentral()
  }

plugins {
  kotlin("jvm") version "1.3.21"
  id("org.springframework.boot") version "2.1.3.RELEASE"
}

apply(plugin = "io.spring.dependency-management")
apply(plugin = "io.ebean")

configure<EnhancePluginExtension> {
  debugLevel = 9
}

defaultTasks("clean", "build")

springBoot {
    mainClassName = "br.com.pintos.rest.ColetorApplicationKt"
}

dependencies {
  compile("io.ebean:ebean:11.28.1")
  compile("io.ebean:querybean-generator:11.19.1")
  compile("io.ebean:ebean-querybean:11.20.1")
  compile("io.ebean:ebean-annotation:4.2")
  compile("io.ebean.tools:finder-generator:4.2.2")

  compile ("ch.qos.logback:logback-classic:1.2.3")
  compile ("org.slf4j:slf4j-api:1.7.25")
  compile ("org.slf4j:jul-to-slf4j:1.7.25")

  compile("mysql:mysql-connector-java:5.1.47")
  compile("org.sql2o:sql2o:1.5.4")
  //Printer Cups
  compile("org.cups4j:cups4j:0.7.6")

  implementation(kotlin("stdlib-jdk8"))
  // logging
  // currently we are logging through the SLF4J API to LogBack. See logback.xml file for the logger configuration
  compile("ch.qos.logback:logback-classic:1.2.3")
  compile("org.slf4j:slf4j-api:1.7.25")
  // Kotlin
  compile(kotlin("stdlib-jdk8"))
  compile(kotlin("reflect"))

  //REST
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-tomcat")
  //implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.session:spring-session-jdbc")
  implementation("com.h2database:h2:1.4.197")
  //implementation("com.google.code.gson:gson:2.8.5")

  //Utils
  compile("org.imgscalr:imgscalr-lib:4.2")

}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = "1.8"
}