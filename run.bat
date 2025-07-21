@echo off

echo Iniciando microsserviços...

:: Inicia o discovery-server
start "discovery-server" cmd /k "cd microsservices_esofii\discovery-server && mvn spring-boot:run"

:: Aguarda 20 segundos
timeout /t 20 /nobreak >nul

:: Inicia o config-server
start "config-server" cmd /k "cd microsservices_esofii\config-server && mvn spring-boot:run"

:: Aguarda 10 segundos
timeout /t 10 /nobreak >nul

:: Inicia o criar-usuarios
start "criar-usuarios" cmd /k "cd config-service\criar-usuarios && mvn spring-boot:run"

:: Inicia o crud-de-eventos
start "crud-de-eventos" cmd /k "cd config-service\crud-de-eventos && mvn spring-boot:run"

:: Inicia o notification-service
start "notification-service" cmd /k "cd config-service\notification-service && mvn spring-boot:run"

echo iniciados.
