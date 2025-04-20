import { createSlice } from '@reduxjs/toolkit';
import { jwtDecode } from 'jwt-decode';


const token = localStorage.getItem('token');
const user = token ? jwtDecode(token) : null;

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        token,
        user,
        isAuthenticated: !!token,
    },
    reducers: {
        loginSuccess: (state, action) => {
            state.token = action.payload;
            state.user = jwtDecode(action.payload);
            state.isAuthenticated = true;
            localStorage.setItem('token', action.payload);
        },
        logout: (state) => {
            state.token = null;
            state.user = null;
            state.isAuthenticated = false;
            localStorage.removeItem('token');
        },
    },
});

export const { loginSuccess, logout } = authSlice.actions;
export default authSlice.reducer;
