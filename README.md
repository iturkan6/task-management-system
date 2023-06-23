
  <h1>Task Management System - MVP of Jira</h1>
  <h2>Table of Contents</h2>
  <ol>
    <li><a href="#overview">Overview</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#configuration">Configuration</a></li>
    <li><a href="#running-the-application">Running the Application</a></li>
    <li><a href="#api-documentation">API Documentation</a></li>
  </ol>
  <h2 id="overview">Overview</h2>
  <p>
    The Task Management System is a simplified version of Jira, designed to manage tasks.
    It is built using the Spring Boot framework, providing a robust and scalable Java-based application.
  </p>
  <h3>Key Features:</h3>
  <ul>
    <li>User registration and authentication</li>
    <li>Tasks creation and management</li>
  </ul>
  <h2 id="installation">Installation</h2>
  <ol>
    <li>Clone the repository: <code>git clone https://github.com/iturkan6/task-management-system.git</code></li>
    <li>Navigate to the project directory: <code>cd task-management-system</code></li>
    <li>Build the project using Maven: <code>mvn clean install</code></li>
  </ol>
  <h2 id="configuration">Configuration</h2>
  <p>
    The application requires certain configurations to run properly.
    You can customize these configurations by modifying the <code>application.yaml</code> file located in the <code>src/main/resources</code> directory.
    The important configuration properties to consider are:
  </p>
  <ol>
    <li><code>server.port</code>: The port on which the application will run. You can set it as an environment variable named <code>{PORT}</code></li>
    <li><code>spring.datasource.url</code>: The URL of the database. You can set it as an environment variable named <code>{URL}</code></li>
    <li><code>spring.datasource.username</code> and <code>spring.datasource.password</code>: The credentials for the database connection.
    You can set them as environment variables named <code>{USERNAME}</code> and <code>{PASSWORD}</code> respectively.</li>
  </ol>
  <p>Make sure to update these values according to your environment.</p>
  <h2 id="running-the-application">Running the Application</h2>
  <ol>
    <li>Make sure you have Java Development Kit (JDK) installed on your system.</li>
    <li>Execute the following command: <code>java -jar target/task-management-system.jar</code></li>
  </ol>
  <p>Upon successful execution, the application will be up and running on <code>http://localhost:8080/*</code>.</p>
  <h2 id="api-documentation">API Documentation</h2>
  <p>
    The application provides a set of APIs for managing tasks and projects.
    You can find detailed documentation for these APIs in the <a href="/api-docs">API Documentation</a> section of the application.
  </p>
  <p>Available Endpoints:</p>
  <ul>
    <li><code>POST /auth/register</code>: Register new user.</li>
    <li><code>POST /auth/login</code>: Verify user and get access token.</li>
    <br>
    <li><code>GET /tasks</code>: Retrieve all tasks.</li>
    <li><code>GET /tasks/{id}</code>: Retrieve a specific task.</li>
    <li><code>POST /tasks</code>: Create a new task.</li>
    <li><code>PUT /tasks/{id}</code>: Update an existing task.</li>
    <li><code>DELETE /tasks/{id}</code>: Delete a task.</li>
  </ul>
