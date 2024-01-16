Feature: Iniciar sesión en el sistema

  Yo como usuario
  Quiero iniciar sesión en el sistema
  Para poder autenticarme en el sistema

  Background:
    Given que soy un usuario registrado

  Scenario: Inicio de sesión exitoso
  When ingreso mis credenciales de inicio de sesion
  Then deberia recibir un token de autenticacion

