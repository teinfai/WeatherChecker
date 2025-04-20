namespace WeatherChecker.Dtos
{
    public class WeatherResultDto
    {
        public string Description { get; set; } = string.Empty;
        public double Temperature { get; set; }
        public double Humidity { get; set; }
    }
}
