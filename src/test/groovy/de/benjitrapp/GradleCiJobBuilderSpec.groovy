package de.benjitrapp

import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.JobParent
import spock.lang.Specification

@Mixin(JobSpecMixin)
class GradleCiJobBuilderSpec extends Specification {

    JobParent jobParent = createJobParent()
    GradleCiJobBuilder builder

    def setup() {
        builder = new GradleCiJobBuilder(
            name: 'test-job',
            description: 'testing the job',
            ownerAndProject: '0wn3r/example',
            tasks: 'clean test'
        )
    }

    void 'test XML output'() {
        when:
        Job job = builder.build(jobParent)

        then:
        with(job.node) {
            name() == 'project'
            triggers.'hudson.triggers.SCMTrigger'.spec.text() == '@daily'
            !publishers.'hudson.tasks.Mailer'.recipients
        }
    }

    void 'test XML output - with overrides'() {
        given:
        builder.emails = ['test1@mail.de', 'test2@mail.de']
        builder.pollScmSchedule = '@weekly'

        when:
        Job job = builder.build(jobParent)

        then:
        with(job.node) {
            name() == 'project'
            triggers.'hudson.triggers.SCMTrigger'.spec.text() == '@weekly'
            publishers.'hudson.tasks.Mailer'.recipients.text() == 'test1@mail.de test2@mail.de'
        }
    }
}
