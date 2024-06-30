plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.ertools"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    /** Core **/
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    /** Discord **/
    implementation("net.dv8tion:JDA:5.0.0-beta.5")

    /** Serialization **/
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")

    /** Configuration **/
    implementation("com.typesafe:config:1.4.2")
    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")

    /** Logging **/
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
    implementation("org.slf4j:jul-to-slf4j:2.0.5")

    /** Tests **/
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

application {
    mainClass.set("com.ertools.FergolaBotApplicationKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = application.mainClass
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}