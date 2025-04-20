namespace WeatherChecker.Models
{
    public class GeoResponse
    {
        public GeoResult[] results { get; set; }
    }

    public class GeoResult
    {
        public Geometry geometry { get; set; }
    }

    public class Geometry
    {
        public double lat { get; set; }
        public double lng { get; set; }
    }
}
