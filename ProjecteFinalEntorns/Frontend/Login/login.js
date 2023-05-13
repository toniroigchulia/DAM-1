document.addEventListener("DOMContentLoaded", () => {

    
    document.getElementById("botonEnviar").addEventListener("click", send);


});

function send(){
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://172.16.138.129:3000/OperationDam/Suma", true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send("a="+document.getElementById("num1").value+"&b="+document.getElementById("num2").value);
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("num1").value = "";
            document.getElementById("num2").value = "";
            document.getElementById("resultado").innerHTML = ehttp.responseText;
        }
    };
};    