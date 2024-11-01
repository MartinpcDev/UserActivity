# GitHub Activity CLI

Challenge basado en el reto [roadmap.sh](https://roadmap.sh/projects/github-user-activity)

## Descripción

GitHub Activity CLI es una herramienta de línea de comandos escrita en Java que permite a los
usuarios obtener y mostrar la actividad reciente de un usuario de GitHub. Utilizando la API pública
de GitHub, la aplicación proporciona un resumen claro y conciso de las acciones recientes realizadas
por el usuario especificado.

## Funcionalidades

* **Consulta de Usuario**: Permite al usuario ingresar un nombre de usuario de GitHub al ejecutar la
  aplicación desde la línea de comandos.
* **Obtención de Actividad**: Realiza una solicitud a la API de GitHub para obtener la actividad
  reciente del usuario especificado.
* **Visualización en Terminal**: Muestra de manera organizada la actividad, incluyendo eventos
  como "Commits", "Issues abiertos" y "Repositorios estrellados".
* **Manejo de Errores**: Implementa un manejo de errores robusto para situaciones como nombres de
  usuario no válidos o fallas en la API.

## Uso

Para ejecutar la aplicación, utiliza el siguiente comando en la línea de comandos:

```
Ingrese el nombre: <usuario de GitHub>
```

## Requisitos

* Java JDK 8 o superior.
* Conexión a Internet para acceder a la API de GitHub.