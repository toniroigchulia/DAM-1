let chat;
let amigoSeleccionado;
const chats = {};

document.addEventListener("DOMContentLoaded", () => {
    // Metdodos que ocurren al cargar el documento o al hacer click en el boton especifico

    getAmics().then(function() {
        
        getChat(true);
        
        setInterval(function() {
        
            getChat(false);
        }, 1000);
    });
    
    
    document.getElementById("msgForm").addEventListener("submit", sendChat);
    document.getElementById("botonAñadir").addEventListener("click", añadirAmigo);
    document.getElementById("botonVolver").addEventListener("click", volver);
    chat = document.getElementById("chat")
    let tuEmail = document.getElementById("tuEmail")
    tuEmail.innerHTML = "Usuario: " + sessionStorage.getItem("mail")
});

function getChat(enviados) {
    let ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("GET", "http://localhost:8080/BackendLlenguatgeDeMarques/Xat?" + new URLSearchParams({
        mail: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session"),
        enviados: enviados
    }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send();

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let messages = JSON.parse(this.response);

            for (let message of messages) {
                
                let email = message.origen == sessionStorage.getItem("mail") ? message.desti : message.origen;
                
                chats[email].push(message);
                if (message.origen == amigoSeleccionado) {
                    renderizarMensages(message)
                };
            };
            
            enviados = true;
        };
    };
};


function sendChat(event) {
    event.preventDefault();
    let ehttp = new XMLHttpRequest();
    
    
    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("POST", "http://localhost:8080/BackendLlenguatgeDeMarques/Xat?");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send(new URLSearchParams({
        mail: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session"),
        receptor: amigoSeleccionado,
        sms: document.getElementById("sms").value
    }).toString()); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
    
    renderizarMensages({
        text: document.getElementById("sms").value,
        origen: sessionStorage.getItem("mail")
    });
    
    let chatInput = document.getElementById("sms");
    chatInput.value = "";
};

// Funcion para añadir amigo
function añadirAmigo() {
    let ehttp = new XMLHttpRequest();

    // Puerto metodo de envio y informacion que se manda al backend
    ehttp.open("POST", "http://localhost:8080/BackendLlenguatgeDeMarques/Friend?");
    ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ehttp.send(new URLSearchParams({
        mail: sessionStorage.getItem("mail"),
        session: sessionStorage.getItem("session"),
        friend: document.getElementById("añadirAmigo").value
    }).toString()); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
    // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url

    ehttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.response == 1) {
                location.reload();
            } else {
                let notificacion = document.getElementById("notificacion");
                notificacion.style.display = "block";
            };
        };
    };
};

// Funcion para conseguir la lista de amigos del backend
function getAmics() {
    return new Promise((resolve, reject) => {

        let ehttp = new XMLHttpRequest();

        // Puerto metodo de envio y informacion que se manda al backend
        ehttp.open("GET", "http://localhost:8080/BackendLlenguatgeDeMarques/Friend?" + new URLSearchParams({
            mail: sessionStorage.getItem("mail"),
            session: sessionStorage.getItem("session")
        }).toString(), true); // El (+ new URLSearchParams) y (.toString(), true) sirve para poder escribir el contenido 
        // como si fuera un json por comodidad y despues en el momento de mandarlo lo transforma para que vaya con la url
        ehttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ehttp.send();

        ehttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let amigos = JSON.parse(this.response)
                let listaAmigos = document.getElementById("listaAmigos");

                for (let amigo of amigos) {
                    let añadirAmigo = document.createElement("div");
                    añadirAmigo.classList.add("amigo");
                    añadirAmigo.addEventListener("click", cambiarAmigo(amigo));

                    chats[amigo] = [];

                    añadirAmigo.textContent = amigo;

                    listaAmigos.appendChild(añadirAmigo);
                };

                resolve();
            };
        };
    });
};


function cambiarAmigo(amigo) {
    return function() {  
        
        document.querySelector(".amigo.active")?.classList.remove("active");
        
        this.classList.add("active");
        amigoSeleccionado = amigo;
        chat.innerHTML = "";
        renderizarMensages(...chats[amigoSeleccionado]);
    };
};


function renderizarMensages(...messages) {
    for (let message of messages) {
        let messageElement = document.createElement("div");
    
        messageElement.classList.add(message.origen == sessionStorage.getItem("mail") ? "propio" : "externo", "mensage");
    
        messageElement.textContent = message.text;

        chat.appendChild(messageElement);
    };
    
    if (chat.lastChild) {
    
        chat.lastChild.scrollIntoView({behavior:"smooth"});
    };
};

// Funcion para salir al login 
function volver() {

    window.location.replace("../Login/login.html");
};