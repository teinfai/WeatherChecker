using Microsoft.EntityFrameworkCore;
using WeatherChecker.DbContext;
using WeatherChecker.Models;

namespace WeatherChecker.Repositories
{
    public class LocationRepository : ILocationRepository
    {
        private readonly WeatherDbContext _context;

        public LocationRepository(WeatherDbContext context)
        {
            _context = context;
        }

        public async Task<List<Location>> GetAllAsync()
        {
            return await _context.Locations.ToListAsync();
        }

        public async Task<Location?> GetByIdAsync(int id)
        {
            return await _context.Locations.FindAsync(id);
        }

        public async Task<Location> AddAsync(Location location)
        {
            _context.Locations.Add(location);
            await _context.SaveChangesAsync();
            return location;
        }

        public async Task<bool> UpdateAsync(Location location)
        {
            _context.Locations.Update(location);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            var location = await _context.Locations.FindAsync(id);
            if (location == null) return false;

            _context.Locations.Remove(location);
            return await _context.SaveChangesAsync() > 0;
        }
    }
}
