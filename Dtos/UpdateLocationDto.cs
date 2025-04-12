namespace WeatherChecker.Dtos
{
    public class UpdateLocationDto
    {
        public string Name { get; set; } = string.Empty;
        public double Latitude { get; set; }
        public double Longitude { get; set; }
    }
}
