@echo off
setlocal

set "ROOT=%~dp0"
set "LIB=%ROOT%APPjeauge_lib\*"
set "OUT=%ROOT%out"
set "SOURCES=%TEMP%\appjeauge_sources_%RANDOM%.txt"

where javac >nul 2>nul
if errorlevel 1 (
  echo Java compiler not found. Install a JDK and make sure javac is in PATH.
  pause
  exit /b 1
)

if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

dir /s /b "%ROOT%*.java" > "%SOURCES%"
javac -encoding UTF-8 -cp ".;%LIB%" -d "%OUT%" @"%SOURCES%"
set "RC=%ERRORLEVEL%"
del "%SOURCES%" >nul 2>nul

if not "%RC%"=="0" (
  echo.
  echo Compilation failed.
  pause
  exit /b %RC%
)

echo Compilation finished.
endlocal

