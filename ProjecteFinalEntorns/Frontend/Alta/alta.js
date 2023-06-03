document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico
    
    document.getElementById("volver").addEventListener("click", volver);
    document.getElementById("botonEnviar").addEventListener("click", send);
    
    getMedicines();
    getPatients();
    
});

// Funcion para volver a gestion
function volver() {
    window.location.replace("../Gestio/gestio.html");
};

// Funcion para mandar informacion al backend
function send() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("POST", "http://localhost:8080/EntornsBackend/Release");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send(new URLSearchParams({
        mail: sessionStorage.getItem("mail"), 
        session: sessionStorage.getItem("session"), 
        idXip: document.getElementById("idxip").value, 
        idMed: document.getElementById("medicamentos").value, 
        date: document.getElementById("dataLimite").value, 
        idPatient: document.getElementById("pacientes").value
    }).toString()); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    
    ehttp.onreadystatechange = function () {
        // Si el backend devuelve un error quiere decir que no se ha podido dar de alta asi que se notifica al usuario
        if (this.status >= 500 && this.status < 600) {
            var notificacion = document.getElementById("notificacion");
            
            notificacion.style.color = "red"
            notificacion.innerHTML = "Ha habido un problema comprueba que todos los campos sean correctos";
        };
        
        // Si ha ido todo bien se resetea el formulario y se le indica al usuario
        if (this.readyState == 4 && this.status == 200) {  
            var form = document.getElementById("form");
            form.reset();
        
            var notificacion = document.getElementById("notificacion");
            
            notificacion.style.color = "green"
            notificacion.innerHTML = "Se ha dado de alta correctamente";
        };    
    };
};

// Funcion para obtener todos los pacientes de la base de datos y mostrarlos en el momento de dar de alta
function getPatients() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServePatients?" + new URLSearchParams({
        email: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // Transformamos el json para poder usarlo
            data = JSON.parse(this.response);
            
            //Obtenemos el elemento donde ira el listado de pacientes
            var patientSelect = document.getElementById("pacientes");
            
            // Por cada paciente que nos llega se ejecuta lo siguiente
            for (var i = 0; i < data.length; i++) {
                
                // Obtenemos su email
                patientMail = data[i].mail;
                
                // Creamos la variable que se añadira el select
                var option = document.createElement("option");
                
                // Le damos el texto y el valor deseado
                option.text = patientMail;
                option.value = patientMail;
                
                // Se añade al select
                patientSelect.appendChild(option);
            };
        };
    };
};

// Funcion para consegui todas las medicinas del backend
function getMedicines() {
    var ehttp = new XMLHttpRequest();
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServeMedicines?" + new URLSearchParams({
        email: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // Toda la logica es identica a la de getPatients
            data = JSON.parse(this.response);
            var medicineSelect = document.getElementById("medicamentos");
            
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement("option");

                option.text = data[i].name;
                option.value = data[i].id;

                medicineSelect.appendChild(option);
            };
        };
    };
};