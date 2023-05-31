document.addEventListener("DOMContentLoaded", () => {
    
    document.getElementById("logOut").addEventListener("click", logOut);
    document.getElementById("Alta").addEventListener("click", alta);
    
    getTable();
});

function logOut() {
    window.location.replace("../Login/login.html");
};

function alta() {
    window.location.replace("../Alta/alta.html");
};

function getTable() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServeXips?" + new URLSearchParams({
        mail: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            
            var tabla = document.getElementById("tabla");
            
            tabla.innerHTML = this.response;
        };
    };
};