using System.Security.Cryptography;
using System.Text;

namespace WeatherChecker.Helpers
{
    public static class PasswordHelper
    {
        public static string HashPassword(string plainText)
        {
            using var sha256 = SHA256.Create();
            var bytes = Encoding.UTF8.GetBytes(plainText);
            var hash = sha256.ComputeHash(bytes);
            return Convert.ToBase64String(hash);
        }
    }
}
