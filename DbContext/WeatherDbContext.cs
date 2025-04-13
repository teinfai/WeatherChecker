using Microsoft.EntityFrameworkCore;
using WeatherChecker.Models;

namespace WeatherChecker.DbContext
{
    public class WeatherDbContext : Microsoft.EntityFrameworkCore.DbContext
    {
        public WeatherDbContext(DbContextOptions<WeatherDbContext> options)
            : base(options)
        {
        }

        public DbSet<Location> Locations { get; set; }
    }
}