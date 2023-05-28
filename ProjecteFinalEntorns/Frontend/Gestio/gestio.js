document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("logOut").addEventListener("click", logOut);
});

function logOut() {
    console.log("salir")
    window.location.replace("../Login/login.html");
};