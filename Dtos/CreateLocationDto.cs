namespace WeatherChecker.Dtos
{
    public class CreateLocationDto
    {
        public string Name { get; set; } = string.Empty;
        public double Latitude { get; set; }
        public double Longitude { get; set; }
    }
}