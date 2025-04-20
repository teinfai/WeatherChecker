using WeatherChecker.Dtos;
using WeatherChecker.Models;
using WeatherChecker.Repositories;
using System.Net.Http.Json;
using Microsoft.Extensions.Options;
using System.Text.Json;
using WeatherChecker.Helpers;

namespace WeatherChecker.Services
{
    public class UserService : IUserService

    {


        private readonly IUserRepository _repo;
        private readonly HttpClient _httpClient;


        public UserService(IUserRepository repo)
        {
            _repo = repo;
            _httpClient = new HttpClient();
        }

        public async Task<UserDto> RegisterUser(UserDto Request)
        {
            var user = new User
            {
                Name = Request.Name,
                Password = PasswordHelper.HashPassword(Request.Password),
                Role = Request.Role,
                Email = Request.Email
            };

            var saved = await _repo.AddUser(user);
            // return ToDto(saved);

            return ToDto(saved);
        }

        private UserDto ToDto(User user)
        {
            return new UserDto
            {
                Name = user.Name,
                Password = user.Password,
                Role = user.Role,
                Email = user.Email
            };
        }




    }


}
