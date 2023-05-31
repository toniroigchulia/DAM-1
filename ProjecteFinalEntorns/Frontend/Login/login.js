document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    document.getElementById("botonEnviar").addEventListener("click", send);
});

// Funcion para mandar informacion al backend
function send() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/Login?" + new URLSearchParams({
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {      
        if (this.readyState == 4 && this.status == 200) {
            // Transformamos el json del backend a informacion manejable
            data = JSON.parse(this.response);
            
            // Si la sesion es null quiere decir que el inicio de sesion no ha sido correcto
            if (data.session == null) {
                
                // Indicamos al usuario que no se ha podido iniciar session
                var notificacion = document.getElementById("notificacion");
                notificacion.innerHTML = "Error de inicio de session.";
            }
            
            // Si la session no es null recorremos esta array para guardar en el session 
            // storage la informacion deseada con el nombre de la array
            var list = ["mail", "session", "name"];
            for (var index in list) {
                sessionStorage.setItem(list[index], data[list[index]]);
            };
            
            // I si la session no es null se avanza a la pagina de gestion
            if (data.session != null) {
                window.location.replace("../Gestio/gestio.html");
            };
        };
    };
};