document.addEventListener("DOMContentLoaded", () => {

    
    document.getElementById("botonEnviar").addEventListener("click", send);


});

function send(){
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://localhost:8080/EntornsBackend/LoginServlet", true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send("");
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
        }
    };
};    