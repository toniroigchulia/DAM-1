//Lista donde estaran todos los equipos
var list = new Array;


//Añadimos 3 equipos 
var e1 = new Equipo("Mallorca", 5, 3);

var e2 = new Equipo("Madrid", 3, 3);

var e3 = new Equipo("Betis", 4, 3);

list.push(e1);
list.push(e2);
list.push(e3);
//////////////////////////////////////

//Contructor de equipos
function Equipo(nom, punts, partits) {
    this.nom = nom;
    this.punts = punts;
    this.partits = partits;

    this.ganarPartido = function (x) {
        this.punts = this.punts + 3 * x;
        this.partits = this.partits + x
    }
    this.empatarPartido = function (x) {
        this.punts = this.punts + 3 * x;
        this.partits = this.partits + x;
    }
    this.perderPartido = function (x) {
        this.partits = this.partits + x;
    }
}

//Metodos JS
function cargarEquipo() {
    let nom = document.getElementById("nom").value;
    let punts = document.getElementById("punts").value;
    let partits = document.getElementById("partits").value
    let e = new Equipo(nom, punts, partits)
    list.push(e)
    console.log(list[list.length - 1])
}

function generarTabla() {
    let tabla = "<table>"
        + "<th>Equipo</th><th>PJ</th><th>Pts</th>"
    for (let i = 0; i < list.length; i++) {
        tabla += "<tr><td>" + list[i].nom + "</td><td>" + list[i].partits + "</td><td>" + list[i].punts + "</td></tr>"
    }
    tabla += "</table>";
    document.getElementById("classificación").innerHTML = tabla;
}

function ordenarAs() {
    for (let j = 0; j < list.length - 1; j++) {
        let primero = list[j];
        for (let i = j+1; i < list.length; i++) {
            if (primero.punts < list[i].punts) {
                primero = list[i];
            }
        }
        let aux = list[j];
        list[j] = primero;
        list[i] = aux;
    }
}