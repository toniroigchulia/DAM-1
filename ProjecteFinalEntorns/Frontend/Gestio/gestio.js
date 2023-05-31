document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    document.getElementById("logOut").addEventListener("click", logOut);
    document.getElementById("Alta").addEventListener("click", alta);
    
    getTable();
});

// Funcion para salir de la web
function logOut() {
    window.location.replace("../Login/login.html");
};

// Funcion para avançar al apartado de alta
function alta() {
    window.location.replace("../Alta/alta.html");
};

// Funcion para obtener la tabla de xips del doctor que ha hecho login
function getTable() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServeXips?" + new URLSearchParams({
        mail: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            
            // Identificamos el elemento al cual quereos insertal la tabla
            var tabla = document.getElementById("tabla");
            
            // Hacemos que su contenido sea igual a la tabla que nos llega del backend
            tabla.innerHTML = this.response;
        };
    };
};