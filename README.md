# Online Examination System (OES)

A web-based **Online Examination System** built with **Spring Boot** that allows administrators to create and manage exams and questions, and enables students to take exams and view results through a simple web interface.

---

## ✨ Features

### 👩‍💼 Admin

* Secure login
* Create and manage exams
* Add and view questions per exam
* View student results

### 👨‍🎓 Student

* Secure login
* View available exams
* Take exams online
* View results after submission

### 🔐 Security

* Role-based access (Admin / Student)
* Spring Security configuration

---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security
* **Database:** MySQL (JPA / Hibernate)
* **Frontend:** Thymeleaf, HTML
* **Build Tool:** Maven

---

## 📁 Project Structure

```
src/main/java/com/prasanna/OnlineExaminationSystem
├── config        # Security configuration
├── controller    # MVC controllers (Admin, Student, Login)
├── entity        # JPA entities (User, Exam, Question, Result)
├── repository    # JPA repositories
├── service       # Business logic
```

Templates are located under:

```
src/main/resources/templates
```

---

## ⚙️ Configuration

Update the database configuration in:

```
src/main/resources/application.properties
```

Make sure:

* MySQL is running
* The database exists
* Credentials are correct

Hibernate is set to auto-create tables on startup.

---

## ▶️ Running the Application

### Prerequisites

* Java 8 or higher
* Maven
* MySQL

### Steps

```bash
# Clone the repository
git clone <your-repo-url>

# Navigate to the project
cd OnlineExaminationSystem

# Run the application
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

---

## 🚀 Future Enhancements

* Timer-based exams
* Question randomization
* Result analytics
* Pagination and search
* Improved UI/UX

---

## 📜 License

This project is for educational purposes. Feel free to modify and extend it.

---

## 🙌 Author

Developed by **Prasanna Lakshmi Motati**
