# 🏫 SRMS Backend — School Result Management System

A fully-featured, production-ready Spring Boot backend for managing school operations.

## 🚀 Tech Stack

- **Java 17** + **Spring Boot 4.0.1**
- **MySQL** — Database
- **Spring Security** + **JWT** — Authentication
- **Spring Data JPA** — ORM + Pagination
- **JavaMail** — Email notifications
- **iTextPDF** — PDF generation
- **Swagger UI** — API documentation
- **Docker** + **Docker Compose** — Containerization

## ✅ Features

- 🔐 JWT Authentication & Role-based Authorization
- 👨‍🎓 Student Management (CRUD + Pagination)
- 👨‍🏫 Teacher Management (CRUD)
- 👨‍👩‍👦 Parent Management (CRUD)
- 📅 Attendance Tracking + Email alerts
- 💰 Fee Management + Due notifications
- 📝 Result Management + Auto Grade calculation
- 📢 Announcements + Email blast to all parents
- 📄 Report Card PDF generation
- 🔑 Forgot Password / Change Password
- 📖 Swagger UI Documentation
- 🐳 Docker + Docker Compose support

## 👥 Roles

| Role | Access |
|---|---|
| ADMIN | Full access |
| TEACHER | Mark attendance, Add results |
| PARENT | View child data, Download report card |
| STUDENT | View own data |

## 🛠️ Setup Instructions

**1. Clone the repo**
```bash
git clone https://github.com/raghavpareek0103/srms-backend.git
cd srms-backend/srms-backend
```

**2. Create application.yaml**

Copy `application-example.yaml` to `src/main/resources/application.yaml` and fill your values.

**3. Setup MySQL**
```sql
CREATE DATABASE srms_db;
```

**4. Create .env file**
```env
DB_USERNAME=root
DB_PASSWORD=YOUR_PASSWORD
JWT_SECRET=YOUR_JWT_SECRET
JWT_EXPIRATION=36000000
MAIL_USERNAME=YOUR_EMAIL
MAIL_PASSWORD=YOUR_APP_PASSWORD
```

**5. Run normally**
```bash
./mvnw spring-boot:run
```

**6. Run with Docker**
```bash
./mvnw clean package -DskipTests
docker-compose up --build
```

**7. Access Swagger UI**
http://localhost:8080/swagger-ui.html

## 📡 API Endpoints

### Auth
| Method | URL | Access |
|---|---|---|
| POST | `/auth/login` | Public |
| POST | `/auth/register` | ADMIN |
| GET | `/auth/me` | All |
| POST | `/auth/change-password` | All |
| POST | `/auth/forgot-password` | Public |

### Students (with Pagination)
| Method | URL | Access |
|---|---|---|
| POST | `/admin/students` | ADMIN |
| GET | `/admin/students?page=0&size=10&sortBy=name&direction=asc` | ADMIN |
| GET | `/admin/students/all` | ADMIN |
| GET | `/admin/students/{id}` | ADMIN |
| PUT | `/admin/students/{id}` | ADMIN |
| DELETE | `/admin/students/{id}` | ADMIN |

### Teachers
| Method | URL | Access |
|---|---|---|
| POST | `/teachers` | ADMIN |
| GET | `/teachers` | ADMIN, TEACHER |
| PUT | `/teachers/{id}` | ADMIN |
| DELETE | `/teachers/{id}` | ADMIN |

### Parents
| Method | URL | Access |
|---|---|---|
| POST | `/parents` | ADMIN |
| GET | `/parents` | ADMIN |
| GET | `/parents/{id}` | ADMIN, PARENT |
| PUT | `/parents/{id}` | ADMIN |
| DELETE | `/parents/{id}` | ADMIN |

### Attendance
| Method | URL | Access |
|---|---|---|
| POST | `/attendance` | TEACHER |
| GET | `/attendance/{studentId}` | PARENT, STUDENT |
| PUT | `/attendance/{id}` | TEACHER |
| DELETE | `/attendance/{id}` | ADMIN |

### Fees
| Method | URL | Access |
|---|---|---|
| POST | `/fees` | ADMIN |
| GET | `/fees/{studentId}` | PARENT, STUDENT |
| PUT | `/fees/{id}` | ADMIN |
| DELETE | `/fees/{id}` | ADMIN |

### Results
| Method | URL | Access |
|---|---|---|
| POST | `/results` | TEACHER |
| GET | `/results/{studentId}` | PARENT, STUDENT |
| PUT | `/results/{id}` | TEACHER |

### Announcements
| Method | URL | Access |
|---|---|---|
| POST | `/announcements` | ADMIN |
| GET | `/announcements` | PARENT, STUDENT |

### Report Card
| Method | URL | Access |
|---|---|---|
| GET | `/report-card/{studentId}` | PARENT, STUDENT |
| POST | `/report-card/email/{studentId}` | ADMIN |

## 🔑 Default Admin Credentials

Username: admin
Password: Admin@123

## 📧 Email Setup (Gmail)

1. Gmail → Security → 2-Step Verification ON
2. App Passwords → Generate
3. Paste in `.env` file as `MAIL_PASSWORD`

## 🐳 Docker

```bash
# Build
./mvnw clean package -DskipTests

# Run
docker-compose up --build

# Stop
docker-compose down
```

## 👨‍💻 Author

**Raghav Pareek** — [github.com/raghavpareek0103](https://github.com/raghavpareek0103)
