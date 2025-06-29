#language:es
@Login
Característica: Verificar Login
  Como usuario del sitio web de comercio electrónico
  Quiero poder ver y actualizar los datos de mi cuenta
  Para que mis datos de pedido y entrega sean correctos

  @acc01
  Escenario: Iniciar sesión con credenciales correctas
    Dado que el usuario esta en la pagina de login
    Cuando el usario ingresa sus credencuales
    Y hace click en el botón de Log in
    Entonces el sistema deberia mostrarle el menu de log out

  @acc02
  Escenario: Actualización de información personal
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la página de configuración de la cuenta
    Y actualiza su información personal con nuevos datos
    Y guarda los cambios
    Entonces el sistema debe mostrar un mensaje de confirmación y los datos del usuario deben actualizarse correctamente

  @acc03
  Escenario: Agregar Tarjeta de Crédito
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la sección de información de pago
    Y agrega una nueva tarjeta de crédito con detalles válidos
    Y guarda la nueva tarjeta
    Entonces el sistema debe mostrar un mensaje de confirmación y la tarjeta de crédito debe estar asociada a la cuenta del usuario

  @acc04
  Escenario: Agregar Dirección de Envío
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la sección de direcciones de envío
    Y agrega una nueva dirección de envío con detalles válidos
    Y guarda la nueva dirección
    Entonces el sistema debe mostrar un mensaje de confirmación y la dirección de envío debe estar asociada a la cuenta del usuario

  @acc05
  Escenario: Cambio de Clave
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario accede a la página de configuración de la cuenta
    Y cambia su clave actual por una nueva clave segura
    Y guarda los cambios
    Entonces el sistema debe mostrar un mensaje de confirmación y la clave del usuario debe actualizarse correctamente

  @acc06
  Escenario: Intento de cambio de clave sin proporcionar clave actual
    Dado que el usuario ha iniciado sesión en su cuenta
    Cuando el usuario intenta cambiar su clave sin proporcionar la clave actual
    Entonces el sistema debe mostrar un mensaje de error indicando que la clave actual es requerida para realizar el cambio
