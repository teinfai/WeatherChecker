using Microsoft.AspNetCore.Mvc; // Import ASP.NET Core MVC features
using WeatherChecker.Dtos;       // Import Data Transfer Objects (DTOs)
using WeatherChecker.Services;   // Import service interfaces

namespace WeatherChecker.Controllers
{
    // Marks this class as an API controller
    [ApiController]

    // Route pattern: api/Locations/{action}
    [Route("api/[controller]/[action]")]

    // Controller class that handles location-related API actions
    public class LocationsController : ControllerBase
    {
        // Declare a private variable for the location service
        private readonly ILocationService _locationService;

        // Constructor: receives ILocationService via dependency injection
        public LocationsController(ILocationService locationService)
        {
            _locationService = locationService;
        }

        // POST: Add a new location
        // [HttpPost]
        // public async Task<IActionResult> AddLocation([FromBody] CreateLocationDto dto)
        // {
        //     try
        //     {
        //         // Call the service to add location
        //         var result = await _locationService.AddLocationAsync(dto);

        //         // Return 201 Created with the new location and its ID
        //         return CreatedAtAction(nameof(GetLocationById), new { id = result.Id }, result);
        //     }
        //     catch (Exception ex)
        //     {
        //         // Return 400 Bad Request with error message
        //         return BadRequest(ex.Message);
        //     }
        // }

        // // GET: Get all locations
        // [HttpGet]
        // public async Task<IActionResult> GetAllLocations()
        // {
        //     try
        //     {
        //         // Call the service to get all locations
        //         var result = await _locationService.GetAllLocationsAsync();

        //         // Return 200 OK with the result
        //         return Ok(result);
        //     }
        //     catch (Exception ex)
        //     {
        //         // Return 500 Internal Server Error
        //         return StatusCode(500, ex.Message);
        //     }
        // }

        // // GET: Get location by ID
        // [HttpGet("{id}")]
        // public async Task<IActionResult> GetLocationById(int id)
        // {
        //     try
        //     {
        //         // Get location by ID
        //         var result = await _locationService.GetLocationByIdAsync(id);

        //         // If not found, return 404
        //         if (result == null) return NotFound();

        //         // Return 200 OK with location data
        //         return Ok(result);
        //     }
        //     catch (Exception ex)
        //     {
        //         return StatusCode(500, ex.Message);
        //     }
        // }

        // // PUT: Update location by ID
        // [HttpPut("{id}")]
        // public async Task<IActionResult> UpdateLocation(int id, [FromBody] UpdateLocationDto dto)
        // {
        //     try
        //     {
        //         // Try to update location
        //         var success = await _locationService.UpdateLocationAsync(id, dto);

        //         // If update failed (not found), return 404
        //         if (!success) return NotFound();

        //         // Return 204 No Content on success
        //         return NoContent();
        //     }
        //     catch (Exception ex)
        //     {
        //         return BadRequest(ex.Message);
        //     }
        // }

        // // DELETE: Delete location by ID
        // [HttpDelete("{id}")]
        // public async Task<IActionResult> DeleteLocation(int id)
        // {
        //     try
        //     {
        //         // Try to delete location
        //         var success = await _locationService.DeleteLocationAsync(id);

        //         // If not found, return 404
        //         if (!success) return NotFound();

        //         // Return 204 No Content on success
        //         return NoContent();
        //     }
        //     catch (Exception ex)
        //     {
        //         return StatusCode(500, ex.Message);
        //     }
        // }

        // // GET: Get weather info for a specific location
        // [HttpGet("{id}/weather")]
        // public async Task<IActionResult> GetWeatherForLocation(int id)
        // {
        //     try
        //     {
        //         // Get weather data for the location
        //         var weather = await _locationService.GetWeatherAsync(id);

        //         // If no data, return 404
        //         if (weather == null) return NotFound();

        //         // Return 200 OK with weather data
        //         return Ok(weather);
        //     }
        //     catch (Exception ex)
        //     {
        //         return StatusCode(500, ex.Message);
        //     }
        // }


        // POST: Add a new location
        // [HttpPost]
        // public async Task<IActionResult> AddLocation([FromBody] CreateLocationDto dto)
        // {
        //     try
        //     {
        //         // Call the service to add location
        //         var result = await _locationService.AddLocationAsync(dto);

        //         // Return 201 Created with the new location and its ID
        //         return CreatedAtAction(nameof(GetLocationById), new { id = result.Id }, result);
        //     }
        //     catch (Exception ex)
        //     {
        //         // Return 400 Bad Request with error message
        //         return BadRequest(ex.Message);
        //     }
        // }

        [HttpGet]
        public async Task<IActionResult> SearchWeatherWithAddress([FromQuery] SearchDto dto)
        {
            try
            {
                var result = await _locationService.GetWeatherByAddressAsync(dto);

                if (result == null)
                    return NotFound("Could not get weather or geolocation.");
                    
                return Ok(result);
            }
            catch (Exception ex)
            {
                // Return 400 Bad Request with error message
                return BadRequest(ex.Message);
            }
        }
    }
}
