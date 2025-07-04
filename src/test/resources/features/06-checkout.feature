Feature: Realizar un pedido con productos aleatorios, segunda dirección y método de pago

  Background:
    Given el usuario accede a Juice Shop
    And abre la página de login desde el menú de cuenta
    And inicia sesión con email "probando001@gmail.com" y contraseña "Password123"

  @pedido02
  Scenario: Completar un pedido con productos aleatorios, segunda dirección y método de pago
    Given el usuario agrega 2 productos aleatorios del catálogo al carrito
    When procede al checkout desde el carrito
    And elige la segunda dirección guardada
    And selecciona el primer método de entrega disponible
    And utiliza la primera tarjeta guardada para el pago
    Then debería ver el mensaje de confirmación de compra "Thank you for your purchase!"
