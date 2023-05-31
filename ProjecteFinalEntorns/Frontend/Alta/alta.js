document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("volver").addEventListener("click", volver);
    document.getElementById("botonEnviar").addEventListener("click", send);
    
    getMedicines();
    getPatients();
    
});

function volver() {
    window.location.replace("../Gestio/gestio.html");
};

function send() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://localhost:8080/EntornsBackend/Release");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send(new URLSearchParams({
        mail: sessionStorage.getItem("mail"), 
        session: sessionStorage.getItem("session"), 
        idXip: document.getElementById("idxip").value, 
        idMed: sessionStorage.getItem(document.getElementById("medicamentos").value), 
        date: document.getElementById("dataLimite").value, 
        idPatient: document.getElementById("pacientes").value
    }).toString());
    
    ehttp.onreadystatechange = function () {
        if (this.status >= 500 && this.status < 600) {
            var notificacion = document.getElementById("notificacion");
            
            notificacion.style.color = "red"
            notificacion.innerHTML = "Ha habido un problema comprueba que todos los campos sean correctos";
        };
        
        if (this.readyState == 4 && this.status == 200) {  
            var form = document.getElementById("form");
            form.reset();
        
            var notificacion = document.getElementById("notificacion");
            
            notificacion.style.color = "green"
            notificacion.innerHTML = "Se ha dado de alta correctamente";
        };    
    };
};

function getPatients() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServePatients?" + new URLSearchParams({
        email: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            data = JSON.parse(this.response);
            var patientSelect = document.getElementById("pacientes");
            
            for (var i = 0; i < data.length; i++) {
                
                patientMail = data[i].mail;
                
                var option = document.createElement("option");

                option.text = patientMail;
                option.value = patientMail;

                patientSelect.appendChild(option);
            };
        };
    };
};

function getMedicines() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("GET", "http://localhost:8080/EntornsBackend/ServeMedicines?" + new URLSearchParams({
        email: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session")
    }).toString(), true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            data = JSON.parse(this.response);
            var medicineSelect = document.getElementById("medicamentos");
            
            for (var i = 0; i < data.length; i++) {
                
                sessionStorage.setItem(data[i].name, data[i].id)
                medicineName = data[i].name;
                
                var option = document.createElement("option");

                option.text = medicineName;
                option.value = medicineName;

                medicineSelect.appendChild(option);
            };
        };
    };
};