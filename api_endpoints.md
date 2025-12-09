# API Endpoints Report

This document lists all the API endpoints found in the project, along with their HTTP method, URL, output type, and functionality.

## AuthController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `POST` | `/register` | `ResponseEntity<?>` (JSON Map) | Registers a new user with username and password. Checks if user exists. |
| `POST` | `/login` | `ResponseEntity<?>` (JSON Map) | Authenticates a user. Returns a JWT token if successful, or an error message. |

## BookingController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `POST` | `/booking` | `Booking` | Creates a new booking for a show with specified seats and sends a confirmation email to the client. |

## MovieController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `POST` | `/movies` | `Movie` | Adds a new movie to the database. |
| `GET` | `/movies` | `List<Movie>` | Retrieves a list of all movies. |
| `GET` | `/movies/{id}` | `Movie` | Retrieves a specific movie by its ID. |

## SeatController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `GET` | `/seats/show/{showId}` | `List<Seat>` | Retrieves all seats associated with a specific show ID. |

## ShowController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `POST` | `/shows` | `Show` | Adds a new show. Fetches associated Movie and Theatre details and auto-generates seats. |
| `GET` | `/shows/movie/{movieId}` | `List<Show>` | Retrieves all shows for a specific movie ID. |
| `GET` | `/shows/{id}` | `Show` | Retrieves a specific show by its ID. |
| `GET` | `/shows` | `List<Show>` | Retrieves a list of all shows. |

## TheatreController
| Method | URL | Output Type | Functionality |
| :--- | :--- | :--- | :--- |
| `POST` | `/theatres` | `Theatre` | Adds a new theatre to the database. |
| `GET` | `/theatres` | `List<Theatre>` | Retrieves a list of all theatres. |
| `GET` | `/theatres/{id}` | `Theatre` | Retrieves a specific theatre by its ID. |
