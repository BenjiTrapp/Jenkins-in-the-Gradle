// If you want, you can define your seed job in the DSL and create it via the REST API (Take a look into the README.MD)

job('seed') {

    description("This job will allow you to create a brand new pipeline or update an existing one.")
    logRotator(-1, 10, -1, -1)
    label("master")

    blockOn(Arrays.asList("create-or-update-seed-job"))

    scm {
        github 'BenjiTrapp/jenkins-in-the-gradle'
    }


    parameters {
        choiceParam("jobName", Jobs.allJobNamesForSeedJob(), "Choose Job to rebuild or choose * to rebuild all jobs")
    }

    wrappers {
        timestamps()
        buildUserVars()
        preBuildCleanup {
            deleteDirectories(true)
        }
    }

    triggers {
        cron('@midnight')
    }
    steps {
        gradle 'clean test'
        dsl {
            external 'jobs/**/*Jobs.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}