@echo off

echo Executing Smoke Test...

cd /d %~dp0

mvn clean test -Dcucumber.filter.tags="@smoke"

pause