package de.benjitrapp.defaults

class ViewDefaults {
    def static columnsForViewFullDetails() {
        return {
            status()
            weather()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
            buildButton()
            cronTrigger()
        }
    }
}
