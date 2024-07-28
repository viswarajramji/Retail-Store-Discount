#!/bin/bash

# Function to execute a command and check its result
execute_step() {
    "$@"
    result=$?
    if [ $result -ne 0 ]; then
        echo "Error executing: $@"
        exit $result
    fi
}

# Step 1: Clean and build the project
echo "************************************** Starting build process **************************************"
execute_step mvn clean install
echo "************************************** Build successful **************************************"

# Step 2: Run linting
echo "************************************** Starting linting process **************************************"
execute_step mvn checkstyle:check
echo "************************************** Linting successful **************************************"

# Step 3: Run SpotBugs
echo "************************************** Starting SpotBugs analysis **************************************"
execute_step mvn spotbugs:check
echo "************************************** SpotBugs analysis successful **************************************"

# Step 4: Run tests and generate code coverage report
echo "************************************** Starting testing process **************************************"
execute_step mvn test
execute_step mvn jacoco:report
echo "************************************** Testing and code coverage successful **************************************"

# Step 5: Run SonarQube analysis
echo "************************************** Starting SonarQube analysis **************************************"
execute_step mvn clean verify sonar:sonar -Dsonar.token=sqp_291df72412f2745d777c3fad0df590a30dce068b
echo "************************************** SonarQube analysis successful **************************************"

# Step 6: Start the Spring Boot application
echo "************************************** Starting Spring Boot application **************************************"
execute_step mvn spring-boot:run
echo "************************************** Spring Boot application started successfully **************************************"

echo "All steps completed successfully."
