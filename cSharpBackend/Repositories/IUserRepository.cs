using WeatherChecker.Models;

namespace WeatherChecker.Repositories
{
    public interface IUserRepository
    {
        Task<User> AddUser(User userModel);
        Task<User?> GetByName(string name);

        // New
        Task<List<User>> GetAllUsers();
        Task<bool> DeleteUser(int id);
    }
}
