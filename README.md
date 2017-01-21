# Selenium + TestNg + DataProvider
 the framework based on [webdriver-testng-archetype](https://github.com/barancev/webdriver-testng-archetype)
## How to execute tests:
### Pre-requisite:
please install and setup the next artifacts:

 * [JDK 1.8.^](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (tested on 1.8.102)
 * [Apache Maven 3.3.^](https://maven.apache.org/download.cgi) (tested on 3.3.9)
 * [Google Chrome](https://www.google.com/chrome/browser/desktop/), browser (tested on v.55.0.2883.87 (64-bit))
 * [ChromeDriver 2.27](https://sites.google.com/a/chromium.org/chromedriver/downloads), depends on used chrome browser
 * [Firefox](https://www.mozilla.org/en-US/firefox/new/), browser version greater or equal to 50 (tested on v.50.1.0)
 * [geckodriver](https://github.com/mozilla/geckodriver/releases), tested with v.0.13.0

### Steps:
1. [Clone or download](https://help.github.com/articles/cloning-a-repository/) current repository
2. For launching tests with Firefox, perform next command: 
`mvn clean test -Dwebdriver.gecko.driver={path_to_geckodriver}`
3. For running tests with Google Chrome browser perform next command: 
`mvn clean test-P chrome,localhost,nogrid -Dwebdriver.chrome.driver={path_to_chromedriver}`

### Notes:
 * Tested under Windows 10
 * Not tested with browsers: edge, opera, safari
 * For IE browser, except IEDriverServer, required to set compatibility driver settings
   and probably make some changes on test environment (e.g. modify registry), depends on version of Windows OS