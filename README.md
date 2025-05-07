# Movie Ticket Management System (MTMS)

## Overview

The Movie Ticket Management System (MTMS) is a web-based application designed to simplify the process of browsing, booking, and managing movie tickets. The system serves both users and administrators, offering an efficient platform for users to book tickets and for administrators to manage movie listings, theatre schedules, and seat availability. The system ensures smooth integration of booking functionalities, providing an intuitive user interface and robust backend for seamless movie ticket management.

## Features

### Admin Functionalities:

- Manages movies, theatres, screens, showtimes, and seat layouts.
- Handles user management and roles, including managing user profiles and bookings.
- Monitors system performance and manages available shows.

### User Functionalities:

- Registers, logs in, and manages personal profiles.
- Browses movies by title, description, genre, rating, and more.
- Selects city, theatre, and showtime for ticket booking.
- Selects seats from a dynamic seat layout.
- Books and cancels tickets.
- Views booking history.

### Booking System:

- Real-time seat selection based on available showtimes and theatre screens.

### Admin Dashboard:

- Access to movie, theatre, and screen management.
- Perform CRUD Operations on Movies, Theatres, Screens and Showtimings.

## Technologies Used:

- **Frontend:** Angular 17 (for dynamic UI rendering and user interactions)
- **Backend:** Spring Boot (for REST API development and handling business logic)
- **Database:** MySQL (for structured data storage and management)
- **API Testing Tool:** Postman (for testing REST APIs)
- **Version Control:** Git & GitHub (for collaborative development and version tracking)
- **Authentication & Security:** Spring Security (for user authentication and authorization)

## System Flow:

- **User Authentication:** Users (Admin and general users) log in using secure credentials, with role-based access control.
- **Role-Based Access Control:** Access is granted based on user roles (Admin, User).
- **Movie Booking & Seat Selection:** Users browse movies, select a showtime and theatre, choose seats, and complete ticket booking.
- **Admin Movie & Theatre Management:** Admins can add, update, and remove movies, theatres, screens, and showtimes.
- **Booking History & Reports:** Users view their booking history, while admins monitor booking data and earnings.

### Conclusion:
The **Movie Ticket Management System** offers a seamless and intuitive platform for both users and administrators. It enhances the movie-going experience by providing real-time movie booking, dynamic seat selection, and efficient admin management of movie schedules and bookings. With a well-structured backend and user-friendly frontend, the system ensures smooth user interactions, data management, and future scalability with the planned payment integration.
