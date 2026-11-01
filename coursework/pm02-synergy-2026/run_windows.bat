@echo off
setlocal
cd /d "%~dp0"
call build_windows.bat || exit /b 1
java -Dfile.encoding=UTF-8 -cp out edu.synergy.practice.Main %*