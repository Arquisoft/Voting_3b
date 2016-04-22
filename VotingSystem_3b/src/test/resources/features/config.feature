#language: es
Característica: página de configuración de votación
  Escenario: El cliente llama a /guardarVotacion
	Cuando el cliente llama a /guardarVotacion
	Entonces el cliente recibe 200
	Y el resultado contiene la cadena "Configuración de la votación"