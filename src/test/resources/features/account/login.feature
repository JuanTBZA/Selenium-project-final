Feature: Inicio de sesión

  @login01
  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    When inicia sesión con email "<email>" y contraseña "<password>"
    Then debería ingresar correctamente y ver el mensaje de bienvenida con email "<email>"

    Examples:
      | email                 | password    |
      | probando001@gmail.com        | Password123 |

  @login02
  Scenario Outline: Inicio de sesión fallido con credenciales inválidas
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    When inicia sesión con email "<email>" y contraseña "<password>"
    Then debería ver el mensaje de error

    Examples:
      | email                  | password
      | probando001@gmail.com | WrongPassword

