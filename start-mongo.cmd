@echo off
rem Starts a local MongoDB. Data is stored in .\data\mongo
set "DBPATH=%~dp0data\mongo"
if not exist "%DBPATH%" mkdir "%DBPATH%"

set "MONGOD=%USERPROFILE%\tools\mongodb\bin\mongod.exe"
if not exist "%MONGOD%" set "MONGOD=mongod"

echo MongoDB running on 127.0.0.1:27017  - close this window to stop it.
"%MONGOD%" --dbpath "%DBPATH%" --bind_ip 127.0.0.1 --port 27017 --logpath "%~dp0data\mongod.log"
