namespace WeatherChecker.Dtos
{
    public class WeatherInfoDto
    {
        public string Description { get; set; }
        public double Temperature { get; set; }
        public int Humidity { get; set; }
        public double WindSpeed { get; set; }
    }
}