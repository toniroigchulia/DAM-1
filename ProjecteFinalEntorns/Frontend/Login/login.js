document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("botonEnviar").addEventListener("click", send);
});

function send() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/Login?" + new URLSearchParams({
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    }).toString(), true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {      
        if (this.readyState == 4 && this.status == 200) {
            data = JSON.parse(this.response);
            if (data.session == null) {
                var notificacion = document.getElementById("notificacion");
                notificacion.innerHTML = "Error de inicio de session.";
            }
            
            var list = ["mail", "session", "name"];
            for (var index in list) {
                sessionStorage.setItem(list[index], data[list[index]]);
            };
            
            if (data.session != null) {
                window.location.replace("../Gestio/gestio.html");
            };
        };
    };
};