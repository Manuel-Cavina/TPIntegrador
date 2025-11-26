# TPIntegrador

# Reconstruye y lanza los contenedores que necesitan actualización

#docker compose up --build -d

# Lanza los contenedores existentes sin reconstruir

#docker compose up -d

# Crea una solicitud
$tokenResponse = Invoke-RestMethod -Uri 'http://localhost:8091/realms/logistica/protocol/openid-connect/token' -Method Post -Body @{
  client_id = 'logistica-client'
  grant_type = 'password'
  username = 'cliente1'
  password = 'cliente123'
} -ContentType 'application/x-www-form-urlencoded'
$TOKEN = $tokenResponse.access_token
$payload = @{
  nroSolicitud = 0
  contenedorId = 1
  clienteId = 1
  costoEstimado = 500.0
  tiempoEstimado = '2025-11-26T12:00:00'
  costoFinal = 0.0
  tiempoReal = $null
  estadoId = 1
}


