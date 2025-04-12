using Microsoft.AspNetCore.Mvc;
using WeatherChecker.Dtos;
using WeatherChecker.Services;

namespace WeatherChecker.Controllers
{
    [ApiController]
    [Route("api/[controller]/[action]")]
    public class LocationsController : ControllerBase
    {
        private readonly ILocationService _locationService;

        public LocationsController(ILocationService locationService)
        {
            _locationService = locationService;
        }

        [HttpPost]
        public async Task<IActionResult> AddLocation([FromBody] CreateLocationDto dto)
        {
            try
            {
                var result = await _locationService.AddLocationAsync(dto);
                return CreatedAtAction(nameof(GetLocationById), new { id = result.Id }, result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        public async Task<IActionResult> GetAllLocations()
        {
            try
            {
                var result = await _locationService.GetAllLocationsAsync();
                return Ok(result);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetLocationById(int id)
        {
            try
            {
                var result = await _locationService.GetLocationByIdAsync(id);
                if (result == null) return NotFound();
                return Ok(result);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateLocation(int id, [FromBody] UpdateLocationDto dto)
        {
            try
            {
                var success = await _locationService.UpdateLocationAsync(id, dto);
                if (!success) return NotFound();
                return NoContent();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteLocation(int id)
        {
            try
            {
                var success = await _locationService.DeleteLocationAsync(id);
                if (!success) return NotFound();
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("{id}/weather")]
        public async Task<IActionResult> GetWeatherForLocation(int id)
        {
            try
            {
                var weather = await _locationService.GetWeatherAsync(id);
                if (weather == null) return NotFound();
                return Ok(weather);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }
    }
}
