function fixar(){
    let color = document.getElementById("backColor").value;
    document.body.style.backgroundColor = color;
    sessionStorage.setItem("color", color);
}

function init(){
    let color = sessionStorage.getItem("color", color);
    document.body.style.backgroundColor = color;
}