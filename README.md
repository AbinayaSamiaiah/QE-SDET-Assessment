**Automation Testing Setup with Selenium and Java**

**Overview**

This project uses Selenium WebDriver with Java to automate functional and regression tests for a web application. 
The tests are organized using the TestNG framework, and Maven is used for dependency management.

The README below provides instructions for setting up the automation environment, dependencies, running the tests, and understanding the results generated in the reports.

**Table of Contents**

Prerequisites

Project Setup

Running Tests

Understanding the Report

**Prerequisites**

Ensure that the following software is installed and set up before running the tests:

1. Java Development Kit (JDK)

Version: Recent version according to system specifications

Download: JDK

Check installation in Command Prompt:

java -version

2. Maven

Download: Maven Installation

Check installation in command prompt:

mvn -v

3. Edit the System Environment Variables

Step 1: Copy Java file <path> in your local machine and configure it under System Variables -> New -> JAVA_HOME & <Path>

Step 2: Copy Java bin file <path> in your local machine and configure it under System Variables -> Path -> New -> <Path>

Step 3: Copy Maven file <path> in your local machine and configure it under System Variables -> Path -> New -> <Path>

Step 4: Copy Maven bin file <path> in your local machine and configure it under System Variables -> Path -> New -> <Path>

Try running commands in Java and Maven installation steps, if it threw error before.

4. IDE for Java (Eclipse, IntelliJ IDEA, etc.)

Download Eclipse: Eclipse IDE

Download IntelliJ IDEA: IntelliJ IDEA

5. Selenium WebDriver Dependencies

The project uses Maven to automatically download Selenium dependencies. Ensure the internet connection is active to download the dependencies when running Maven.

6. Browser Driver

For Chrome or Edge, ChromeDriver or EdgeDriver must be downloaded respectively and placed in your PATH or referenced directly in the test configuration.

Download ChromeDriver: ChromeDriver

Download EdgeDriver: EdgeDriver

7. Git & Visual Studio Code

Download Git and Visual Studio Code to perform clone and push actions in Github.

9. TestNG Plugin

Go to Eclipse -> Help -> Eclipse MarketPlace -> Search for 'TestNG' & Install.

**Project Setup**

Step 1: Clone the Github Repository

Clone the repository to your local machine using below commands in Gitbash:

Open Visual Studio Code
Open Terminal
cd <local path where you want to clone the files>
git clone https://github.com/AbinayaSamiaiah/QE-SDET-Assessment.git

Note: make sure files are cloned from master branch of github

Step 2: Install Dependencies

Navigate to the project directory and install the necessary dependencies using Maven:

cd <project directory name>
mvn install

Note: provide commands in Visual Studio Code -> Terminal

Maven will automatically download the required libraries (including Selenium WebDriver) as specified in the pom.xml under project folder.

Step 3: Configure WebDriver Path

You can configure the WebDriver in your test code. For example, if you're using ChromeDriver, add this line in the setup method of your test class:

System.setProperty("webdriver.chrome.driver", "localpath/to/chromedriver");

Alternatively, you can place the browser driver in the system’s PATH.

Step 4: Import the Project into Your IDE

Open your preferred IDE (Eclipse, IntelliJ IDEA).

Import the project as a Maven project to ensure that dependencies are automatically downloaded.


**Running Tests**

To run all the tests, use the following Maven command:

mvn test

This command will trigger all the tests defined in the src/test/java directory using the TestNG framework.

Running Specific Tests
To run a specific test class, use the following Maven command, replacing TestClassName with the name of your test class:

mvn -Dtest=TestClassName test

Alternatively, you can run tests using your IDE’s built-in test runner (Eclipse or IntelliJ IDEA).

Or You can create Test Suite using TestNG plugin in Eclipse by following below steps:
Right click on eclipse project -> TEStNG -> Convert to TestNG -> Provide Suite name and Test name -> Finish -> Navigate to .xml file created under project -> make changes to class name for which test should be run.
Refer testng.xml file in project directory.

Once test run is completed, Results will be available in Console and Results of running suite.


**Understanding the Report**

TestNG Reports

After running the tests, Maven will generate reports in the test-output directory(refresh the project). These reports are created by TestNG.

TestNG HTML Report: This provides a graphical summary of the test execution. It includes test names, pass/fail statuses, execution time, and more.

Location: test-output/surefire-reports/index.html

TestNG XML Report: This is an XML-based report that contains detailed information about the tests, including status codes, errors, and stack traces.

Location: test-output/testng-results.xml

Example: Understanding TestNG HTML Report
Test Summary: The top section shows the number of tests that passed, failed, or were skipped.

Passed: Tests that ran successfully.

Failed: Tests that encountered errors.

Skipped: Tests that were skipped (due to conditions or configurations).

Test Logs: Below the summary, you can view logs for individual test cases, including:

Test Name: The name of the test that ran.

Execution Time: How long the test took to execute.

Test Status: Whether the test passed, failed, or was skipped.

Stack Trace (for failed tests): In case of failure, the detailed error message and stack trace are displayed.

Detailed Test View: Clicking on any test case in the report will give you detailed information like the input, output, and any error logs.

**Report View**

Test Result Summary

Passed: 5

Failed: 0

Skipped: 0

Individual Test Details

Total running time: 41 seconds

Class  Time(ms)
UIValidation.REQ001B	37,106
UIValidation.REQ001B_SUPPORTUPGRADE	3,352
UIValidation.REQ001A	552
UIValidation.REQ001B_SUPPORTUPGRADE	281
UIValidation.REQ001B_SUPPORTUPGRADE	70
