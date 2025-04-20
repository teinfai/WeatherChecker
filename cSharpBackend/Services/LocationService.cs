using WeatherChecker.Dtos;
using WeatherChecker.Models;
using WeatherChecker.Repositories;
using System.Net.Http.Json;
using Microsoft.Extensions.Options;
using System.Text.Json;


namespace WeatherChecker.Services
{
    public class LocationService : ILocationService
    {
        private readonly ILocationRepository _repo;
        private readonly IOptions<OpenWeatherMapModules> openWeatherMapModules;
        private readonly IOptions<OpenCageDataMapModules> openCageDataMapModules;
        private readonly HttpClient _httpClient;

        public LocationService(
            ILocationRepository repo,
            IOptions<OpenWeatherMapModules> openWeatherMapModules,
            IOptions<OpenCageDataMapModules> openCageDataMapModules
            )
        {
            _repo = repo;
            this.openWeatherMapModules = openWeatherMapModules;
            this.openCageDataMapModules = openCageDataMapModules;
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
                Longitude = location.Longitude,
                Address = location.Address,
                City = location.City,
                Country = location.Country,
            };
        }




        public async Task<WeatherResultDto> GetWeatherByAddressAsync(SearchDto dto)
        {
            var openWeatherBaseUrl = openWeatherMapModules.Value.URL;
            var openWeatherApiKey = openWeatherMapModules.Value.Key;

            var openCageDataBaseUrl = openCageDataMapModules.Value.URL;
            var openCageDataApiKey = openCageDataMapModules.Value.Key;

            var City = dto.City == "string" ? "" : dto.City;
            var Country = dto.Country == "string" ? "" : dto.Country;

            var fullAddress = $"{dto.Address}, {City}, {Country}";

            // // var url = $"https://api.openweathermap.org/data/2.5/weather?lat={location.Latitude}&lon={location.Longitude}&appid={_apiKey}&units=metric";
            // var url = $"{openWeatherBaseUrl}/data/2.5/weather?lat={location.Latitude}&lon={location.Longitude}&appid={apiKey}&units=metric";

            // var address = "1600 Pennsylvania Ave NW, Washington, DC";  // Hardcoded address
            // var url = $"{openCageDataBaseUrl}/geocode/v1/json?q={address}&key={openCageDataApiKey}";

            var url = $"{openCageDataBaseUrl}/geocode/v1/json?q={fullAddress}&key={openCageDataApiKey}";

            var response = await _httpClient.GetAsync(url);
            if (!response.IsSuccessStatusCode) return null;


            if (response.IsSuccessStatusCode)
            {
                var openCageResponse = await response.Content.ReadFromJsonAsync<GeoResponse>();

                if (openCageResponse != null && openCageResponse.results != null && openCageResponse.results.Any())
                {
                    var geoInfo = openCageResponse.results[0].geometry;
                    var ReturnLng = geoInfo.lng;
                    var ReturnLat = geoInfo.lat;

                    // Console.WriteLine($"Latitude: {ReturnLng}, Longitude: {ReturnLat}");

                    var Weathrurl = $"{openWeatherBaseUrl}/data/2.5/weather?lat={ReturnLat}&lon={ReturnLng}&appid={openWeatherApiKey}&units=metric";

                    var WeatherInfoResponse = await _httpClient.GetAsync(Weathrurl);

                    if (WeatherInfoResponse.IsSuccessStatusCode)
                    {

                        var openWeatherResponse = await WeatherInfoResponse.Content.ReadFromJsonAsync<OpenWeatherResponse>();
                        if (openWeatherResponse != null)
                        {

                            // Console.WriteLine($"Response weather: {openWeatherResponse}");

                            return new WeatherResultDto
                            {
                                Description = openWeatherResponse.weather.FirstOrDefault()?.description ?? "",
                                Temperature = openWeatherResponse.main.temp,
                                Humidity = openWeatherResponse.main.temp
                            };

                        }
                        else
                        {
                            return null;
                        }


                    }



                }
                else
                {
                    return null;
                }


            }



            // var location = geoResponse.results[0].geometry;

            // // Step 2: Call OpenWeather API with Latitude and Longitude
            // string weatherApiKey = "YOUR_OPENWEATHER_API_KEY";
            // var weatherUrl = $"https://api.openweathermap.org/data/2.5/weather?lat={location.lat}&lon={location.lng}&appid={weatherApiKey}&units=metric";

            // var rawWeather = await _httpClient.GetFromJsonAsync<dynamic>(weatherUrl);

            // // Step 3: Return the combined result with both Location and Weather Data
            // return new WeatherResultDto
            // {
            //     Location = new LocationDto
            //     {
            //         City = locationDto.City,
            //         Country = locationDto.Country,
            //         Address = locationDto.Address,
            //         Latitude = location.lat,
            //         Longitude = location.lng
            //     },
            //     Weather = new WeatherInfoDto
            //     {
            //         Description = rawWeather.weather[0].description,
            //         Temperature = rawWeather.main.temp
            //     }
            // };

            return null;

        }


    }
}
