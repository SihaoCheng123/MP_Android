# WEATLY :blush:

### Índice

1. [Introducción](#introducción)
2. [Nombre](#nombre)
3. [Logo](#logo)
4. [Diseño](#diseño)
5. [Modelo E-R](#modelo-e-r)
6. [Funcionalidades](#funcionalidades)
7. [Flujo de navegación](#flujo-de-navegación)
8. [Softwares utilizados](#softwares-utilizados)

## Introducción
**Weatly** es una aplicación multiplataforma tanto para dispositivos Android como para plataformas web.
El objetivo principal es la planificación de comidas con la posibilidad de gestionar las recetas y realizar una lista la compra con los ingredientes.

## Nombre
El nombre de la app viene a juego con la palabra _week_ que significa "semana" y la palabra _eat_, de "comer" en inglés.
Puesto que se trata de un planificador semanal de comidas, es un nombre más que apropiado para una aplicación de este estilo.
Además, el nombre en inglés abrirá las puertas a un público más internacional.

## Logo
(Inserte logo aquí e explicación)

## Diseño
Se utilizará un diseño minimalista y simple con un toque moderno. Se da importancia a la simplicidad a través de eliminar elementos que sobrecargan mucho la pantalla
y sobreestimulan al usuario a la hora de usar la aplicación.

### Colores
La paleta de colores consiste principalmente de tres colores con la ayuda variaciones del blanco y negro para el texto.

<img src="https://github.com/user-attachments/assets/d44abfc4-0e39-4b2f-9e7f-3e85f8d5479a" width="300">

**Color primario**

Para el color primario, se utilizará un tono verde, un color muy relacionado con la salud, la frescura y la naturaleza.
Es ideal para botones principales, encabezadois y elementos descacados. En la barra de navegación, se utilizará en botones de acción y en elementos clave como íconos principales.

**Color secundario**

En acompañamiento del primario, se usará un tono morado como secundario. Este color denota extravaganvia y autenticidad, lo cual hará destacar la aplicación de entre otras.
Será para botones de acciones secundarias, campos de texto resaltados y detalles creativos como líneas divisorias o fondos de tarjetas.

**Color terciario**

Para la correcta visualización y lectura, se elegirá un color beige, pues transmite calma y combina con los dos anteriores.
Su uso principal será en fondos sobre el cual resaltarán los demás colores elegidos.

### Tipografía

En cuanto a tipografía, Poppins es una fuente sans serif amigable y redondeada que emite modernidad y funcionalidad.
Además de ser fácil de leer, su amplia gama de tamaños y estilos permite versatilidad y consistencia. 

<img src="https://github.com/user-attachments/assets/8508e503-4e91-4364-ab79-74af1e2bb4d8" width="150">

### Formas

Los bordes redondeados estarán presentes en toda la aplicación, desde la barra de navegación hasta los pop-ups.
Esta decisión se debe a que su uso mantiene la coherencia en el diseño y atraerá la atención del ususario al centro del contenedor donde se encuentra la información.
Asimismo, la elevación de algunos elementos darán dimensión y profundidad al diseño.

<img src="https://github.com/user-attachments/assets/420e6f3f-7af6-4a1a-bd96-2dc8ff7734b8" width="250">
<img src="https://github.com/user-attachments/assets/6eb44ae9-2d6d-413b-a4b2-8cbb9bacc6ac" width="250">
<img src="https://github.com/user-attachments/assets/2995336c-1681-458c-bf72-2d43b5653fad" width="250">

## Modelo E-R

<img src="https://github.com/user-attachments/assets/70c7b893-9dc0-4549-9824-c98f3ef23dc4" width="1050">

## Funcionalidades
- Gestión de recetas (personalizadas)
- Planificación de comidas semanales
- Lista de compras generada automáticamente
- Notificaciones y recordatorios

## Flujo de navegación

1.  La aplicación empieza por el inicio de sesión. El usuario debe introducir sus credenciales y podrá entrar a la aplicación.
  
<img src="https://github.com/user-attachments/assets/16769e59-d060-458d-9f19-5bb204acbda2" width="300" height="600">

2. En el caso de que no tenga una cuenta creada, puede acceder al registro pulsando el siguiente texto en verde.

<img src="https://github.com/user-attachments/assets/feb28056-c06f-4f58-a84b-b4619cb7b268" width="300" height="40">

3. Una vez en el registro, debe rellenar un formulario con sus datos y se creará una cuenta.
Para confirmar que están bien los datos, comprobaremos que en la base de datos no exista un usuario con el mismo correo.

<img src="https://github.com/user-attachments/assets/1460f45d-8199-4f73-90d8-3f626c787466" width="300" height="600">

4. Tras crear la cuenta e iniciar sesión, entra a la pantalla principal de la aplicación.

<img src="https://github.com/user-attachments/assets/35b04911-09eb-4596-8df6-6c9d0ccb6d24" width="300" height="600">


- En la parte de arriba, aparece el nombre de usuario guardado
  
- Después, aparece un calendario con vista semanal en forma de carrusel y el usuario puede deslizar hacia los lados para ver días anteriores o posteriores. También puede pulsar un día específico para ver el menú de ese día.
  
- Por último, aparece cada comida en una tarjeta y está separada por secciones. Si no hay una comida específica para ese día, tiene la posibilidad de añadirla.

5.  El usuario puede entrar en la sección de planificador a través de la barra de navegación inferior.
 
<img src="https://github.com/user-attachments/assets/28296c2c-7c06-45aa-8425-d00ea75a555f" width="300" height="600">

- Puede acceder a una vista mensual del calendario si pulsa en el nombre del mes

<img src="https://github.com/user-attachments/assets/6f44b19b-9b9e-42b4-bc51-f6d4f9271c56" width="300" height="300">

- Puede ver cada tarjeta con su comida e ingredientes correspondientes. Si pulsa en la tarjeta, aparece la receta completa

6. Si le da al botón flotante de añadir, un pop-up sube para añadir una nueva receta.

  <img src="https://github.com/user-attachments/assets/95d08e52-2228-4cdd-b843-88a7cc113e57" width="300" height="500"> 

- El usuario puede crear su propia receta introduciendo el nombre, la fecha, la hora, los pasos. También pude seleccionar una categoría para la receta y añadir sus correspondientes ingredientes. 

7. Tras añadir una receta con sus ingredientes, el usuario puede acceder a la sección de compra a través de la barra de navegación inferior.

- Por defecto, aparecen todos los ingredientes o puede filtrar por categoría

- Cada ingrediente aparece con su nombre, cantidad, precio aproximado y un checkbox para marcar si ya lo tiene.

8. También existe una sección de usuario, accesible desde la barra de navegación inferior

- El usuario puede ver su nombre de usuario con una imagen, si es que ha subido una anteriormente
- Puede modificarr sus datos personales, al igual que cambiar la contraseña
- Puede visualizar tanto sus recetas creadas como sus favoritas 

## Softwares utilizados
![My Skills](https://skillicons.dev/icons?i=java,react,nodejs,androidstudio,mysql,figma,docker)


