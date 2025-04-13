using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace WeatherChecker.Dtos
{
    public class SearchDto
    {

        [Required]
        [DefaultValue("")]
        public string Address { get; set; }

        [DefaultValue("")]
        public string City { get; set; }

        [DefaultValue("")]
        public string Country { get; set; }

    }
}