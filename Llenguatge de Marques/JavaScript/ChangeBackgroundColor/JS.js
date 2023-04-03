var lastColor = "white";

function changeBackgroundColor(){

    var input = document.getElementById("userInput").value;
    
    document.getElementById("div").style.color = lastColor;
    
    lastColor = input;
    
    document.getElementById("body").style.backgroundColor = input;
}