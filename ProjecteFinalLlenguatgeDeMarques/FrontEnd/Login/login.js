document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    document.getElementById("botonEnviar").addEventListener("click", send);
    document.getElementById("botonRegistrar").addEventListener("click", registro);
});

// Funcion para mandar informacion al backend
function send() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/BackendLlenguatgeDeMarques/Login?" + new URLSearchParams({
        mail: document.getElementById("email").value,
        pass: document.getElementById("password").value
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {      
        if (this.readyState == 4 && this.status == 200) {
            
            if (this.response == "false") {
                
                // Indicamos al usuario que no se ha podido iniciar session
                var notificacion = document.getElementById("notificacion");
                notificacion.innerHTML = "Error de inicio de session.";
            } else {
                
                window.location.replace("../Xat/xat.html");
            };
        };
    };
};

function registro() {

    window.location.replace("../Register/register.html");
};