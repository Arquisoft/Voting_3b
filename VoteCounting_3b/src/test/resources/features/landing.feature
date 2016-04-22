Feature: landing page 
  Scenario: client makes call to GET /
    When the client calls /
    Then the client receives status code of 200
    And the client receives the string "Bienvenido al sistema de recuento oficial"