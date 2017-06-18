package de.benjitrapp.defaults

class JobDefaults {

    static enum Workspace {
        KEEP,
        WIPE_OUT{
            boolean wipe_out() {
                return true
            }
        }

        static boolean wipe_out() { return false }
    }

    def static wrappers() {
        return {
            timestamps()
            buildUserVars()
            colorizeOutput()
        }
    }

    def static cleanUpwrappers() {
        return {
            timestamps()
            buildUserVars()
            preBuildCleanup()
        }
    }
}
