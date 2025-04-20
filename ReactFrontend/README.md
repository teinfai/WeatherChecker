# 🌐 WeatherChecker Frontend (ReactJS)

A modern frontend app built with **ReactJS**, styled with **Bootstrap**, and powered by **Redux Toolkit** for state management. This frontend connects to a secured .NET Web API backend to provide JWT-authenticated weather tracking functionality.

---

## 🧱 Tech Stack

- ReactJS (via Create React App)
- Redux Toolkit (global state management)
- React-Bootstrap + Bootstrap (UI framework)
- Axios (HTTP client with JWT support)

---

## 🚀 Setup Instructions

1. **Install dependencies:**
```bash
npm install
```

2. **Start the development server:**
```bash
npm start
```
Runs on: [http://localhost:3000](http://localhost:3000)

---

## 🔐 Authentication Flow

- Users log in with credentials (name & password)
- Upon success, backend returns a **JWT token**
- Token is stored in `localStorage` for session persistence
- Redux manages auth state for UI & requests
- Axios attaches the token to `Authorization: Bearer <token>` in headers

---

## ✨ Features

- 🔑 **User Login & Register** UI using React-Bootstrap
- 🌦️ **Weather Search by Address** via OpenCageData → OpenWeatherMap
- 📦 **Secure API communication** with backend via stored JWT
- 🧠 **Session tracking** and conditional UI based on Redux state

---

## 📂 Folder Highlights

```
- public/
- src/
  - Modal/
    - RegisterModal.js
  - slices/
    - authSlice.js
  - App.css
  - App.js
  - App.test.js
  - Dashboard.js
  - index.css
  - index.js
  - LoginForm.js
  - logo.svg
  - reportWebVitals.js
  - setupTests.js
  - store.js
- .gitignore
- images.jpeg
- package.json
- package-lock.json
- README.md
```

---

## ✅ Notes

- React communicates with a secured **C# .NET backend** or **Java Spring backend**
- Ensure **CORS** is enabled on the both backend for localhost access
- Tokens are stored securely in `localStorage` and reused for authenticated requests
- Backend uses **JWT authentication**, OpenCageData for geocoding, and OpenWeatherMap for weather info

- Make sure CORS is enabled in your .NET backend or Java backend
- Tokens are automatically reused while valid

---

