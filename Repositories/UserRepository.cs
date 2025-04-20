using Microsoft.EntityFrameworkCore;
using WeatherChecker.DbContext;
using WeatherChecker.Models;

namespace WeatherChecker.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly UserDbContext _context;

        public UserRepository(UserDbContext context)
        {
            _context = context;
        }
    
        public async Task<User> AddUser(User userModel)
        {
            _context.Users.Add(userModel);
            await _context.SaveChangesAsync();
            return userModel;
        }

        public async Task<User?> GetByName(string name)
        {
            return await _context.Users.FirstOrDefaultAsync(u => u.Name == name);

        }

    }
}
