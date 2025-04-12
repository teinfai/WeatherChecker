using WeatherChecker.Dtos;
using WeatherChecker.Models;
using WeatherChecker.Repositories;
using System.Net.Http.Json;
using Microsoft.Extensions.Options;

namespace WeatherChecker.Services
{
    public class LocationService : ILocationService
    {
        private readonly ILocationRepository _repo;
        private readonly IOptions<OpenWeatherMapModules> openWeatherMapModules;
        private readonly HttpClient _httpClient;

        public LocationService(
            ILocationRepository repo,
            IOptions<OpenWeatherMapModules> openWeatherMapModules
            )
        {
            _repo = repo;
            this.openWeatherMapModules = openWeatherMapModules;
            _httpClient = new HttpClient();
        }

        public async Task<LocationDto> AddLocationAsync(CreateLocationDto dto)
        {
            var location = new Location
            {
                Name = dto.Name,
                Latitude = dto.Latitude,
                Longitude = dto.Longitude
            };

            var saved = await _repo.AddAsync(location);
            return ToDto(saved);
        }

        public async Task<List<LocationDto>> GetAllLocationsAsync()
        {
            var list = await _repo.GetAllAsync();
            return list.Select(ToDto).ToList();
        }

        public async Task<LocationDto?> GetLocationByIdAsync(int id)
        {
            var location = await _repo.GetByIdAsync(id);
            return location == null ? null : ToDto(location);
        }

        public async Task<bool> UpdateLocationAsync(int id, UpdateLocationDto dto)
        {
            var existing = await _repo.GetByIdAsync(id);
            if (existing == null) return false;

            existing.Name = dto.Name;
            existing.Latitude = dto.Latitude;
            existing.Longitude = dto.Longitude;
            return await _repo.UpdateAsync(existing);
        }

        public async Task<bool> DeleteLocationAsync(int id)
        {
            return await _repo.DeleteAsync(id);
        }

        public async Task<WeatherResultDto?> GetWeatherAsync(int id)
        {
            var location = await _repo.GetByIdAsync(id);
            if (location == null) return null;
            var baseUrl = openWeatherMapModules.Value.URL;
            var apiKey = openWeatherMapModules.Value.Key;

            // var url = $"https://api.openweathermap.org/data/2.5/weather?lat={location.Latitude}&lon={location.Longitude}&appid={_apiKey}&units=metric";
            var url = $"{baseUrl}/data/2.5/weather?lat={location.Latitude}&lon={location.Longitude}&appid={apiKey}&units=metric";

            var response = await _httpClient.GetAsync(url);
            if (!response.IsSuccessStatusCode) return null;

            var json = await response.Content.ReadFromJsonAsync<OpenWeatherResponse>();
            if (json == null) return null;

            return new WeatherResultDto
            {
                Description = json.weather.FirstOrDefault()?.description ?? "",
                Temperature = json.main.temp,
                Humidity = json.main.humidity
            };
        }

        private static LocationDto ToDto(Location location)
        {
            return new LocationDto
            {
                Id = location.Id,
                Name = location.Name,
                Latitude = location.Latitude,
                Longitude = location.Longitude
            };
        }

        private class OpenWeatherResponse
        {
            public List<Weather> weather { get; set; } = new();
            public Main main { get; set; } = new();

            public class Weather
            {
                public string description { get; set; } = string.Empty;
            }

            public class Main
            {
                public double temp { get; set; }
                public double humidity { get; set; }
            }
        }
    }
}
