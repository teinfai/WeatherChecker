using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WeatherChecker.Models
{
    public class User : Entity<int>
    {
        public string Email { get; set; }

        public string Name { get; set; } 
    
        public string Password { get; set; } 
    
        public string Role { get; set; } 
    }
}