# A dockerized version of Jenkins in the Gradle

An example [Job DSL](https://github.com/jenkinsci/job-dsl-plugin) project that uses Gradle for building and testing and
docker for an easy deployment of your Jenkins. 

**Attention**: Currently some of the classes, tests and mechanics need some "love" and attempts to check if everything
is working as assumed. This project is currently in an early pre-alpha phase. Every contribution is welcome so feel 
free to support me :-)

# Script Examples

* [Example 1](jobs/exampleJob.groovy) - Simple "Hello World" Job that get`s seeded


## Testing

To execute the existing tests use:
```
./gradlew test` runs the specs.
```

JobScriptsSpec]
will loop through all DSL files and make sure they don't throw any exceptions when processed. All XML output files are written to `build/debug-xml`. 
This can be useful if you want to inspect the generated XML before check-in.

## Seed Job

You can create the example seed job via the Rest API Runner (see below) using the pattern `jobs/seed.groovy`.

Or manually create a job with the same structure:

* Invoke Gradle script
   * Use Gradle Wrapper: `true`
   * Tasks: `clean test`
* Process Job DSLs
   * DSL Scripts: `jobs/**/*Jobs.groovy`
   * Additional classpath: `src/main/groovy`
* Publish JUnit test result report
   * Test report XMLs: `build/test-results/**/*.xml`

Note that starting with Job DSL 1.60 the "Additional classpath" setting is not available when
[Job DSL script security](https://github.com/jenkinsci/job-dsl-plugin/wiki/Script-Security) is enabled.

## REST API Runner

Note: the REST API Runner does not work with [Automatically Generated DSL](https://github.com/jenkinsci/job-dsl-plugin/wiki/Automatically-Generated-DSL). 

A gradle task is configured that can be used to create/update jobs via the Jenkins REST API, if desired. Normally
a seed job is used to keep jobs in sync with the DSL, but this runner might be useful if you'd rather process the
DSL outside of the Jenkins environment or if you want to create the seed job from a DSL script.

```./gradlew rest -Dpattern=<pattern> -DbaseUrl=<baseUrl> [-Dusername=<username>] [-Dpassword=<password>]```

* `pattern` - ant-style path pattern of files to include
* `baseUrl` - base URL of Jenkins server
* `username` - Jenkins username, if secured
* `password` - Jenkins password or token, if secured


## Getting started using Image from Docker Hub

TODO

## File structure

    .
    ├── jobs                            # DSL script files for the job(s)
    ├── resources                       # resources for DSL scripts
    ├── src
    │   ├── main
    │   │   ├── groovy.de.benjitrapp             
    │   │   │     └── defaults          # Useful default methods for the creation of jobs/views
    │   │   │     └── rest              
    │   │   └── resources
    │   │       └── idea.gdsl           # IDE support for IDEA
    │   └── test
    │       └── groovy ..               # specs
    ├── views                           # DSL script files for the view(s)
    └── build.gradle                    # build file
    
## Links
API für die Jenkins-Job-DSL:         https://jenkinsci.github.io/job-dsl-plugin/#