group = "com.spacexlab.glib"
version = "0.1.0"


plugins {
    java
    kotlin("jvm") version "1.5.21"


}


repositories {
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib"))
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.3.61")
    testImplementation("junit", "junit", "4.12")

}


