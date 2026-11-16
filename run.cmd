@echo off
rem Starts MongoDB in its own window, then the website on http://localhost:8080
cd /d "%~dp0"
start "MongoDB" cmd /c "%~dp0start-mongo.cmd"
call "%~dp0mvnw.cmd" jetty:run
