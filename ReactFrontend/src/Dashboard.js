import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';


function Dashboard() {
    const [formData, setFormData] = useState({ address: '', city: '', country: '' });
    const [weatherResult, setWeatherResult] = useState(null);
    const navigate = useNavigate();


    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.address.trim()) {
            toast.error('Address is required');
            return;
        }

        try {
            const token = localStorage.getItem('token');
            const res = await axios.post('http://localhost:5000/api/weathertracker/info', formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
                
            });

       
            setWeatherResult(res.data);
            toast.success('🌍 Weather data loaded!');
        } catch (error) {
            toast.error('❌ Failed to fetch weather');
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        toast.info('👋 Logged out');
        navigate('/');
    };


    return (
        <div className="container mt-5">
            <h2 className="mb-4 text-center">🌤️ Welcome to Demo App</h2>
            <div className="row justify-content-center">
                {/* LEFT FORM */}
                <div className="col-md-8">
                    <div className="alert alert-warning" role="alert">
                        (Due to limitation of 3rd party api)
                        ⚠️ Please enter a simple address (e.g., road, area, city).
                        Do not include full unit numbers or complex building names — OpenCage may reject long/complicated inputs.
                    </div>

                </div>
            </div>
            <div className="d-flex justify-content-end mb-3">
                <button className="btn btn-danger btn-sm" onClick={handleLogout}>
                    🔓 Logout
                </button>
            </div>



            <div className="row justify-content-center">
                {/* LEFT FORM */}
                <div className="col-md-4">
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label>Address</label>
                            <input
                                type="text"
                                name="address"
                                className="form-control"
                                value={formData.address}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="mb-3">
                            <label>City</label>
                            <input
                                type="text"
                                name="city"
                                className="form-control"
                                value={formData.city}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label>Country</label>
                            <input
                                type="text"
                                name="country"
                                className="form-control"
                                value={formData.country}
                                onChange={handleChange}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary w-100">Submit</button>
                    </form>
                </div>

                {/* RIGHT RESULT BOX */}
                <div className="col-md-4">
                    <div
                        className="border rounded p-3"
                        style={{ maxHeight: '400px', overflowY: 'auto', backgroundColor: '#f9f9f9' }}
                    >
                        {weatherResult ? (
                            <>
                                <h5>Weather in {weatherResult.city}, {weatherResult.country}</h5>
                                <p><strong>Description:</strong> {weatherResult.description}</p>
                                <p><strong>Temperature:</strong> {weatherResult.temperature}°C</p>
                                <p><strong>Feels Like:</strong> {weatherResult.feelsLike}°C</p>
                                <p><strong>Humidity:</strong> {weatherResult.humidity}%</p>
                                <p><strong>Wind Speed:</strong> {weatherResult.windSpeed} m/s</p>
                            </>
                        ) : (
                            <p className="text-muted">Weather info will appear here...</p>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
