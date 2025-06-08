
# 🍽️ Food Ordering App

A full-stack food ordering web application built using **Spring Boot**, **MongoDB**, and **JavaScript (JSX)**.  
Users can browse food items, add them to a cart, and place orders seamlessly.

---

## 📚 Table of Contents

- [About the Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## About the Project

This application allows users to:

- View a food menu fetched dynamically from the backend.
- Add or remove items from their cart.
- Calculate cart totals in real time.
- Submit orders with customer details (name, address, city, state).
- Store and manage orders in MongoDB via the backend.

The backend is built with Spring Boot, exposing REST APIs. The frontend uses JavaScript and JSX to build a responsive UI that interacts with the backend APIs.

---

## Tech Stack

- **Frontend:** JavaScript, JSX  
- **Backend:** Spring Boot (Java)  
- **Database:** MongoDB  
- **API:** RESTful JSON communication  

---

## Features

- Display food menu dynamically from backend  
- Add/remove items to/from cart  
- Calculate cart totals on the fly  
- Submit order with customer details  
- Store and manage orders on backend  

---

## Installation

### Backend Setup

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Ensure MongoDB is installed and running locally or remotely, and properly configured in your Spring Boot application properties (`application.properties` or `application.yml`).

Backend server will be available at:  
`http://localhost:8080`

---

### Frontend Setup

The frontend runs on two local ports:

- **Admin panel:** http://localhost:5173  
- **User-facing app:** http://localhost:5174  

Navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the frontend development server:

```bash
npm run dev
```

Open your browser and access the apps via the ports above.

---

### Environment Variables (Optional)

If your backend or database configuration requires environment variables, create a `.env` file in the root of frontend and backend directories accordingly, e.g.,

```
REACT_APP_API_BASE_URL=http://localhost:8080/api
MONGODB_URI=mongodb://localhost:27017/your-db-name
```

Make sure to update your Spring Boot and frontend config to read these variables as needed.

---

## Usage

- Open the user-facing app at `http://localhost:5174`
- Browse the food menu loaded from the backend.
- Add or remove items in your cart.
- Fill out order details: name, address, city, state.
- Submit your order.
- Backend will process and store your order in MongoDB.

---

## API Endpoints

| HTTP Method | Endpoint          | Description             |
|-------------|-------------------|-------------------------|
| GET         | `/api/food`       | Retrieve all food items  |
| POST        | `/api/order`      | Submit a new order       |
| GET         | `/api/order/{id}` | Get order details by ID  |

---

## Screenshots

*(Add your screenshots in `/screenshots` folder and update paths here)*

- Home Page  
- Menu Page  
- Cart Page  
- Order Form  
- Razor Pay Integration  

---

## Contributing

Contributions are welcome!

1. Fork the repository  
2. Create your feature branch (`git checkout -b feature/your-feature`)  
3. Commit your changes (`git commit -m 'Add new feature'`)  
4. Push to the branch (`git push origin feature/your-feature`)  
5. Open a Pull Request  

---

## Contact

Developed by **Vedant Pophali**  
Email: [officialvedantpophali2005@gmail.com](mailto:officialvedantpophali2005@gmail.com)  
LinkedIn: [https://www.linkedin.com/in/vedant-pophali/](https://www.linkedin.com/in/vedant-pophali/)
