
def call(credentialsId) {
    def qgStatus = 'UNKNOWN'
    
    // Set a timeout of 1 minutes (or whatever is appropriate)
    timeout(time: 1, unit: 'MINUTES') { 
        try {
            // Wait for the quality gate status
            qgStatus = waitForQualityGate()
        } catch (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException e) {
            // This block is executed if the timeout is reached
            echo "Timeout reached. Quality Gate status is UNKNOWN."
            qgStatus = 'UNKNOWN'
        }
    }
    
    // Continue the pipeline regardless of the status
    if (qgStatus == 'OK') {
        echo "Quality Gate passed!"
    } else {
        echo "Quality Gate did not pass. Proceeding to next stage."
    }
}


