using Microsoft.AspNetCore.Mvc;
using WeatherChecker.Services; // Import ASP.NET Core MVC features
using WeatherChecker.Dtos;

namespace WeatherChecker.Controllers
{
    // Marks this class as an API controller
    [ApiController]

    // Route pattern: api/Locations/{action}
    [Route("api/users/[action]")]

    // Controller class that handles location-related API actions
    public class UserController : ControllerBase
    {
        // Declare a private variable for the location service
        private readonly IUserService _userService;



        // Constructor: receives ILocationService via dependency injection
        public UserController(IUserService userService)
        {
            _userService = userService;
        }



        [HttpPost]
        public async Task<IActionResult> register([FromBody] UserDto Request)
        {
            try
            {
                var result = await _userService.RegisterUser(Request);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        // [HttpPost]
        // public async Task<IActionResult> login([FromBody] LoginUserDto Request)
        // {

        //     if (string.IsNullOrWhiteSpace(Request.Name) || string.IsNullOrWhiteSpace(Request.Password))
        //         throw new Exception("Name and password are required.");
        //     try
        //     {
        //         var result = await _userService.LoginUser(Request);
        //         return Ok(result);
        //     }
        //     catch (Exception ex)
        //     {
        //         return BadRequest(ex.Message);
        //     }
        // }


    }


}
