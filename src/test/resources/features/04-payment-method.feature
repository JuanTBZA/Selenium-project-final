Feature: Gestión de métodos de pago

  Background:
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    And inicia sesión con email "probando001@gmail.com" y contraseña "Password123"

  @payment01
  Scenario Outline: Agregar un nuevo método de pago
    And navega a la sección de métodos de pago
    And abre el formulario para añadir nueva tarjeta
    When completa el formulario de tarjeta con los siguientes datos:
      | nombre  | <nombre>  |
      | numero  | <numero>  |
      | mes     | <mes>     |
      | anio    | <anio>    |
    And envía el formulario
    Then debería ver el mensaje de confirmación del método de pago agregado

    Examples:
      | nombre       | numero           | mes | anio |
      | Juan Pérez   | 1234567890125555 | 4   | 2083 |
      | Ana Ramírez  | 4111111111111111 | 12  | 2085 |
