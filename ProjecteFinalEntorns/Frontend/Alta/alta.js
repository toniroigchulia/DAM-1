document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("volver").addEventListener("click", volver);
    
    getMedicines();
    getPatients();
    
});

function volver() {
    window.location.replace("../Gestio/gestio.html");
};

function send() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://localhost:8080/EntornsBackend/Release");
    ehttp.setRequestHeader("Content-type", "application/json");
    ehttp.send({
        mail: sessionStorage.getItem("mail"), 
        session: sessionStorage.getItem("session"), 
        idXip: document.getElementById("idxip").value, 
        idMed: document.getElementById("medicamentos").value, 
        date: document.getElementById("dataLimite").value, 
        idPatient: document.getElementById("pacientes").value
    });
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.response);
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
                
                patientName = data[i].name;
                
                var option = document.createElement("option");

                option.text = patientName;
                option.value = patientName;

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
                
                medicineName = data[i].name;
                
                var option = document.createElement("option");

                option.text = medicineName;
                option.value = medicineName;

                medicineSelect.appendChild(option);
            };
        };
    };
};