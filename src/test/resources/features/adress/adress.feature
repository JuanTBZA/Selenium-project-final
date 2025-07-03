Feature: Gestión de direcciones de envío

  Background:
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    And inicia sesión con email "probando001@gmail.com" y contraseña "Password123"

  @address01
  Scenario Outline: Agregar una nueva dirección de envío
    And navega a la sección de direcciones guardadas
    When agrega una nueva dirección con los siguientes datos:
      | nombre     | <nombre>     |
      | dirección  | <direccion>  |
      | ciudad     | <ciudad>     |
      | estado     | <estado>     |
      | código     | <codigo>     |
      | país       | <pais>       |
    Then debería ver el mensaje de confirmación de la direccion agregada

    Examples:
      | nombre | direccion             | ciudad | estado | codigo | pais |
      | Juan   | Av. Siempre Viva 123 | Lima   | Perú   | 11000  | Perú |
      | Ana    | Jr. Las Flores 456    | Cusco  | Perú   | 08000  | Perú |
