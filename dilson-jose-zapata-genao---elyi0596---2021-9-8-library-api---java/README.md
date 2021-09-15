# Library / Biblioteca 


#- [Instructions in English](README_EN.md)

# gbh-api-1.0-SNAPSHOT 

## Descripción

Proyecto desarrollado en Java  EE con el JDK 8 y maven desplegado en servidor de aplicaciones wildfly-24.0.1.Final o Payara 5 utilizando JDBC-RESOURCE para la conexcion a la base de datos en esta ocacion Microsoft SQL  Server y Java API for RESTful Web Services (JAX-RS).

## Se utilizó para el desarrollo:

- IDE:  IntelliJ IDEA 2021.2 
- Sistema operativo: Microsoft Windows 10 64bit.
- Java JDK 8




## <a name="requirements"></a>Requerimeinto

 - Servidor de base de datos [Microsoft SQL Server]
    > Es necesario crear la base de datos gbh en el servidor ante de              desplegar el proyecto

 - Payara 5 o wildfly-24.0.1.Final para el despliegue o implementación del     API REST 
    > Es requerido establecer un Pool de Conexión la base de datos (*JDBC Connection Pools*) y un Recurso JDBC (*JDBC Resource*) vinculado al Pool definido dicho JDBC Resource debe de colocarse en la ruta \src\main\webapp\WEB-INF\web.xml donde se muestran las siguientes configuracion del JDBC para que este diponible en la aplicacion tanto en payara como en wildfly 
    
    ```JavaScript
     <resource-ref>
           <res-ref-name>jdbGbh</res-ref-name>
           <res-type>javax.sql.DataSource</res-type>
           <res-auth>Container</res-auth>
    </resource-ref>
    ```
  >Es necesario crear un archivo de configuracion que sera donde apunte el resource-ref del  web.xml para el JDNI name \WEB-INF\payara-web.xml o jboos-web.xml dependiendo el servidor
  
  - paraya 
  
      ```JavaScript
     <?xml version="1.0" encoding="UTF-8"?>
<payara-web-app>
    <class-loader delegate="true"/>
    <property name="default-role-mapping" value="true">
        <description>Enable default group to role mapping</description>
    </property>
    <resource-ref>
        <res-ref-name>jdbGbh</res-ref-name>
        <jndi-name>jdbcPoolGbh</jndi-name>
    </resource-ref>
    <scanning-exclude>*</scanning-exclude>
    <scanning-include>Webinar*</scanning-include>
</payara-web-app>
  ```
  - Wildfly
  
 ```JavaScript
    <?xml version="1.0" encoding="UTF-8"?>
<jboss-web version="7.1" xmlns="http://www.jboss.com/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.jboss.com/xml/ns/javaee http://www.jboss.org/schema/jbossas/jboss-web_7_1.xsd">
    <resource-ref>
        <res-ref-name>jdbGbh</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <jndi-name>java:/jdbcPoolGbh</jndi-name>
    </resource-ref>
</jboss-web>
  ```
  
  
  > res-ref-name es el nombre de referencia que apunta al JDNI name
    jndi-name es el nombre del datasource JDNI de esta forma se puede agregar cualquier datasource sin copilar solo acediendo a la ruta \WEB-INF
 
## <a name="uso"></a>Uso

A continuación se indican los usos de los Endpoints definido por el requerimiento del cliente.

- **Ver listado de libros**: Se mostrarán los libros registrados en la base de datos en este caso formato json por dft.
    ```JavaScript
    /book
    ```
- **Visualizar un libro**: Mostrar un libro especificando un valor numérico o id del libro en este caso formato json por dft.
    ```
    /book/2
    ```
- **Visualizar por página de un libro en el formato deseado usando friendly routes (ej; /book/1 ó /book/1 /page/11/html).**: Mostrar las páginas de un libro especificando el formato de visualización sea en texto plano (plain) o formato HTML (html).
    
    ```
    /book/4/page/1/text
    ```
    ```
    /book/4/page/1/html
    ```

## Ejecutando desde el IDE 
- Cumplir con los [requerimientos](#requirements) solicitados anteriormente.
- Es necesario tener el servidor de base de datos [Microsoft SQL Server]  en ejecución y establecer los datos de conexión en el proyecto creando el JDBC y agregandolo al web.xml cono se indica anteriormente

    > Tener en cuenta se utilizará con fines de pruebas pudiendo afectar objetos coincidentes tales como base de datos o tablas.

- Al abrir el proyecto nos dirigimos a src\main\resources\liquibase\   

    > Debemos de configurar estos archivos para cumplir con seeders/migrations de la base de datos 
     >> donde path es la ruta del archivo sql en esta caso para la creacion de las tablas y los datos de prueba
  
- changelog.xml 

   ```JavaScript
    <?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                      https://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="3" author="dzapata">
        <comment>ddl and dml bd</comment>
        <sqlFile encoding="UTF-8"
                 path="src\main\resources\liquibase\scripts\001_ddl_dml_gbh_db.sql"
                 splitStatements="true" stripComments="true"/>
    </changeSet>
</databaseChangeLog>
 ```
	
> Archuivo que continene la credenciales del servidor de sql es imprescindible tener la base de datos creada 'GBH'
 


- liquibase.properties 

```JavaScript
# Database Short Name: mysql
#changeLogFile=./src/main/resources/liquibase/changelog.xml
#changeLogFile=./src/main/resources/liquibase/changelog.xml
# JDBC Driver Configuration
driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
#liquibase.classpath=../../../../../src/main/resources/liquibase/mssql-jdbc-9.4.0.jre8.jar
url=jdbc:sqlserver://localhost:1433;databaseName=gbh
username=sa
password=hola0596
```


- Al abrir el proyecto nos dirigimos a src\main\resources\liquibase\scripts  

   > scripts sql que genera e inserta datos en las tablas.

    ```
    001_ddl_dml_gbh_db.sql
    ```


   > debe de ejecutar un comando maven ante de iniciar el proyecto que se encarga de crear las tablas y datos de pruebas
    ```
    mvn liquibase:update 
    ```


## Librearías utilizadas

- Liquibase 4.2.0 http://www.liquibase.org/
- org.projectlombok para @Getter and @Setter https://projectlombok.org/

