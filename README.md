#Mobile Automation Framework

## Prerequisites

1. Node Js          - v13.8.0 and upper
2. Appium           - 1.16.0
3. Xcode            - 11.3.1 (For iOS)
4. Android Studio   - 3.5.3 and upper

## Running The Tests

The current setup has only been used on the iPhone Simulator (there was no Apple Developer ID) and not on Android.

To configure the test to run on the iphone simulator for a specific environment:

* Locate the sit or uat json under the config dir under resources folder
* Open the ${env}.json file and update the binary.path config value
* Under the deviceconf folder update the iphonesimulator.properties
* Its possible to add a new .properties also

To run the test, you can run the TestNG.xml or the ExecutionEngine.java file

### TestNG Properties

* To change the test environment update the "environment" property on the TestNG.xml
* To change the device configuration update the "appium.settings" property on the TestNG.xml

##Reports

Once the test suit has finished executing the following reports are generated under the target folder

* Cucumber Report on this directory target/cucumber-html-reports and open -> (overview-features.html)
* Extent Report on this directory target/test-output/HtmlReport and open -> (ExtentHtml.html)

## High-level Framework Overview

The framework uses Cucumber to describe high-level functional tests. 

* Page Actions are used to perform user actions and validate expected results.
* Page Objects are used to store all element objects for each screen.


 

## Web Elements Naming Standards 

| WebElement Type  | Prefix | Examples                 |
|------------------|--------|--------------------------|
| Text Box         | txt    | txtEmail , txtPassword   |
| Button           | btn    | btnRegister , btnLogin   |
| Drop down        | dd     | ddCountry , ddYear       |
| Select Drop Down | sdd    | sddMonth , sddYear       |
| Check Box        | cb     | cbGender, cbSalaryRange  |
| Header           | hdr    | hdrPrint, hdrUser        |
| Table            | tbl    | tblBooks, tblProducts    |
| Label            | lbl    | lblUserName, lblPassword |
| Image            | lbl    | imgProfile, imgCart      |
| Video            | vdo    | vdoWelcome               |

## User Actions Naming Standards
| Action                      | Prefix | Examples                |
|-----------------------------|--------|-------------------------|
| Check a check box           | chk    | chkGender               |
| Click                       | clk    | clkSigin, clkRegister   |
| Select value from drop down | select | selectYear, selectMonth |
| Type                        | set    | setEmail, setPassword   |

## Gherking and BDD
The Golden Gherkin Rule: Treat other readers as you would want to be treated. Write Gherkin so that people who don’t know the feature will understand it.

The Cardinal Rule of BDD: One Scenario, One Behavior!

Write all steps in third-person point of view. If first-person and third-person steps mix, scenarios become confusing.

* Givens should always use present or present perfect tense

* Whens and Thens should always use present tense.

## References

* https://automationpanda.com/2017/01/30/bdd-101-writing-good-gherkin/

## Credits

Sibusiso Radebe

## License

TODO: