document.addEventListener("DOMContentLoaded", () => {

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