using WeatherChecker.Dtos;

namespace WeatherChecker.Services
{
    public interface IUserService
    {
        Task<UserDto> RegisterUser(UserDto request);
        Task<TokenDto> LoginUser(LoginUserDto request);

        // New
        Task<List<UserDto>> GetAllUsers();
        Task<bool> DeleteUser(int id);
    }
}
