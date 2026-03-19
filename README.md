# Refactorización del Sistema de Biblioteca - Aplicando SoC y GRASP

## Contexto del proyecto

El objetivo de este reto es refactorizar un sistema de biblioteca que inicialmente tenía un mal diseño desde el punto de vista de la arquitectura de software.

El archivo original (`sistema_biblioteca.py`) contenía una única clase que se encargaba de realizar todo el proceso de préstamo de un libro, incluyendo validaciones, cálculos, persistencia de datos y envío de notificaciones.

El propósito de la refactorización fue aplicar correctamente los principios:

* **SoC (Separation of Concerns)**
* **GRASP - Controller**
* Buenas prácticas de arquitectura en **Spring Boot**

---

## Diseño inicial (ANTES de la refactorización)

El sistema estaba compuesto por una sola clase:

```
SistemaBiblioteca
```

Esta clase tenía un único método público:

```
prestarLibro(idUsuario, isbnLibro, dias)
```

Y dentro de este mismo método se realizaban todas las tareas:

* Verificar disponibilidad del libro
* Verificar si el usuario tenía multas
* Calcular la fecha de devolución
* Calcular el costo del préstamo
* Guardar el préstamo en base de datos
* Actualizar el stock del libro
* Enviar un correo de recordatorio

### Problema principal

La clase estaba violando completamente el principio de responsabilidad única, ya que una sola clase estaba encargada de múltiples responsabilidades que no estaban relacionadas entre sí.

---

## Diseño refactorizado (DESPUÉS de la refactorización)

El sistema fue reorganizado usando una arquitectura por capas basada en Spring Boot.

Ahora el sistema está compuesto por las siguientes clases:

```
PrestamoController
PrestamoService
LibroService
UsuarioService
NotificacionService
```

Cada clase ahora tiene una única responsabilidad clara dentro del sistema.

---

## Flujo del sistema después de la refactorización

1. El usuario realiza una petición para prestar un libro.
2. El `PrestamoController` recibe la solicitud.
3. El `PrestamoService` coordina la lógica del préstamo.
4. El `LibroService` verifica disponibilidad y actualiza el stock.
5. El `UsuarioService` valida si el usuario tiene multas.
6. El `NotificacionService` envía el correo de recordatorio.

---

# Respuestas solicitadas

## ¿Por qué el diseño inicial violaba el SoC y el Controller (GRASP)?

El diseño inicial violaba el principio **SoC (Separation of Concerns)** porque una sola clase (`SistemaBiblioteca`) tenía múltiples responsabilidades al mismo tiempo.

Esta clase:

* Validaba usuarios
* Validaba libros
* Calculaba fechas
* Calculaba costos
* Guardaba datos en base de datos
* Actualizaba inventario
* Enviaba correos electrónicos

Esto hace que el sistema sea difícil de mantener, difícil de escalar y difícil de probar.

También violaba el patrón **Controller (GRASP)** porque no existía una clase encargada únicamente de coordinar el flujo del sistema. En lugar de eso, la clase hacía tanto el control como la lógica de negocio y la persistencia.

---

## ¿Qué clases se crearon y por qué?

Se crearon las siguientes clases para separar responsabilidades correctamente:

### PrestamoController

Se encarga únicamente de recibir la solicitud del usuario y delegar la operación al servicio correspondiente.

---

### PrestamoService

Contiene la lógica principal del proceso de préstamo. Es el encargado de coordinar los servicios necesarios para completar la operación.

---

### LibroService

Se encarga de todo lo relacionado con los libros:

* Verificar disponibilidad
* Actualizar el stock

---

### UsuarioService

Se encarga de validar información del usuario, específicamente si tiene multas pendientes.

---

### NotificacionService

Se encarga de enviar el correo de recordatorio cuando se realiza un préstamo.

---

## ¿Qué trade-offs tiene esta solución?

### Ventajas

* El código ahora es más limpio y organizado
* Cada clase tiene una responsabilidad clara
* Es más fácil de mantener
* Es más fácil de escalar
* Es más fácil de probar (cada servicio se puede probar por separado)
* Se reduce el acoplamiento entre clases
* Se mejora la legibilidad del sistema

---

### Desventajas

* El número de clases aumenta
* El proyecto se vuelve más grande
* Puede parecer más complejo para alguien que apenas está empezando
* Requiere entender arquitectura por capas

---

## Conclusión

La refactorización permitió transformar un diseño monolítico con múltiples responsabilidades en una arquitectura organizada, escalable y fácil de mantener, aplicando correctamente los principios SoC y GRASP dentro de una aplicación Spring Boot.
