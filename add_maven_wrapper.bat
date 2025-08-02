@echo off
setlocal enabledelayedexpansion

echo.
echo 🔧 Adicionando Maven Wrapper em cada microserviço...

set services[0]=microsservices_esofii\discovery-server
set services[1]=microsservices_esofii\config-server
set services[2]=config-service\criar-usuarios
set services[3]=config-service\crud-de-eventos
set services[4]=config-service\notification-service

for /L %%i in (0,1,4) do (
    set dir=!services[%%i]!
    echo 👉 Verificando: !dir!
    if exist "!dir!" (
        pushd "!dir!" >nul
        if exist mvnw (
            echo    - mvnw já existe ✅
        ) else (
            echo    - Gerando mvnw...
            call mvn wrapper:wrapper
        )
        popd >nul
    ) else (
        echo    - Diretório não encontrado ❌
    )
)

echo.
echo ✅ Finalizado!
pause
