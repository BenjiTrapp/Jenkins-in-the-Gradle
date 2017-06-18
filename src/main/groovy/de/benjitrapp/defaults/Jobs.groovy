package de.benjitrapp.defaults

enum Jobs {
    /*
        EXAMPLE_JOB_0("example_job"),
    */
    EXAMPLE_JOB("example_job")

    private String name

    Jobs(name) {this.name = name}

    def jobName() {return name}

    def static allJobNamesForSeedJob() {
        List<String> result = new ArrayList<>()
        result.add('*')
        result.addAll(Views.viewNames())
        result.addAll(jobNames())

        return Collections.sort(result)
    }

    def static jobNames() {
        List<String> result = new ArrayList<>()

        for (Jobs v : values()) {
            result.add(v.jobName())
        }
        return result
    }
}
