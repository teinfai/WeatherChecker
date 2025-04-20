namespace WeatherChecker.Dtos
{
    public class LocationDto
    {
        public int Id { get; set; }
        public string Name { get; set; } = string.Empty;
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        // public string City { get; set; }
        // public string Country { get; set; }
        // public string Address { get; set; }

        public string City {get; set;}
        public string Country {get; set;}
        public string Address {get; set;}
    }
}
