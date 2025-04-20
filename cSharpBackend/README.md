# 🌦️ WeatherChecker (.NET + MySQL + Swagger)

A backend-only weather tracking API using .NET Web API, EF Core, and 3rd party OpenWeatherMap APIs.

🔗 https://openweathermap.org/api/one-call-3

---

## 🔧 Tech Stack

- .NET 8 Web API
- MySQL + EF Core
- Swagger
- Clean architecture (Controller-Service-Repository)
- JWT Authentication
- External API: OpenWeatherMap, OpenCageData

---

## 💻 Environment Setup

1. **Install .NET 8 SDK**  
   👉 https://dotnet.microsoft.com/en-us/download/dotnet/8.0

2. **Install MySQL Workbench**  
   👉 https://dev.mysql.com/downloads/workbench/

3. **Install Dotnet Tool**
```bash
dotnet tool install --global dotnet-ef
```
📚 https://learn.microsoft.com/en-us/ef/core/cli/dotnet

---

## 🚀 Setup Instructions

### 1. Create the project:
```bash
dotnet new webapi -n WeatherChecker
cd WeatherChecker
```

### 2. Add required packages:
```bash
dotnet add package Microsoft.EntityFrameworkCore -v 8.0.0
dotnet add package Pomelo.EntityFrameworkCore.MySql -v 8.0.0
dotnet add package Microsoft.EntityFrameworkCore.Design -v 8.0.0
dotnet add package Microsoft.AspNetCore.Authentication.JwtBearer -v 8.0.0
```

### 3. Project structure:
```
- Controllers/
    - AuthController.cs
    - UserController.cs
    - WeathertrackerController.cs
- DbContext/
    - WeatherDbContext.cs
- Dtos/
    - CreateLocationDto.cs
    - LocationDto.cs
    - LoginUserDto.cs
    - RegisterUserDto.cs
    - SearchDto.cs
    - TokenDto.cs
    - UpdateLocationDto.cs
    - UserDto.cs
    - WeatherInfoDto.cs
    - WeatherResultDto.cs
- Migrations/
- Models/
    - Entity.cs
    - GeoResponse.cs
    - Location.cs
    - OpenWeatherResponse.cs
    - User.cs
- Modules/
    - JwtTokenGenerator.cs
    - OpenCageDataMap.cs
    - OpenWeatherMap.cs
- Repositories/
    - ILocationRepository.cs
    - LocationRepository.cs
    - IUserRepository.cs
    - UserRepository.cs
- Services/
    - ILocationService.cs
    - LocationService.cs
    - IUserService.cs
    - UserService.cs
- Utils/
    - PasswordHelper.cs
```

### 4. Configure `appsettings.json`:
```json
"ConnectionStrings": {
  "DefaultConnection": "server=localhost;database=WeatherCheckerDb;user=root;password=your_password;"
},
"JwtSettings": {
  "SecretKey": "your-strong-secret-key",
  "Issuer": "WeatherCheckerAPI",
  "Audience": "WeatherCheckerClient"
}
```

### 5. Register WeatherDbContext and JWT in `Program.cs`

### 6. Apply database migration:
```bash
dotnet ef migrations add InitialCreate
dotnet ef database update
```

### 7. Register Dependency Injection services in Program.cs

### 8. Run the API:
```bash
dotnet run
```
Open Swagger UI at: https://localhost:<port>/swagger

---

## 🔐 Authentication (JWT Token)

The API uses **JWT Bearer Authentication** to secure endpoints.

### 🔑 Login Flow

1. Send credentials to:
```http
POST /auth/login
```
2. Server returns JWT token on success.
3. Use it in Authorization header:
```http
Authorization: Bearer <token>
```

---

## 📦 API Endpoints

### 👤 User API
| Method | Endpoint                      | Description         |
|--------|-------------------------------|---------------------|
| POST   | `/api/users/register`         | Create a new user   |
| GET    | `/api/users/retrieveAllUser`  | Get all users       |
| DELETE | `/api/users/delete/{id}`      | Delete user by ID   |

### 🌦️ Weather API
| Method | Endpoint                   | Description             |
|--------|----------------------------|-------------------------|
| POST   | `/api/weathertracker/info` | Get weather by location |

### 🔐 Auth API
| Method | Endpoint         | Description                    |
|--------|------------------|--------------------------------|
| POST   | `/auth/login`    | Authenticate user and get token|

## 🌍 External API Usage

- **OpenWeatherMap API** is used to fetch real-time weather data.
- **OpenCageData API** is used for geocoding user-provided addresses.

## ✅ Notes

- This application does **not store latitude and longitude**; instead, users input address strings.
- Users can **register and log in** securely using **JWT token-based authentication**.
- Once authenticated, users can **track current weather by submitting a location name/address**.
- Weather data is retrieved live via OpenWeatherMap and OpenCageData APIs.
- This is a proof-of-concept (no caching implemented).

---

