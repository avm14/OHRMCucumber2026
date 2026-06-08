@echo off

echo Executing Regression Test...

cd /d %~dp0

mvn clean test -Dcucumber.filter.tags="@regression"

pause