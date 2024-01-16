Feature: Registrarse en el sistema

  Yo como usuario
  Quiero registrarme en el sistema
  Para obtener un token de registro

  Scenario: Obtener detalles de todos los usuarios
    Given que soy un usuario autenticado
    When hago una solicitud GET para obtener detalles de usuarios
    Then deberia recibir una respuesta exitosa
    And la respuesta deberia contener detalles de usuarios




