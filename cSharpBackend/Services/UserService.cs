using WeatherChecker.Dtos;
using WeatherChecker.Models;
using WeatherChecker.Repositories;
using System.Net.Http.Json;
using Microsoft.Extensions.Options;
using System.Text.Json;
using WeatherChecker.Helpers;
using WeatherChecker.Modules;

namespace WeatherChecker.Services
{
    public class UserService : IUserService

    {
        private readonly IUserRepository _repo;
        private readonly HttpClient _httpClient;

        private readonly JwtTokenGenerator _jwtTokenGenerator;

        public UserService(
            IUserRepository repo,
            JwtTokenGenerator jwtTokenGenerator
            )
        {
            _repo = repo;
            _httpClient = new HttpClient();
            _jwtTokenGenerator = jwtTokenGenerator;

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

        public async Task<TokenDto> LoginUser(LoginUserDto Request)
        {
            var findUser = await _repo.GetByName(Request.Name);

            if (findUser == null)
                throw new Exception("Invalid username or password.");

            var userHashedPassword = PasswordHelper.HashPassword(Request.Password);

            if (findUser.Password != userHashedPassword)
                throw new Exception("Invalid username or password.");

            var token = _jwtTokenGenerator.GenerateToken(findUser.Id, findUser.Name);

            return new TokenDto
            {
                Token = token,
                Name = findUser.Name,
            };

        }


        public async Task<List<UserDto>> GetAllUsers()
        {
            var users = await _repo.GetAllUsers();
            return users.Select(ToDto).ToList();
        }

        public async Task<bool> DeleteUser(int id)
        {
            return await _repo.DeleteUser(id);
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
