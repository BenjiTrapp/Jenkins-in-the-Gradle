FROM jenkins:2.32.3

COPY plugins.txt /var/jenkins_home/plugins.txt
RUN /usr/local/bin/plugins.sh /var/jenkins_home/plugins.txt

# Adding default Jenkins Jobs
COPY config/master-seed-job/create-or-update-seed-job-config.xml /usr/share/jenkins/ref/jobs/create-or-update-seed-job-config/config.xml

# copy custom built plugins
COPY plugins/*.hpi /usr/share/jenkins/ref/plugins/

# so we can use jenkins cli
COPY config/jenkins.properties /usr/share/jenkins/ref/

############################################
# Configure Jenkins
############################################

# Jenkins settings
COPY config/config.xml /usr/share/jenkins/ref/config.xml

# Jenkins Settings, i.e. Maven, Groovy, ...
# let's configure Jenkins with some defaults
COPY config/*.xml /usr/share/jenkins/ref/

# Copy the stuff one by one
#COPY config/hudson.tasks.Maven.xml /usr/share/jenkins/ref/hudson.tasks.Maven.xml
#COPY config/hudson.plugins.groovy.Groovy.xml /usr/share/jenkins/ref/hudson.plugins.groovy.Groovy.xml
#COPY config/maven-global-settings-files.xml /usr/share/jenkins/ref/maven-global-settings-files.xml

# SSH Keys & Credentials
#COPY config/credentials.xml /usr/share/jenkins/ref/credentials.xml
#COPY config/ssh-keys/cd-demo /usr/share/jenkins/ref/.ssh/id_rsa
#COPY config/ssh-keys/cd-demo.pub /usr/share/jenkins/ref/.ssh/id_rsa.pub
