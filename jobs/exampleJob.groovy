import de.benjitrapp.defaults.Jobs

job(Jobs.EXAMPLE_JOB.jobName()) {
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        shell("echo Hallo Jenkins!")
    }
}
