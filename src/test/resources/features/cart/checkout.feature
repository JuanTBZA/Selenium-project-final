Feature: Realizar un pedido completo con múltiples productos, dirección y método de pago

  Background:
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    And inicia sesión con email "juan@gmail.com" y contraseña "coco20045"

  @pedido01
  Scenario: Completar un pedido con productos seleccionados, dirección y pago guardados
    Given el usuario agrega los siguientes productos al carrito:
      | producto  |
      | apple     |
      | banana    |
      | shirt     |
    When procede al checkout desde el carrito
    And elige la segunda dirección guardada
    And selecciona el primer método de entrega disponible
    And utiliza la primera tarjeta guardada para el pago
    Then debería ver el mensaje de confirmación de compra "Thank you for your purchase!"
