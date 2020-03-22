cd /D "%~dp0"
java -jar -Dwebdriver.chrome.driver="chromedriver.exe" selenium-server-standalone-3.141.59.jar -role node -hub %1 -nodeConfig %2