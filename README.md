# App with Kotlin


### Requisitos

* Docker 19.0.0+
* Docker-Compose 1.2.0+
* Java - openjdk version "1.8.0"+
* Kotlin 1.4.0+

### Instalar DataBase
Posicionarse en el directorio del proyecto y 
ejecutar el comando:

> docker-compose up

Para cerrar el contenedor
> docker-compose down

### Ejecución del sistema en desarrollo
Primero debe descargar las dependencias del 
proyecto empleando gradlew.

Despues ejecute el archivo **Main.kt**


![file main](./docs/images/file-main.png)

Para lograr entrar al sistema usar las siguientes credenciales:

> User: **erick@admin.com**
> 
> Pass: **admin**

### Generar datos de prueba 

Para poder testear el sistema, puede generar 
datos de prueba.

Configure en el archivo **Run.kt** con la cantidad de 
elementos que quiera generar con las factories.

![faker](./docs/images/faker.png)

Ejecute las fabricas ejecutando el archico **Run.kt**

![Gif](./docs/images/run.png)

    OJO: La DataBase posee Constraints Unique, para
         evitar que se repitan elementos con los 
         mismos datos. Asi que al crear elementos con
         datos repetidos ramdon puede retornar un error.

### Historial de Commits
El estandar que se empleo para llevar el historial 
de commits fue:

* FEACTURE: Se agrego una nueva caracteristica al sistema


* REFACTOR: No se agrego una nueva caracteristica pero el 
  sistema sufrio cambios en su estructura
  

* FIX: Se corrigio un error o bug en el sistema 


* DOC: Se modifico o actualizo la documentacion del sistema

### Preview Gif

![Gif](./docs/images/app.gif)

### Preview Images

![main-1](./docs/images/img-1.png)

![main-2](./docs/images/img-2.png)

![main-3](./docs/images/img-3.png)


    Sistema de prueba aun no esta terminado. 



