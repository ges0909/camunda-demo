# Camunda demo

## Camunda Platform Initializr

1.[Camunda Platform Initializr](https://start.camunda.com/).

- [x] REST API
- [x] Webapps
  - [x] Spin (XML & JSON)
- [x] Assert
- [ ] Spring Boot Modules
  - [ ] Security
    - login with `user`/"Using generated security password"
  - [ ] Web

2. generate, download, unpack
3. Migrate maven project to gradle.

- see [Migrating Builds From Apache Maven](https://docs.gradle.org/current/userguide/migrating_from_maven.html)
- `gradle init`
- `rm pom.xml`

4. Migrate tests from Junit4 to Junit5.

- requires `test/resource/camunda.cfg.xml`

## Camunda Modeler Plugins

see [The best free Plugins for Camunda’s BPMN 2 Modeler](https://emsbach.medium.com/the-best-free-plugins-for-camundas-bpmn-2-modeler-14eee0c9fdd2)

- `cd %USERPROFILE%\AppData\Roaming\camunda-modeler\resources`
- `git clone https://github.com/rob2universe/plugins`

## Enable Groovy Scripting

build.gradle:

- add `implementation 'org.codehaus.groovy:groovy-all:3.0.9'`

## Open

- What is `camunda.cfg.xml` for?
