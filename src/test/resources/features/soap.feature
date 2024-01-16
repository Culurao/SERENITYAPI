Feature: Sumar dos enteros

  Scenario Outline: Suma de dos numeros enteros
    Given el actor tiene acceso al sistema
    When el actor declara los numeros enteros <Numero1> y <Numero2>
    Then deberia obtener la suma <ResultadoEsperado>

    Examples:
      | Numero1 | Numero2 | ResultadoEsperado |
      | 5       | 7       | 12                |
      | -3      | 8       | 5                 |
      | 0       | 0       | 0                 |
      | 10      | -5      | 5                 |
