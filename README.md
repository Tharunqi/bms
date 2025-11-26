# 🎬 BookMyShow Clone (Simplified)

A simplified online movie ticket booking system built for academic demonstration. This project allows users to browse movies, theatres, and shows, check seat availability, and book tickets.

The backend is powered by **Java Spring Boot**, utilizing an **H2 In-Memory Database** for data storage, while the frontend interacts via standard **HTML/CSS/JavaScript**.

---

## 🚀 Features

* **Movie Management:** Add and view movie details (Title, Genre, Duration).
* **Theatre Management:** Manage theatre locations.
* **Show Management:** Schedule shows by linking movies to theatres at specific times.
* **Smart Seat Management:** Automatic seat generation for every new show added.
* **Booking System:** Real-time seat availability checks and double-booking prevention.
* **Frontend Interface:** Simple web pages to interact with the API.
* **User Authentication** Login and registration implemented with JWT
* **Booking confirmation** Booking confirmation sent through Email

---

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3+ (Spring Web, Spring Data JPA)
* **Database:** H2 SQL Database (In-Memory)
* **Build Tool:** Maven
* **Frontend:** HTML, CSS, JavaScript (Fetch API)
* **IDE:** IntelliJ IDEA (Recommended)

---

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

1.  **Java Development Kit (JDK) 17** or higher.
2.  **Maven** (usually included with IntelliJ).
3.  A web browser (Google Chrome recommended).

---

## ⚙️ How to Run

### 1. Clone or Download
Download the project files to your local machine.

### 2. Build the Project
Open the project in **IntelliJ IDEA** or a terminal and run the following Maven command to download dependencies:
```bash
mvn clean install
```

### 3\. Start the Application

Run the main application class (usually annotated with `@SpringBootApplication`).

  * **Terminal:** `mvn spring-boot:run`
  * **IntelliJ:** Click the green "Run" button on the main class.

The server will start on `http://localhost:8080`.

### 4\. Access the Application

  * **Frontend:** Open `http://localhost:8080/index.html` in your browser.
  * **H2 Database Console:** Open `http://localhost:8080/h2-console`.
      * *JDBC URL:* `jdbc:h2:mem:testdb` (or check your `application.properties`)
      * *User:* `sa`
      * *Password:* (Leave empty or check properties)

-----

## 🔌 API Endpoints

You can test these endpoints using Postman or the built-in frontend.

### 🎥 Movies

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/movies` | Add a new movie |
| `GET` | `/movies` | Get all movies |

### 🎭 Theatres

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/theatres` | Add a new theatre |
| `GET` | `/theatres` | Get all theatres |

### 🗓️ Shows

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/shows` | Add a show (triggers auto-seat generation) |
| `GET` | `/shows/{id}` | Get specific show details |
| `GET` | `/shows/movie/{id}` | Get shows for a specific movie |

### 🎟️ Booking & Seats

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/seats/show/{showId}` | Get seat availability for a show |
| `POST` | `/booking` | Book seats (Params: `showId`, `bookingId`, `userName`, `seats`) |

-----

## ⚠️ Limitations

  * **Data Persistence:** Since this uses an **H2 In-Memory database**, all data (movies, bookings, etc.) will be wiped when the application is restarted.
  * **Security:** There is no authentication (Login/Signup) or payment gateway integration.
  * **Concurrency:** Basic double-booking prevention is implemented, but advanced locking mechanisms are not included.

-----

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example.bookmyshow
│   │       ├── controller  # REST API Endpoints
│   │       ├── model       # JPA Entities (Movie, Theatre, Seat, etc.)
│   │       ├── repository  # SQL Interfaces
│   │       └── service     # Business Logic
│   └── resources
│       ├── static          # HTML/CSS/JS Frontend files
│       └── application.properties
```

-----

## Team Name
Pragun K Kirani (BT2024176)
Ajay Javali (BT2024079)
S Tharun Kumar (IMT2024010)
Punith M Reddy (IMT2024041)
Siddharth Sivakumar (IMT2024017)
Viraj S Srikar (IMT2024040)
