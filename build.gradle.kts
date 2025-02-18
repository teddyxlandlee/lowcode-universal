plugins {
	java
	`maven-publish`
}

group = "icu.7c7.mcdev"
version = project.ext["project_version"]

java.withSourcesJar()
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

tasks.jar {
	from("licenses")
}

tasks.withType(JavaCompile::class).configureEach {
	options.encoding = "UTF-8"
	val targetVersion = 8
	if (JavaVersion.current().isJava9Compatible())
		options.release.set(targetVersion)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            // artifact(javadocJar.get())
            // artifact(tasks.named("sourcesJar"))
            pom {
                name.set("lowcode-universal")
                url.set("https://github.com/teddyxlandlee/lowcode-universal")
            }
        }
    }
    repositories {
        maven {
            name = "localRepository"
            url = uri(layout.buildDirectory.dir("maven-repo"))
        }
    }
}

