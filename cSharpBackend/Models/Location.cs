using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WeatherChecker.Models
{
    public class Location : Entity<int>
    {
        public string Name { get; set; }          // e.g., "Ipoh"

        public string CountryCode { get; set; }   // e.g., "MY"

        public string State { get; set; }         // e.g., "Perak"

        public double Latitude { get; set; }               // Needed for OpenWeatherMap

        public double Longitude { get; set; }              // Needed for OpenWeatherMap

        public DateTime CreatedAt { get; set; } = DateTime.UtcNow; // Custom Timezone

        public string Address {get;set;}
        public string City {get;set;}
        public string Country {get;set;}
    }

    
}