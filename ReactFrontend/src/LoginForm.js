// LoginForm.js
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginSuccess } from './slices/authSlice';
import { toast } from 'react-toastify';
import RegisterModal from './Modal/RegisterModal';

function LoginForm() {
    const [formData, setFormData] = useState({ name: '', password: '' });
    const [showRegister, setShowRegister] = useState(false);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            // 1. Login request
            console.log(formData);


            const res = await axios.post('http://localhost:5000/auth/login', formData);
            const token = res.data.token;
            // console.log(token);
            // 2. Clear old token
            localStorage.removeItem('token');

            // 3. Store new token
            localStorage.setItem('token', token);

            // 4. Dispatch to Redux
            dispatch(loginSuccess(token));

            // 5. Success notification
            toast.success('🎉 Login successful!');
            navigate('/dashboard');
        } catch (err) {
            toast.error('❌ Login failed: ' + (err.response?.data || err.message));
        }
    };


    return (
        <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
            <div className="container p-4 shadow rounded bg-white" style={{ maxWidth: 400 }}>
                <h3 className="mb-4 text-center">Login</h3>
                <form onSubmit={handleLogin}>
                    <div className="mb-3">
                        <label>Username</label>
                        <input
                            type="text"
                            name="name"
                            className="form-control"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label>Password</label>
                        <input
                            type="password"
                            name="password"
                            className="form-control"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <button type="submit" className="btn btn-primary w-100">
                                Login
                            </button>
                        </div>
                        <div className="col-md-6">
                            <button type="button" onClick={() => setShowRegister(true)} className="btn btn-secondary w-100">
                                Register
                            </button>
                        </div>
                    </div>
                </form>
                <RegisterModal show={showRegister} handleClose={() => setShowRegister(false)} />
            </div>
        </div>
    );
}

export default LoginForm;
