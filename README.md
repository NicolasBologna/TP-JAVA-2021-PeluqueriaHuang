<div style="text-align: center;">
<h1>Universidad Tecnológica Nacional</h1>

<img src="Aspose.Words.cbc8b9cb-dd25-4923-8832-0a6298664115.001.jpeg">

<h2>Trabajo Final</h2>

<h2>Trabajo práctico integrador de la materia JAVA - UTN FRRO - 2021</h2>


<h3>Ingeniería en sistemas de Información</h3>

<h3>2021</h3>

<h3>Comisión Turno Noche</h3>
</div>

**Integrantes:** 

|Nombre|Legajo|Email|
| :- | :- | :- |
|Buljubasich, Sofía|47380|<sofiabuljuba@gmail.com>|
|Bologna, Nicolás|43837|<nicolasandresbologna@hotmail.com>|
|Errecalde, Sofía|43857|<sofiaerrecalde@hotmail.com>|


Sistema para una peluquería con sucursales


# Descripción
La peluquería “Hermanos Huang” nos solicitó un sistema de pedidos de turnos para las diferentes instalaciones de corte en la ciudad de Rosario. Este sistema debe poder administrar todas las sucursales, incorporando la posibilidad de permitir a sus peluqueros armar sus perfiles, publicar sus cortes y tener interacción con sus clientes. Los usuarios podrán solicitar turnos para varios tipos de servicios en una sucursal eligiendo el peluquero.

# Roles


|**Rol**|**Permisos**|
| :- | :- |
|Admin|Control total del sistema|
|Peluqueros|Control sobre sus horarios, turnos, tipos de corte, etc.|
|Clientes|Puede agendar turnos, y cancelarlos|
|Invitado|Sólo puede ver el listado de peluqueros, turnos, cortes..|


# Acciones de los diferentes tipos de usuarios
Admin:
El admin crea las sucursales y crea a los usuarios peluqueros y los asigna a cada sucursal. Carga los servicios. Puede ver el listado de peluqueros, los turnos de cada peluquero y de cada sucursal.

Peluqueros:

Asociar los tipos de servicios que realiza a su perfil, carga sus horarios y ver su horario con los turnos y puede cancelarlos. Puede cargar fotos a su perfil de los trabajos que realizó.

Clientes: 

Seleccionan una sucursal y el peluquero (pueden ver sus datos), y sacan un turno.

Invitado:

Solo puede ver las sucursales y los peluqueros pero no puede sacar turno.


|**Requerimiento**|<p>**cant. mín.**</p><p>**1 o 2 integ**</p>|<p>**cant. máx.**</p><p>**3 o 4 integ**</p>|**Detalle/Listado de casos incluidos**|
| :-: | :-: | :-: | :-: |
|ABMC simple|1 x integ|1 x integ|<p>- Usuarios</p><p>- Sucursales</p><p>- Servicios (son varios: cortes, tratamientos)</p>|
|ABMC dependiente|1|2|<p>- Disponibilidad para turnos</p><p>- Servicios asociado al peluquero</p>|
|CU NO-ABMC|1|2|<p>- Solicitar turno</p><p>- Posteo (no es obligatoria la imagen)</p>|
|Listado simple|1|3(\*)||
|Listado complejo|0|<p>1(\*)</p><p></p>|<p>- Listado de peluqueros por sucursal</p><p>- Listado de turnos por peluquero</p><p></p>|
#




|**Requerimiento**|<p>**cant. mín.**</p><p>**1 o 2 integ**</p>|<p>**cant. máx.**</p><p>**3 o 4 integ**</p>|**Detalle/Listado de casos incluidos**|
| :-: | :-: | :-: | :-: |
|ABMC|todos|todos||
|CU "Complejo"(nivel resumen)|1|2|<p>-TURNOS (disponibilidad/solicitud/cancelar/(cobrar)</p><p>-Posteos y comentarios</p>|
|Listado complejo|1|2|<p>- Filtro de turnos por servicio, por peluquero y por sucursal</p><p>- Perfil del peluquero (con los Posteos y comentarios)</p>|
|Nivel de acceso|2|2|<p>-Admin</p><p>-Peluquero</p><p>-Cliente</p><p>-Invitado</p>|
|Manejo de errores|obligatorio|obligatorio|no requiere detalle|
|requerimiento extra obligatorio (\*\*)|0|1|-Manejo de archivos (Fotos en los posteos del perfil del peluquero)|
|publicar el sitio|obligatorio|obligatorio|no requiere detalle|
#
# Modelo de Dominio
![](Aspose.Words.cbc8b9cb-dd25-4923-8832-0a6298664115.002.png)

**Historial de versiones**

|Versión|Fecha|Autor|Descripción|
| :- | :- | :- | :- |
|1|21/08/2021|Grupo Peluquería|Entrega inicial|
|||||



Doc Creative Tim https://demos.creative-tim.com/now-ui-kit/docs/1.0/getting-started/introduction.html

