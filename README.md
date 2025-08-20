# Expense-Tracker
Expense Tracker Application, built in Spring boot Java with applying different concepts as Security using Auth and Spring security filters, use microservices architecture for scalability and enhancement of performance, dockerize the project and connect servers internally with docker network. 

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/MostafaElKaranshawy/Expense-Tracker
cd ./Expense-Tracker
```

### 2. Install Dependencies

- Java JDK (23).
- MySQL Connector.
    - Add the .jar file into your project structure.

### 3. Run the Code

```bash
javac -d out /Backend/src/main/java/com/project/project/*.java
java -cp out /Backend/src/main/java/com/project/project/ProjectApplication.java
```
**Now Your Program Is Ready To Use.**

- You can access the application by navigating to the following URL in your browser or Postman:

```plaintext
http://localhost:8080/{required_endpoint}
```

<hr>

## How to Run (Docker Version)

### 1. Clone the Repository

```bash
git clone https://github.com/MostafaElKaranshawy/Expense-Tracker
cd ./Expense-Tracker
```

### 2. install docker for your system
- [Docker Installation Guide](https://docs.docker.com/get-docker/)

### 3. Check for your .env file

```bash
DP_USERNAME=your_db_username
DB_PASSWORD=your_db_password
DB_HOST=mysql
DB_URL=jdbc:mysql://mysql:3306/library_system
```


### 4. Run the Docker Compose to build and run the containers

- navigate to the Backend directory:
- ```bash
    cd Backend
    ```
- Make sure that the `projectApplication.jar` file is in the root directory of the project.
- Ensure you have a `docker-compose.yml` file in the root directory of your project. If not, create one with the following content:

    ```bash
    docker compose up --build
    ```
- To check that the containers are running, you can use:

  ```bash
  docker ps
  ```
### 5. Access the Application.
- you can access your application by opening your postman and navigating to the ip address of the container.:
- get the ip address of the container by running the following command:
    ```bash
     docker ps
    ```

- save the container name / id of the application container (backend-my_app).

  ```bash
  docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <container_name>
  ```

- Now you can access the application by navigating to the following URL in your browser or Postman:

  ```plaintext
    http://<container_ip>:8080/{required_endpoint}
    ```
<hr>

### Troubleshooting Tips

- If you encounter issues with the database connection, ensure that your `.env` file is correctly configured with the right credentials and database URL.
- If you face any issues with Docker, make sure Docker is running and that you have the necessary permissions to run Docker commands (maybe you need to use `sudo` before docker commands).
- for any other issues, check the docker documentation or the issues section of this repository [Docker Installation Guide](https://docs.docker.com/get-docker/).
- If you need to stop the containers, you can use:

  ```bash
  docker compose down
  ```

<hr>
