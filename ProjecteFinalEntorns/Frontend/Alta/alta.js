document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("volver").addEventListener("click", volver);
    
    getMedicines();
    getPatients();
    
});

function volver() {
    window.location.replace("../Gestio/gestio.html");
};

function send() {

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

        };
    };
};