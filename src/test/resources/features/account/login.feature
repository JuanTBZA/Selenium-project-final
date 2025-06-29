Feature: Inicio de sesión

  @login01
  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    When inicia sesión con email "<email>" y contraseña "<password>"
    Then debería ingresar correctamente y ver el mensaje de bienvenida

    Examples:
      | email                 | password    |
      | juanf@gmail.com       | Password123 |
      | fshtestuser@gmail.com | Password456 |
