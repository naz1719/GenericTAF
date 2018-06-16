@echo OFF
echo In progress ...
taskkill /IM chrome.exe /F
taskkill /IM chromedriver.exe /F

:: taskkill /fi /IM chromedriver.exe /F
:: kill background process

timeout /t -1