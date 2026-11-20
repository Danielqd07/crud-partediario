@echo off
setlocal

set "MAVEN_VERSION=3.9.9"
set "MAVEN_DIST=apache-maven-%MAVEN_VERSION%"
set "MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\%MAVEN_DIST%"
set "MAVEN_ZIP=%USERPROFILE%\.m2\wrapper\dists\%MAVEN_DIST%-bin.zip"
set "MAVEN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/%MAVEN_DIST%-bin.zip"

if exist "%MAVEN_HOME%\bin\mvn.cmd" goto runMaven

echo Maven %MAVEN_VERSION% no esta instalado. Descargando...
if not exist "%USERPROFILE%\.m2\wrapper\dists" mkdir "%USERPROFILE%\.m2\wrapper\dists"

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$ErrorActionPreference='Stop'; Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%'; Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%USERPROFILE%\.m2\wrapper\dists' -Force"

if errorlevel 1 (
    echo.
    echo ERROR: No se pudo descargar o descomprimir Maven.
    exit /b 1
)

del "%MAVEN_ZIP%" >nul 2>&1

:runMaven
call "%MAVEN_HOME%\bin\mvn.cmd" %*
exit /b %ERRORLEVEL%
