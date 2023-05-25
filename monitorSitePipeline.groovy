def call(site) {
    pipeline {
        agent any

        triggers {
            // Poll SCM every 5 minutes
            pollSCM('*/5 * * * *')
        }

        stages {
            stage('Check Site') {
                steps {
                    // Query the status of the site
                    script {
                        def siteUrl = "https://${site}"
                        def response = httpRequest(url: siteUrl, ignoreSslErrors: true, validResponseCodes: '200,400,503')

                        // Check the response status code
                        if (response.status == 400 || response.status == 503) {
                            // Send an email notification to the platform engineer
                            mail to: 'uppusrinath07@gmail.com',
                                subject: "Website Down: ${site}",
                                body: "The website ${site} is down with status code ${response.status}."
                        } else {
                            mail to: 'uppusrinath07@gmail.com',
                                subject: "Website Up: ${site}",
                                body: "The website ${site} is up with status code ${response.status}."
                        }
                    }
                }
            }
        }
    }
}
