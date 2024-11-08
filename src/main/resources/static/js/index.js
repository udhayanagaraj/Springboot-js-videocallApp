

const loadDisplayUsers = () => {

    //check if user is connected
    const connectedUser = localStorage.getItem("connectedUser");
    if(!connectedUser){
        window.location = 'login.html';
        return;
    }

    const userListElement = document.getElementById("userList");

    userListElement.innerHTML = "Loading...";

    fetch("http://localhost:8080/api/v1/users")
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            displayUsers(data,userListElement)
        });
}

const displayUsers = (userList,userListElement) => {
    userListElement.innerHTML = "";

    userList.forEach(user => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
                <div>
                    <i class="fa fa-user-circle"></i>
                    ${user.username} <i class="user-email">(${user.email})</i>
                </div>
                <i class="fa fa-lightbulb-o ${user.status === "online" ? "online" : "offline"}"></i>
            `;
        userListElement.appendChild(listItem);
    });
}

window.addEventListener("load",loadDisplayUsers);


function handleLogout() {
    fetch('http://localhost:8080/api/v1/users/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: localStorage.getItem('connectedUser')
    })
        .then((response) => {
            return response;
        })
        .then((data) => {
            localStorage.removeItem('connectedUser');
            window.location.href = "login.html";
        });
}

const logoutBtn = document.getElementById("logoutBtn");
logoutBtn.addEventListener("click", handleLogout);

