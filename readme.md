# Retail Store Discount Application //TODO: ADD interface

## Overview

This project is a backend service for calculating discounts on a retail store website. The application considers various discount criteria and computes the net payable amount for a given bill. The application is built using Java Spring Boot and follows an object-oriented programming approach with extensive unit test coverage.

## Discount Rules

The following discounts apply on the retail website:

### Percentage Based Discount
| Discount Type           | Discount Rate | Condition                                       |
|-------------------------|---------------|-------------------------------------------------|
| **Employee Discount**   | 30%           | Employees                                       |
| **Affiliate Discount**  | 10%           | Affiliates                                      |
| **Loyalty Discount**    | 5%            | Customers with over 2 years of loyalty          |
| **Non-Grocery Rule**    | N/A           | Percentage-based discounts do not apply to groceries |
| **Single Percentage Discount** | N/A    | Only one percentage-based discount applies per bill |

### General Discount
| Discount Type           | Discount Amount | Condition                        |
|-------------------------|-----------------|----------------------------------|
| **Bill Amount Discount**| $5 for every $100 | Applied on the total bill amount |

## Implementation Status

| Feature                                      | Status |
|----------------------------------------------|--------|
| Code Implementations                         | Done   |
| Unit tests with 100% coverage with JaCoCo    | Done   |
| Static code analysis (CheckStyle and SpotBugs) | Done   |
| SonarQube report for code quality            | Done   |
| Bash script for end-to-end process           | Done   |
| Integration with Swagger                     | Done   |

## Project Structure

The project is structured as follows:
- `src/main/java`: Contains the main application code.
- `src/test/java`: Contains the unit tests.
- `pom.xml`: Maven configuration file.
- `sonar-project.properties`: SonarQube configuration.
- `build_and_test.sh`: Executable script file.

## High-Level UML Class Diagram

The following diagram represents the high-level structure of the classes used in the discount calculation system.

### Constants 
[![](https://mermaid.ink/img/pako:eNp9kFFrgzAQx79KOOjTRBKnVvMm1YLg1rK6hw6hZJq2giZFE1gnfvdluvVlo_d0d__fHXf_AUpZcaBQiLJhfR_X7NSxthDIxGKB4rovpRZqJUWvmFD9rEzsXxENs_wdD6iS-r3hKHnaZpt9khzidLfavD7n_zDRep1maZTfhcyWKMv395Bt8nIgGN8QNDNjIcCClnctqyvz7HRmAerMW14ANWnFj0w3qjA-jAZlWsndVZRAVae5BZ3UpzPQI2t6U-lLxRT_serWvTDxJmX7O2JKoAN8AHWIZ_ueG7jYcfCjGxILrkCJh21C_OUSkyAMXD8cLfic5rHtjV9ppHrS?type=png)](https://mermaid.live/edit#pako:eNp9kFFrgzAQx79KOOjTRBKnVvMm1YLg1rK6hw6hZJq2giZFE1gnfvdluvVlo_d0d__fHXf_AUpZcaBQiLJhfR_X7NSxthDIxGKB4rovpRZqJUWvmFD9rEzsXxENs_wdD6iS-r3hKHnaZpt9khzidLfavD7n_zDRep1maZTfhcyWKMv395Bt8nIgGN8QNDNjIcCClnctqyvz7HRmAerMW14ANWnFj0w3qjA-jAZlWsndVZRAVae5BZ3UpzPQI2t6U-lLxRT_serWvTDxJmX7O2JKoAN8AHWIZ_ueG7jYcfCjGxILrkCJh21C_OUSkyAMXD8cLfic5rHtjV9ppHrS)

### Controller and service class
[![](https://mermaid.ink/img/pako:eNqlVe9r2zAQ_VcOQSFlSYmT5kdNKazp2AZtF5ruy8gXRb44WmXJk-RRt-R_nyzHiZM6o2MOGDv37t27d5L8SpiKkIRkLpmgxtxwGmuazOVcgrtOTuCGG6YyaSdKWq2EQF2GPLwhCq9lvLg-bOMz1L85Q4j23-vQBzSpkgY_ScttflllXgGjgmWCWrxHO6U5XQj8mBSh1oILEcK1u59CCF-ojAQamH6bPYLGXxkaa8CqHQHY1U4CODhItJCWnEA9KSyowQiU9OCiQily3eDJXhf7hlQNN7rx7paqhDrJNY9vkPGEih3No7JUbBi4xcSEcMuNvfzqnq8KokmFNL4tW-CrhtUSqBDg84Af9n286BQ1Q2lpjJXMVmZQh_Dd3dvwHh3plmI3lq3_BRnYPMVyUkp2Yq0Y6rykfpcn90p-LpP-w51_Ln2diaetJ3Y3m7CGbqi7cGkNPhxKOlI9Qos64RLv6PNfR-MXVgUuKyf0mSdZ0jgPmqaCM79Djozmp-KSyxgi10ld3EIpgVQCN7dKxo-u4CQzViWoD9RMVsie3NpbejGenBugIFxapxAKbJMIrUTpYh872h7kSLU5Pb4_mzbm_o6cWV0or82oIVpZgdFbSPOYy8T2m8wq4pt2h53VGbNKF4eUs9ByKvgLHsj3jVU_gPqzb_YB3QrijmzF083KbDiVO52rN2dTWFh9kFLF6ngH1GgzLU0pgLSJG0VCeeQ-HN7OOXGDS3BOQvcY4ZJmws7dN2XtoDSzapZLRkLXLbaJVlm8IuGSCuPesrRYNZvPzvbflMofSiVVinsl4St5JuFocDYIgl43GI9H3Yt-ELRJTsIgCM66w_Peef980B2M-8P-uk1ePEH3bDQcXQTBMOhdjEeDYNxf_wH8Rl0V?type=png)](https://mermaid.live/edit#pako:eNqlVe9r2zAQ_VcOQSFlSYmT5kdNKazp2AZtF5ruy8gXRb44WmXJk-RRt-R_nyzHiZM6o2MOGDv37t27d5L8SpiKkIRkLpmgxtxwGmuazOVcgrtOTuCGG6YyaSdKWq2EQF2GPLwhCq9lvLg-bOMz1L85Q4j23-vQBzSpkgY_ScttflllXgGjgmWCWrxHO6U5XQj8mBSh1oILEcK1u59CCF-ojAQamH6bPYLGXxkaa8CqHQHY1U4CODhItJCWnEA9KSyowQiU9OCiQily3eDJXhf7hlQNN7rx7paqhDrJNY9vkPGEih3No7JUbBi4xcSEcMuNvfzqnq8KokmFNL4tW-CrhtUSqBDg84Af9n286BQ1Q2lpjJXMVmZQh_Dd3dvwHh3plmI3lq3_BRnYPMVyUkp2Yq0Y6rykfpcn90p-LpP-w51_Ln2diaetJ3Y3m7CGbqi7cGkNPhxKOlI9Qos64RLv6PNfR-MXVgUuKyf0mSdZ0jgPmqaCM79Djozmp-KSyxgi10ld3EIpgVQCN7dKxo-u4CQzViWoD9RMVsie3NpbejGenBugIFxapxAKbJMIrUTpYh872h7kSLU5Pb4_mzbm_o6cWV0or82oIVpZgdFbSPOYy8T2m8wq4pt2h53VGbNKF4eUs9ByKvgLHsj3jVU_gPqzb_YB3QrijmzF083KbDiVO52rN2dTWFh9kFLF6ngH1GgzLU0pgLSJG0VCeeQ-HN7OOXGDS3BOQvcY4ZJmws7dN2XtoDSzapZLRkLXLbaJVlm8IuGSCuPesrRYNZvPzvbflMofSiVVinsl4St5JuFocDYIgl43GI9H3Yt-ELRJTsIgCM66w_Peef980B2M-8P-uk1ePEH3bDQcXQTBMOhdjEeDYNxf_wH8Rl0V)

### Model Class
[![](https://mermaid.ink/img/pako:eNp9VGtr2zAU_StCUEiZMXESO40ohS5xhyFNSh4fNgJFc9RUw5YyW4alIf-9evghe6E2CN17pXN0z73SGcZ8TyCCOxYnOM9nFB8ynO6Y-oH8bm7Ad5okxtBLtA3OxqO-b2AtMsoO4LcMRHs7sM1JBgo52M45zcV9JEj6AKgcczumsHsGB5WwjgZAGssxO5CFcQsQmHKWi6yIBc-A4IAyKihO6Aexzn6xMlIb7YyUfTUjxdbOqAwwnBLbTZkAfwvMBBUn26-QN6cjAUIO7UQPMxLTFCfyxAInLxmNSXdnz_A3SijaxqoIkaJ3NAWqGR0LF1l0X-vVKGPrta0raPTSZb2ml6rUVb1UYNHRTKFobYpy0moSHuNkhgUBf7g8HzuoeXd3z_C1O2XR0qjCRjWdYyOihuhrYRoJbGFCVlT923SSzqmlzo_VchquonBtOxfLxWsnUEM3Mv8PFj6_zJc_w9D2PT49RfPocdNyTrfrzfI5XFnYZqovsOs-mDoipVHeiegbYSKg5yktUsxOt2aVDlar9AGR1d8atEIvo02FoQNTkqWY7uWjo_PaQfFOZGdAJKd78oaLROzke3SRS3Eh-PrEYohkVYgDM14c3iF6w0kureK4l3Urn6zae8TsF-dptUWaEJ3hP4g8b-J6A28SBN5o7AcTb-zAk3SP-u7AHwfeIOj373x_dHHghwbou8HIG3qBfzcc-v1AGpdPRJCRXQ?type=png)](https://mermaid.live/edit#pako:eNp9VGtr2zAU_StCUEiZMXESO40ohS5xhyFNSh4fNgJFc9RUw5YyW4alIf-9evghe6E2CN17pXN0z73SGcZ8TyCCOxYnOM9nFB8ynO6Y-oH8bm7Ad5okxtBLtA3OxqO-b2AtMsoO4LcMRHs7sM1JBgo52M45zcV9JEj6AKgcczumsHsGB5WwjgZAGssxO5CFcQsQmHKWi6yIBc-A4IAyKihO6Aexzn6xMlIb7YyUfTUjxdbOqAwwnBLbTZkAfwvMBBUn26-QN6cjAUIO7UQPMxLTFCfyxAInLxmNSXdnz_A3SijaxqoIkaJ3NAWqGR0LF1l0X-vVKGPrta0raPTSZb2ml6rUVb1UYNHRTKFobYpy0moSHuNkhgUBf7g8HzuoeXd3z_C1O2XR0qjCRjWdYyOihuhrYRoJbGFCVlT923SSzqmlzo_VchquonBtOxfLxWsnUEM3Mv8PFj6_zJc_w9D2PT49RfPocdNyTrfrzfI5XFnYZqovsOs-mDoipVHeiegbYSKg5yktUsxOt2aVDlar9AGR1d8atEIvo02FoQNTkqWY7uWjo_PaQfFOZGdAJKd78oaLROzke3SRS3Eh-PrEYohkVYgDM14c3iF6w0kureK4l3Urn6zae8TsF-dptUWaEJ3hP4g8b-J6A28SBN5o7AcTb-zAk3SP-u7AHwfeIOj373x_dHHghwbou8HIG3qBfzcc-v1AGpdPRJCRXQ)


## Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- Docker (optional - needed only for SonarQube Server configuration)

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/yourusername/retail-store-discount.git
```

```sh
cd retail-store-discount
```

### Give the build_and_test.sh Executable Permissions

```sh
sudo chmod +x build_and_test.sh
```
### Run the Build and Test Script

```sh
./build_and_test.sh
```

This script will:
    
* Build the project.
* Perform static code analysis and spot bugs. 
* Run tests and publish the results to JaCoCo. 
* Starts the application.

### Check Application Health for Successful Startup

```sh
http://localhost:8080/actuator/health
```

Once the status shows as up, proceed to the next step.

### Accessing the REST Application

The application is integrated with Swagger, making the REST API endpoints and schema available at:

```sh
http://localhost:8080/swagger-ui/index.html
```

Use the sample payload below (details about the attributes are provided in this section):

```sh
{
  "billId": "bill001",
  "user": {
    "userId": "USER1",
    "userName": "TestUser",
    "userType": "EMPLOYEE",
    "joiningDate": "2024-07-27"
  },
  "items": [
    {
      "itemId": "item001",
      "name": "Laptop",
      "totalPrice": 400,
      "quantity": 1,
      "type": "NON_GROCERIES"
    },
    {
      "itemId": "item002",
      "name": "Milk",
      "totalPrice": 100,
      "quantity": 2,
      "type": "GROCERIES"
    }
  ]
}

```

Sample Output

```sh
{
  "totalAmount": "500.00",
  "discountedAmount": "355.00"
}
```

- Details About the Computations
    - item001 - totalPrice: 400 - NON_GROCERY
    - item002 - totalPrice: 100 - GROCERY
    - User - EMPLOYEE - Joining date (2024-07-27)
    - Step by Step Calculation:
        -   Total Price(totalAmount): 400 + 100 = 500
        -   Total Price Non-Grocery Items: 400
        -   Employee Discount: 30% on non-grocery items = 400 * 0.30 = 120
        -   Loyalty Discount: 0 (Since customer has not joined before 2 years)
        -   Default Discount: $5 for each $100 purchase on bill amount = (500 / 100) * 5 = 25
        -   Net Payable Amount(discountedAmount): Total Price - Employee Discount - Default Discount = 500 - 120 - 25 = 355

### JaCoCo Test Report for Unit Tests Locally
Once the build_and_test.sh script is executed and the application is started:

```sh
-   Go to target/site/jacoco/
-   Open index.html in a web browser to view the unit test code report.
````

## Running SonarQube locally with Docker

Run SonarQube Container

```sh
docker run -d --name sonarqube -p 9000:9000 sonarqube:latest
```

### Access SonarQube
-   Access SonarQube at http://localhost:9000
-   Log in with default credentials (username: admin, password: admin)
-   Change the password upon first login
-   Go to Profile -> My Account -> Security -> Generate a Token
-   Use the generated token in the following command:

```sh
mvn clean verify sonar:sonar -Dsonar.token=<token>
```

### To Include SonarQube Deployment in build_and_test.sh (optional)

In the build_and_test.sh After code coverage include the following snippet

```sh
echo "Starting SonarQube analysis..."
execute_step mvn clean verify sonar:sonar -Dsonar.token=sqp_291df72412f2745d777c3fad0df590a30dce068b
echo "SonarQube analysis successful."
```

### Assumptions

The Bill object has an aggregation to User and List of Items
The Bill object has the following structure

### UML Diagram and Class Diagram
