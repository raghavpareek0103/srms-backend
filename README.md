# 🏫 SRMS Backend — School Result Management System

A fully-featured Spring Boot backend for managing school operations.

## 🚀 Tech Stack

- **Java 17** + **Spring Boot 4.0.1**
- **MySQL** — Database
- **Spring Security** + **JWT** — Authentication
- **Spring Data JPA** — ORM
- **JavaMail** — Email notifications
- **iTextPDF** — PDF generation
- **Swagger UI** — API documentation

## ✅ Features

- 🔐 JWT Authentication & Role-based Authorization
- 👨‍🎓 Student Management (CRUD)
- 👨‍🏫 Teacher Management (CRUD)
- 👨‍👩‍👦 Parent Management (CRUD)
- 📅 Attendance Tracking + Email alerts
- 💰 Fee Management + Due notifications
- 📝 Result Management + Auto Grade calculation
- 📢 Announcements + Email blast
- 📄 Report Card PDF generation
- 🔑 Forgot Password / Change Password
- 📖 Swagger UI Documentation

## 👥 Roles

| Role | Access |
|---|---|
| ADMIN | Full access |
| TEACHER | Mark attendance, Add results |
| PARENT | View child data, Download report card |
| STUDENT | View own data |

## 🛠️ Setup

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

**4. Run**
```bash
./mvnw spring-boot:run
```

**5. Swagger UI**
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

### Students
| Method | URL | Access |
|---|---|---|
| POST | `/admin/students` | ADMIN |
| GET | `/admin/students` | ADMIN |
| PUT | `/admin/students/{id}` | ADMIN |
| DELETE | `/admin/students/{id}` | ADMIN |

### Teachers
| Method | URL | Access |
|---|---|---|
| POST | `/teachers` | ADMIN |
| GET | `/teachers` | ADMIN, TEACHER |
| PUT | `/teachers/{id}` | ADMIN |
| DELETE | `/teachers/{id}` | ADMIN |

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

## 📧 Email Setup

1. Gmail → Security → 2-Step Verification ON
2. App Passwords → Generate
3. Paste in application.yaml

## 👨‍💻 Author

**Raghav Pareek** — [github.com/raghavpareek0103](https://github.com/raghavpareek0103)
