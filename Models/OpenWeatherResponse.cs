namespace WeatherChecker.Models
{
    public class OpenWeatherResponse
    {
        public List<Weather> weather { get; set; } = new();
        public Main main { get; set; } = new();

        public class Weather
        {
            public string description { get; set; } = string.Empty;
        }

        public class Main
        {
            public double temp { get; set; }
            public double humidity { get; set; }
        }
    }

}