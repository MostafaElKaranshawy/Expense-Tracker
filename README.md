## How to Run (Docker Version)

### 1. Clone the Repository

```bash
git clone https://github.com/MostafaElKaranshawy/Expense-Tracker
cd ./Expense-Tracker
```

### 2. install docker for your system
- [Docker Installation Guide](https://docs.docker.com/get-docker/)

### 3. Check for your .env file in each service.

```bash
DB_USERNAME=you_username
DB_PASSWORD=your_password
DB_HOST=expense_tracker_db
DB_URL=jdbc:mysql://expense_tracker_db:3306/expense_tracker
JWT_SECRET=you_token_generator
EUREKA_URL=http://eureka-server:8761/eureka/
```


### 4. Run the Docker Compose to build and run the containers

- Make sure that there is a dockerfile in each service directory
  - eurekaServer
  - Admin-User-Server
  - Expense-Server

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

  - for auth, admin, user endpoints
    ```plaintext
      http://<container_ip>:8081/{required_endpoint}
      ```
  - for expenses endpoints
    ```plaintext
      http://<container_ip>:8082/{required_endpoint}
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
