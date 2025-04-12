using WeatherChecker.Dtos;

namespace WeatherChecker.Services
{
    public interface ILocationService
    {
        Task<LocationDto> AddLocationAsync(CreateLocationDto dto);
        Task<List<LocationDto>> GetAllLocationsAsync();
        Task<LocationDto?> GetLocationByIdAsync(int id);
        Task<bool> UpdateLocationAsync(int id, UpdateLocationDto dto);
        Task<bool> DeleteLocationAsync(int id);
        Task<WeatherResultDto?> GetWeatherAsync(int id);
    }
}
