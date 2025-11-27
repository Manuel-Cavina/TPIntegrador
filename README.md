# TPIntegrador

# Reconstruye y lanza los contenedores que necesitan actualización

#docker compose up --build -d

# Lanza los contenedores existentes sin reconstruir

#docker compose up -d

# Obtener token

$tokenResponse = Invoke-RestMethod -Uri 'http://localhost:8091/realms/logistica/protocol/openid-connect/token' -Method Post -Body @{
    client_id = 'logistica-client'
    grant_type = 'password'
    username = 'operador1'
    password = 'operador123'
} -ContentType 'application/x-www-form-urlencoded'
$TOKEN = $tokenResponse.access_token
Write-Host "Token obtenido y guardado en \$TOKEN."

# Crea una solicitud con datos relacionados

$tokenResponse = Invoke-RestMethod -Uri 'http://localhost:8091/realms/logistica/protocol/openid-connect/token' -Method Post -Body @{
  client_id = 'logistica-client'
  grant_type = 'password'
  username = 'cliente1'
  password = 'cliente123'
} -ContentType 'application/x-www-form-urlencoded'
$TOKEN = $tokenResponse.access_token
$payload = @{
nroSolicitud = 2
contenedorId = 1
clienteId = 1
costoEstimado = 500.0
tiempoEstimado = '2025-11-26T12:00:00'
costoFinal = 0.0
tiempoReal = $null
estadoId = 1
}

# Consulta una solicitud

Invoke-RestMethod -Uri "http://localhost:8080/api/v1/solicitudes/$id" -Method Get -Headers @{ Authorization = "Bearer $TOKEN" }

# Crea un usuario

$tokenResponse = Invoke-RestMethod -Uri 'http://localhost:8091/realms/logistica/protocol/openid-connect/token' -Method Post -Body @{
  client_id = 'logistica-client'
  grant_type = 'password'
  username = 'operador1'
  password = 'operador123'
} -ContentType 'application/x-www-form-urlencoded'
$TOKEN = $tokenResponse.access_token
$payload = @{
nombre = "Pedro"
apellido = "Perez"
email = "pedroperez@gmail.com"
telefono = "1122334455"
rol = "CLIENTE"
activo = $true
} | ConvertTo-Json

# Probar endpoint en postman

Para que sea mas facil usar dos terminales.
En una seleccinar GET para http://localhost:8091/realms/logistica/protocol/openid-connect/token.
Igual que en la teminal comlpletar el body en x-www-form-urlcoded con:
key: grand_type; value:password
key: client_id; value:logistica-client
key: username; value:operador1
key: password; value:operador123
-->send
En la otra terminal seleccionar POST y probar el endoint que se quiera. Agregando en headers check en Authorization y en value: Bearer [token] (sin corchetes).
