#compilar con test con el perfil dev
mvn clean install -Dspring.profiles.active=dev

#he tenido un problema al ejecutar el test con el archivo de configuracion yml, por lo que lo cambie a properties, sin embargo agregue un perfil, ejecutar esta instruccion pot si surge el error nuevamente
mvn clean install -DskipTests
