using WeatherChecker.Models;

namespace WeatherChecker.Repositories
{
    public interface ILocationRepository
    {
        Task<List<Location>> GetAllAsync();
        Task<Location?> GetByIdAsync(int id);
        Task<Location> AddAsync(Location location);
        Task<bool> UpdateAsync(Location location);
        Task<bool> DeleteAsync(int id);
    }
}
