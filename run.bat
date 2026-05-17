@echo off
setlocal

set "ROOT=%~dp0"
set "LIB=%ROOT%APPjeauge_lib\*"
set "OUT=%ROOT%out"
set "MAIN_CLASS=shell.sijoumi.etatcuve.Cuve"

where java >nul 2>nul
if errorlevel 1 (
  echo Java runtime not found. Install Java and make sure java is in PATH.
  pause
  exit /b 1
)

if not exist "%OUT%\shell\sijoumi\etatcuve\Cuve.class" (
  call "%ROOT%compile.bat"
  if errorlevel 1 exit /b 1
)

cd /d "%ROOT%"
java -cp "%OUT%;%ROOT%;%LIB%" %MAIN_CLASS%

if errorlevel 1 (
  echo.
  echo Application exited with an error.
  pause
)

endlocal

