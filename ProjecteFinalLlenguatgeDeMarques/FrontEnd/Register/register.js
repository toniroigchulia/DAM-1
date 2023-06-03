document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    getCountrys();
    
    document.getElementById("botonEnviar").addEventListener("click", confirmarContraseña);
    document.getElementById("botonVolver").addEventListener("click", volver);
});

// Confirmamos que las contraseñas sean iguales antes de mandar el registro al backend
function confirmarContraseña() {

    if (document.getElementById("password").value == document.getElementById("confirmPassword").value) {

        send();
    } else {

        // Indicamos al usuario que no se ha podido iniciar session
        var notificacion = document.getElementById("notificacion");
        notificacion.style.color = "red";
        notificacion.innerHTML = "Las contraseñas no coinciden";
    }

};

// Funcion para mandar informacion al backend
function send() {
    var ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("POST", "http://localhost:8080/BackendLlenguatgeDeMarques/Register?" + new URLSearchParams({
        user: document.getElementById("user").value,
        mail: document.getElementById("email").value,
        pass: document.getElementById("password").value,
        codeCountry: document.getElementById("paises").value
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            if (this.response == "false") {

                // Indicamos al usuario que no se ha podido iniciar session
                var notificacion = document.getElementById("notificacion");
                notificacion.style.color = "red";
                notificacion.innerHTML = "Error de Registro";
            } else {
                var form = document.getElementById("form");
                form.reset();

                // Indicamos al usuario que no se ha podido iniciar session
                var notificacion = document.getElementById("notificacion");
                notificacion.style.color = "green";
                notificacion.innerHTML = "Registrado Correctamente";
            };
        };
    };
};

// Conseguimos el listado de paises del backend
function getCountrys() {
    var ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/BackendLlenguatgeDeMarques/Register?");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // Transformamos el json para poder usarlo
            data = JSON.parse(this.response);

            //Obtenemos el elemento donde ira el listado de pacientes
            var countrySelect = document.getElementById("paises");

            // Por cada paciente que nos llega se ejecuta lo siguiente
            for (var i = 0; i < data.length; i++) {

                // Creamos la variable que se añadira el select
                var option = document.createElement("option");

                // Le damos el texto y el valor deseado
                option.text = data[i].name;
                option.value = data[i].code;

                // Se añade al select
                countrySelect.appendChild(option);
            };
        };
    };
};

function volver() {

    window.location.replace("../Login/login.html");
};