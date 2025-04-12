# WeatherChecker (.NET + MySQL + Swagger)

A backend-only weather tracking API using .NET Web API, EF Core, and 3rd party OpenWeatherMap APIs.

https://openweathermap.org/api/one-call-3
---

## 🔧 Tech Stack
- .NET 8 Web API
- MySQL + EF Core
- Swagger
- Clean architecture (Controller-Service-Repository)
- External API: OpenWeatherMap

## 💻 Environment Setup

Install .NET 8 SDK  
Download and install from:  
https://dotnet.microsoft.com/en-us/download/dotnet/8.0

Install MySQL Workbench  
https://dev.mysql.com/downloads/workbench/

Install Dotnet Tool
dotnet tool install --global dotnet-ef
Resource: https://learn.microsoft.com/en-us/ef/core/cli/dotnet

## 🚀 Setup Instructions

1. Create the project:
   dotnet new webapi -n WeatherChecker
   cd WeatherChecker

2. Add required packages:
   dotnet add package Microsoft.EntityFrameworkCore -v 8.0.0
   dotnet add package Pomelo.EntityFrameworkCore.MySql -v 8.0.0
   dotnet add package Microsoft.EntityFrameworkCore.Design -v 8.0.0

3. Project structure:
   - DbContext/WeatherDbContext.cs
   - Models/Location.cs
   - Services/LocationService.cs,ILocationService.cs
   - Repositories/LocationRepository.cs,ILocationRepository.cs
   - Controllers/LocationController.cs
   - Dtos/CreateLocationDto.cs, UpdateLocationDto.cs, LocationDto.cs, WeatherResultDto.cs

4. Configure MySQL in appsettings.json:
5. Register WeatherDbContext
6. Apply database migration:
dotnet ef migrations add InitialCreate  
dotnet ef database update

7. Register Dependency INjection services in Program.cs

8. Run the API:
dotnet run  
Open Swagger UI at: https://localhost:<port>/swagger

---

## 📦 API Endpoints

| Method | Endpoint                    | Description                      |
|--------|-----------------------------|----------------------------------|
| POST   | /api/locations              | Add new location                 |
| GET    | /api/locations              | List all saved locations         |
| GET    | /api/locations/{id}         | Get location by ID               |
| PUT    | /api/locations/{id}         | Update location info             |
| DELETE | /api/locations/{id}         | Delete location by ID            |
| GET    | /api/locations/{id}/weather | Get current weather from OWM API |

---

## ✅ Notes
- Coordinates are stored for each location to support weather lookups.
- Weather data is fetched live via OpenWeatherMap (no caching in PoC).