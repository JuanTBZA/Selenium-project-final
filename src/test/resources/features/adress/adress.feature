Feature: Gestión de direcciones de envío

  Background:
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    And inicia sesión con email "probando001@gmail.com" y contraseña "Password123"

  @address01
  Scenario: Agregar una nueva dirección de envío
    And navega a la sección de direcciones guardadas
    When agrega una nueva dirección con los siguientes datos:
      | nombre     | Juan                  |
      | dirección  | Av. Siempre Viva 123 |
      | ciudad     | Lima                  |
      | estado     | Perú                  |
      | código     | 11000                 |
      | país       | Perú                  |
    Then debería ver el mensaje de confirmación de la direccion agregada
