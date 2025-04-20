using WeatherChecker.Dtos;

namespace WeatherChecker.Services
{
    public interface IUserService
    {
      
        Task<UserDto> RegisterUser(UserDto request);

    }
}
