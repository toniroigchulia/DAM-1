document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    getCountrys();
    
    document.getElementById("botonEnviar").addEventListener("click", confirmarRegistro);
    document.getElementById("botonVolver").addEventListener("click", volver);
});

// Confirmamos que las contraseñas sean iguales antes de mandar el registro al backend
function confirmarRegistro() {

    if (document.getElementById("password").value == document.getElementById("confirmPassword").value && document.getElementById("email").value.trim()) {

        send();
    } else {

        // Indicamos al usuario que no se ha podido iniciar session
        let notificacion = document.getElementById("notificacion");
        notificacion.style.color = "red";
        notificacion.innerHTML = "Error de registro";
    }

};

// Funcion para mandar informacion al backend
function send() {
    let ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("POST", "http://localhost:8080/BackendLlenguatgeDeMarques/Register?");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send(new URLSearchParams({
        user: document.getElementById("user").value,
        mail: document.getElementById("email").value,
        pass: document.getElementById("password").value,
        codeCountry: document.getElementById("paises").value
    }).toString()); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            if (this.response == "false") {

                // Indicamos al usuario que no se ha podido iniciar session
                let notificacion = document.getElementById("notificacion");
                notificacion.style.color = "red";
                notificacion.innerHTML = "Error de Registro";
            } else {
                let form = document.getElementById("form");
                form.reset();

                // Indicamos al usuario que no se ha podido iniciar session
                let notificacion = document.getElementById("notificacion");
                notificacion.style.color = "green";
                notificacion.innerHTML = "Registrado Correctamente";
            };
        };
    };
};

// Conseguimos el listado de paises del backend
function getCountrys() {
    let ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/BackendLlenguatgeDeMarques/Register?");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // Transformamos el json para poder usarlo
            data = JSON.parse(this.response);

            //Obtenemos el elemento donde ira el listado de pacientes
            let countrySelect = document.getElementById("paises");

            // Por cada paciente que nos llega se ejecuta lo siguiente
            for (let i = 0; i < data.length; i++) {

                // Creamos la variable que se añadira el select
                let option = document.createElement("option");

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