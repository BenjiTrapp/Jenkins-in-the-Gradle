package de.benjitrapp.defaults

enum Views {

    /*
        How-To: Add multiple specific jobs to a view:
        VIEW_NAME("view_name", Jobs.AA.jobName() + "|" + Jobs.BB.jobName() ...),
    */

    /*
        How-To: Adding Jobs by RegEx to a view
    */
    SEED_JOBS("SEED_JOBS", "(.*seed.*|.*SEED.*|.*Seed.*)")

    String name
    String pattern

    Views(name, pattern) {
        this.name = name
        this.pattern = pattern
    }

    def viewName() {
        return name
    }

    def pattern() {
        return pattern
    }

    def static viewNames() {
        List<String> result = new ArrayList<>()

        for (Views v : values()) {
            result.add(v.viewName())
        }
        return result
    }
}
