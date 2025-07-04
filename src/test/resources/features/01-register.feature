Feature: Registro de usuario

  @register01
  Scenario Outline: Registro exitoso de un nuevo cliente
    Given el usuario accede a Juice Shop
    And navega desde Login hasta el formulario de registro
    When se registra con:
      | email           | <email>        |
      | contraseña      | <password>     |
      | repetirPassword | <repeat>       |
      | pregunta        | <pregunta>    |
      | respuesta       | <respuesta>    |
    Then debería ver el mensaje de confirmación "Registration completed successfully. You can now log in."


    Examples:
      | email                 | password    | repeat      | pregunta               | respuesta |
      | probando001@gmail.com       | Password123 | Password123 | Mother's maiden name? | Mariela   |
