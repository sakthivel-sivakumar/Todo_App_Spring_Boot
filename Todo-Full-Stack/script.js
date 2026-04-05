// ================= CONFIG =================
const SERVER_URL = "http://localhost:8080";

// 🔐 Redirect if not logged in (for todo page)
if (window.location.pathname.includes("todo.html") && !localStorage.getItem("token")) {
    alert("Please login first");
    window.location.href = "/Todo-Full-Stack/login.html";
}

// 🔐 Common headers
const getHeaders = () => ({
    "Content-Type": "application/json",
    "Authorization": "Bearer " + localStorage.getItem("token")
});


// ================= LOGIN =================
async function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${SERVER_URL}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
        alert("Invalid credentials");
        return;
    }

    const data = await res.json();
    const token = data.token; // OR data.jwt (based on your backend)
    localStorage.setItem("token", token);
    window.location.href = "/Todo-Full-Stack/todos.html";
}


// ================= REGISTER =================
async function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${SERVER_URL}/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
        alert("Registration failed");
        return;
    }

    alert("Registered successfully");
    window.location.href = "/Todo-Full-Stack/login.html";
}


// ================= CREATE TODO CARD =================
function createTodoCard(todo) {
    const div = document.createElement("div");
    div.className = "todo-card";

    // Checkbox
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.checked = todo.completed;
    checkbox.onchange = () => updateTodoStatus(todo);

    // Title
    const title = document.createElement("span");
    title.innerText = todo.title;

    

    if (todo.completed) {
        div.classList.add("completed");
    }

    // Edit button
    const editBtn = document.createElement("button");
    editBtn.classList.add("edit-btn");
    editBtn.innerText = "✏️";
    editBtn.onclick = () => {
        const newTitle = prompt("Update todo:", todo.title);
        if (newTitle) {
            updateTodo(todo.id, newTitle, todo.completed);
        }
    };

    // Delete button
    const delBtn = document.createElement("button");
    delBtn.classList.add("delete-btn");
    delBtn.innerText = "❌";
    delBtn.onclick = () => deleteTodo(todo.id);

    // Styling (optional)
    div.style.display = "flex";
    div.style.justifyContent = "space-between";
    div.style.alignItems = "center";
    div.style.padding = "10px";
    div.style.marginTop = "10px";
    div.style.border = "1px solid #ccc";
    div.style.background = "white";

    div.appendChild(checkbox);
    div.appendChild(title);
    div.appendChild(editBtn);
    div.appendChild(delBtn);

    return div;
}


// ================= LOAD TODOS =================
async function loadTodos() {
    console.log("load todos")
    const res = await fetch(`${SERVER_URL}/api/todo/getTodos`, {
        headers: getHeaders()
    });

    if (!res.ok) {
        alert("Unauthorized");
        return;
    }

    const todos = await res.json();

    const container = document.getElementById("todo-list");
    container.innerHTML = "";

    if (todos.length === 0) {
        container.innerHTML = "<p>No todos found</p>";
        return;
    }

    todos.forEach(todo => {
        container.appendChild(createTodoCard(todo));
    });
}


// ================= ADD TODO =================
async function addTodo() {
    const title = document.getElementById("new-todo").value;

    if (!title) return;

    await fetch(`${SERVER_URL}/api/todo/create`, {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify({ title })
    });

    document.getElementById("new-todo").value = "";
    loadTodos();
}


// ================= TOGGLE COMPLETE =================
async function updateTodoStatus(todo) {
    await fetch(`${SERVER_URL}/api/todo/update/${todo.id}`, {
        method: "PUT",
        headers: getHeaders(),
        body: JSON.stringify({
            title: todo.title,
            completed: !todo.completed
        })
    });

    loadTodos();
}


// ================= UPDATE TODO =================
async function updateTodo(id, title, completed) {
    await fetch(`${SERVER_URL}/api/todo/update/${id}`, {
        method: "PUT",
        headers: getHeaders(),
        body: JSON.stringify({
            title,
            completed
        })
    });

    loadTodos();
}


// ================= DELETE TODO =================
async function deleteTodo(id) {
    await fetch(`${SERVER_URL}/api/todo/delete/${id}`, {
        method: "DELETE",
        headers: getHeaders()
    });

    loadTodos();
}


// ================= INIT =================
document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});