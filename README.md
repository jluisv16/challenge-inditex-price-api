# Price API - Proyecto de Evaluación

Este proyecto es una implementación de un servicio de precios para productos basado en la arquitectura hexagonal y el enfoque Domain-Driven Design (DDD). Utiliza Spring Boot, Java 17 y varios principios de buenas prácticas de desarrollo como Clean Code y SOLID.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 2.x**
- **JUnit y Mockito** (para pruebas unitarias)
- **H2 Database** (base de datos en memoria para pruebas)
- **Spring Web** (para el desarrollo de servicios RESTful)
- **Maven** (gestión de dependencias y construcción del proyecto)
- **Jacoco** (cobertura de pruebas)
- **SonarQube** (para análisis estático de código)
- **Lombok** (para simplificación de código, como generación de constructores, getters, setters, etc.)

## Descripción del Proyecto

Este proyecto consiste en una aplicación de **Spring Boot** que proporciona un endpoint REST para consultar el precio final de un producto de una cadena de tiendas, basado en la fecha de consulta y el identificador del producto y la cadena.

La tabla **PRICES** de una base de datos refleja el precio final de un producto para una cadena entre unas fechas determinadas. Los parámetros clave en la consulta son el identificador de la cadena (`brandId`), el identificador del producto (`productId`), y la fecha en la que se desea conocer el precio (`date`). El servicio consulta la base de datos para encontrar el precio vigente para ese producto y devuelve los detalles correspondientes.

## Tabla PRICES

La tabla **PRICES** tiene los siguientes campos relevantes:

| BRAND_ID | START_DATE           | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE  | CURR |
|----------|----------------------|----------------------|------------|------------|----------|--------|------|
| 1        | 2020-06-14 00:00:00  | 2020-12-31 23:59:59  | 1          | 35455      | 0        | 35.50  | EUR  |
| 1        | 2020-06-14 15:00:00  | 2020-06-14 18:30:00  | 2          | 35455      | 1        | 25.45  | EUR  |
| 1        | 2020-06-15 00:00:00  | 2020-06-15 11:00:00  | 3          | 35455      | 1        | 30.50  | EUR  |
| 1        | 2020-06-15 16:00:00  | 2020-12-31 23:59:59  | 4          | 35455      | 1        | 38.95  | EUR  |

### Descripción de los Campos

- **BRAND_ID**: Identificador de la cadena (1 = ZARA).
- **START_DATE**, **END_DATE**: Fechas de inicio y fin del precio vigente.
- **PRICE_LIST**: Identificador de la tarifa aplicada.
- **PRODUCT_ID**: Identificador del producto.
- **PRIORITY**: Si hay tarifas superpuestas, se aplica la de mayor prioridad.
- **PRICE**: Precio final del producto.
- **CURR**: Moneda del precio (por ejemplo, EUR).

## Requerimientos

1. **Endpoint REST**:
    - Acepta tres parámetros: `date`, `brandId`, y `productId`.
    - Devuelve un objeto que contiene el `price`, `currency`, `productId`, `brandId`, y las fechas de inicio y fin de la tarifa vigente.

2. **Base de Datos**:
    - Se utiliza una base de datos en memoria **H2** para almacenar los datos de precios.
    - Se inicializa con datos predefinidos para simular los precios de los productos.

3. **Tests**:
    - Se realizan pruebas unitarias e integradas para validar que el servicio devuelve correctamente los precios en base a la fecha solicitada.
    - Ejemplos de pruebas:
        - Test 1: Petición a las 10:00 del 14 de junio para el producto 35455 de la marca 1 (ZARA).
        - Test 2: Petición a las 16:00 del 14 de junio para el producto 35455 de la marca 1 (ZARA).
        - Test 3: Petición a las 21:00 del 14 de junio para el producto 35455 de la marca 1 (ZARA).
        - Test 4: Petición a las 10:00 del 15 de junio para el producto 35455 de la marca 1 (ZARA).
        - Test 5: Petición a las 21:00 del 16 de junio para el producto 35455 de la marca 1 (ZARA).

---

## Arquitectura del Proyecto

Este proyecto sigue los principios de **Domain-Driven Design (DDD)**, **Arquitectura Hexagonal**, **Clean Code** y **SOLID**. A continuación se describe cómo se implementa cada uno de estos enfoques.

### 1. **Domain-Driven Design (DDD)**

- **Entidad Product**: Representa un producto con su `id` y `description`. Es la entidad central de la base de datos.
- **Entidad Price**: Representa el precio de un producto en un rango de fechas. Cada precio está asociado a un `Product` y a una `Brand`.
- **Entidad Brand**: Representa la marca del producto.

### 2. **Arquitectura Hexagonal**

La arquitectura hexagonal divide el sistema en varias capas que son independientes entre sí. Cada capa tiene un propósito específico y se comunica a través de interfaces bien definidas.

- **Application Layer**: Esta capa contiene la lógica de negocio y el API. El API se encuentra dentro de esta capa para mantener la cohesión entre la lógica de negocio y los casos de uso del sistema.
- **Domain Layer**: Contiene las entidades del dominio como `Product` y `Price`.
- **Infrastructure Layer**: Se encarga de la persistencia de los datos (repositorios) y las implementaciones concretas de las interfaces de servicios.

### 3. **Clean Code y SOLID**

Se aplican principios de **Clean Code** y **SOLID** para garantizar un código limpio, legible y mantenible. Algunas de las prácticas seguidas son:

- **SRP (Single Responsibility Principle)**: Cada clase tiene una única responsabilidad. Por ejemplo, `PriceService` se encarga solo de la lógica de negocio relacionada con los precios.
- **OCP (Open-Closed Principle)**: Las clases están abiertas a extensión pero cerradas a modificación. Esto se logra mediante la creación de interfaces y el uso de patrones como el repositorio.
- **DIP (Dependency Inversion Principle)**: Las dependencias se inyectan a través de interfaces, lo que facilita las pruebas y la flexibilidad.

---

## Clases Principales

### 1. **PriceController**

El controlador expone un endpoint REST para consultar el precio de un producto para una marca en una fecha específica.

```java
 @GetMapping("/api/prices")
    public ResponseEntity<PriceDto> getValidPrices(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        // Llamar al servicio para obtener los precios válidos
        Optional<PriceDto> validPrice = priceService.findValidPrices(brandId, productId, date);

        if (validPrice.isPresent()) {
            return ResponseEntity.ok(validPrice.get());
        } else {
            if(Objects.isNull(productId)){
                throw new ProductNotFoundException("Se requere un producto" + productId);
            }
            if(Objects.isNull(brandId)){
                throw new ProductNotFoundException("Se requiere un brand " + brandId);
            }
        }

        return ResponseEntity.noContent().build();
}
```
## Ejecutar el Proyecto

Para ejecutar el proyecto localmente, sigue estos pasos:

### Requisitos previos

- **Java 17**: Asegúrate de tener Java 17 instalado en tu máquina. Puedes verificarlo con el siguiente comando:

  ```bash
  java -version

- **Maven**: Asegúrate de tener Maven instalado en tu máquina. Puedes verificarlo con el siguiente comando:

  ```bash
  mvn -version

## Pasos para ejecutar el proyecto

### 1. Clonar el proyecto

Si aún no has clonado el proyecto, puedes hacerlo con el siguiente comando:

```bash
git clone https://github.com/jluisv16/challenge-inditex-price-api.git
```

### 2. Compilar y ejecutar el proyecto

Para compilar y ejecutar el proyecto, utiliza el siguiente comando Maven:

```bash
mvn clean spring-boot:run
```
### 3. Acceder al servicio

Una vez que la aplicación esté en ejecución, el servicio REST estará disponible por defecto en http://localhost:8080.

Para probar el endpoint, puedes realizar una solicitud a /api/prices usando Postman o cURL.
```bash
curl -X GET "http://localhost:8080/api/prices?brandId=1&productId=35455&date=2023-03-16T21:00:00"
```

### 4. Ejecutar las pruebas

Para ejecutar las pruebas unitarias, utiliza el siguiente comando Maven:
```bash
mvn test
```


