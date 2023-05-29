document.addEventListener("DOMContentLoaded", () => {
    
    console.log(sessionStorage.getItem("mail"));
    console.log(sessionStorage.getItem("session"));
    console.log(sessionStorage.getItem("name"));
    
    document.getElementById("logOut").addEventListener("click", logOut);
    document.getElementById("Alta").addEventListener("click", alta);
});

function logOut() {
    window.location.replace("../Login/login.html");
};

function alta() {
    window.location.replace("../Alta/alta.html");
};

function getTable() {

};