Classroom-style Spring Boot project

This submission follows the classroom requirements:

- Manual getters/setters (no Lombok)
- `Long` primary keys with `@GeneratedValue(strategy = GenerationType.IDENTITY)`
- H2 in-memory datasource (see `src/main/resources/application.properties`)
- Spring Data JPA repositories under `com.cse.project.repository`

Build & run (Windows PowerShell):

```powershell
$env:JAVA_HOME="C:\Users\User\.jdk\jdk-25"
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
& 'C:\Users\User\.maven\maven-3.9.15\bin\mvn.cmd' -DskipTests=false clean test
& 'C:\Users\User\.maven\maven-3.9.15\bin\mvn.cmd' spring-boot:run
```

Files included in the ZIP: `pom.xml`, `src/main/java/com/cse/project/**`, `src/main/resources/application.properties`, `README.md`.
