using WeatherChecker.Models;

namespace WeatherChecker.Repositories
{
    public interface IUserRepository
    {
        Task<User> AddUser(User usermodel);
    }
}
