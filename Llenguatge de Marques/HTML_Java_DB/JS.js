function suma() {
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
}

function resta() {
    var ehttp = new XMLHttpRequest();
    var i = 0;
    
    while (1 < 100) {
    
        ehttp.open("POST", "http://172.16.138.129:3000/OperationDam/Resta", true);
        ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ehttp.send("a="+i+"&b="+document.getElementById("num2").value);
        
        ehttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("num1").value = "";
                document.getElementById("num2").value = "";
                document.getElementById("resultado").innerHTML = ehttp.responseText;
            }
        };
    
        i++
    }
    
}

function multi() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://172.16.138.129:3000/OperationDam/Multiplicacio", true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send("a="+document.getElementById("num1").value+"&b="+document.getElementById("num2").value);
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("num1").value = "";
            document.getElementById("num2").value = "";
            document.getElementById("resultado").innerHTML = ehttp.responseText;
        }
    };
}

function div() {
    var ehttp = new XMLHttpRequest();
    
    ehttp.open("POST", "http://172.16.138.129:3000/OperationDam/Divisio", true);
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send("a="+document.getElementById("num1").value+"&b="+document.getElementById("num2").value);
    
    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("num1").value = "";
            document.getElementById("num2").value = "";
            document.getElementById("resultado").innerHTML = ehttp.responseText;
        }
    };
}