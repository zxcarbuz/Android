@echo off
setlocal
cd /d "%~dp0"
if not exist out mkdir out
dir /s /b src\*.java > source_files.txt
javac -encoding UTF-8 -d out @source_files.txt
if errorlevel 1 (echo BUILD FAILED & exit /b 1)
echo BUILD OK
endlocal