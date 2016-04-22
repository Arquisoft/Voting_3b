Feature: vote page 
  Scenario: client makes call to POST /statistics
    When the client calls /statistics
    Then the client receives status code 200
    And the client receives the strings "Resultados votaciones"
    
  Scenario: client makes calls to POST /statisticsCiudad
  	When the client call to /statisticsCiudad
  	Given the city "Oviedo"
  	Then the client receives the number "303"
  	
  Scenario: cliente makes a call to POST /statisticsComunidad
  	When the client makes a call to /statisticsComunidad
  	Given the state "Galicia"
  	Then the client receives the participation "71.39"