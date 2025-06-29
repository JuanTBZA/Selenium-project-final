#language:es
@Checkout
Característica: Crear una orden
  Como usuario del sitio web de comercio electrónico
  quiero hacer una compra
  Para obtener el producto que necesito

  Antecedentes: usuario esta logeado
    Dado que el usuario esta en la pagina de inicio de sesion
    Cuando el usuario ingresa su correo electronico y clave
    Entonces el usuario deberia estar logeado

  @chec01
  Escenario: chec01 - Agregar productos al carrito
    Dado que el usuario está en la página de inicio
    Cuando el usuario busca y selecciona un producto "Camiseta"
    Y el usuario añade el producto al carrito
    Entonces el carrito debe mostrar "1 producto" y el total debe ser actualizado correctamente

  @chec02
  Escenario: Proceso de pago exitoso
    Dado que el usuario ha agregado productos al carrito
    Y el usuario ha ingresado su información de envío
    Y el usuario ha seleccionado un método de pago válido
    Cuando el usuario confirma la compra
    Entonces el usuario debe recibir una confirmación de pedido
    Y el estado del pedido debe ser "En proceso"

  @chec03
  Escenario: Manejo de stock insuficiente
    Dado que el usuario está en la página de producto "Zapatos"
    Y el stock de "Zapatos" es cero
    Cuando el usuario intenta agregar "Zapatos" al carrito
    Entonces el usuario debe recibir un mensaje de "Producto agotado"

  @chec04
  Escenario: Cupón de descuento
    Dado que el usuario ha agregado productos al carrito
    Y el usuario tiene un cupón de descuento válido
    Cuando el usuario aplica el cupón
    Entonces el total del carrito debe actualizarse con el descuento aplicado

  @chec05
  Esquema del escenario: : Verificación de historial de pedidos
    Dado que el usuario ha realizado pedidos anteriormente
    Cuando el usuario accede al historial de pedidos
    Entonces el usuario debe ver la lista de sus pedidos anteriores con detalles
    Ejemplos:
    | IdOrden |
    | 00011   |
