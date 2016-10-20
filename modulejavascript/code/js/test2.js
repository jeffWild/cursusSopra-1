
function additionner() {
    // récuperer les valeur des champs du formulaire
    // pour acceder au document HTML
    // on utilise l'objet 'implicite' document
    // cet objet document fournit entre autre l'api DOM

    var elemop1 = document.getElementById("op1");
    var elemop2 = document.getElementById("op2");
     // elemop1 et elemop2 sont des 'element', un objet
     // decrivant une partie/balise du document
    var valueop1 = elemop1.value;
    var valueop2 = elemop2.value;

//    console.log("valueop1 = " + valueop1);
//    console.log("valueop2 = " + valueop2);
    var op1 = parseInt(valueop1);
    var op2 = parseInt(valueop2);

    var resultatElem = document.getElementById("resultat");
    resultatElem.innerHTML = "<h2> addition = " + (op1 + op2) + "</h2>";
};

function ajouterContact() {
    if (window.personnes == undefined) {
        window.personnes = [];
    }
    var nom = document.getElementById("nomPersonne").value;
    var prenom = document.getElementById("prenomPersonne").value;
    var p = new Personne(nom, prenom);
    personnes.push(p); // push permet d'ajouter un element a un tableau
    refreshRepertoire();
};

// regénérer le contenu du div repertoire a partir des personnes
function refreshRepertoire() {
    var repertoire = document.getElementById("repertoire");
    repertoire.innerHTML = "";
    var html = "";
    for( var i = 0; i < personnes.length; i++) {
        html += personnes[i].afficher();
    }
    repertoire.innerHTML = html;
    var liste = document.getElementsByClassName("personne");
    for (var i = 0; i < liste.length; i++) {
        var div = liste[i];
        div.setAttribute('data-pos', "" + i);
        div.addEventListener('click', function() {
            // quand un event listener est appelé
            // le 'this' est positionné sur l'element cliqué
            var pos = parseInt(this.getAttribute('data-pos'));
            removePersonne(pos);
        });
    }
};

function removePersonne(pos) {
    // retirer une element du tableau a la position pos
    personnes.splice(pos, 1);
    refreshRepertoire();
};

// nous définissons ici un constructeur d'objet Personne
function Personne(nom, prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.afficher = function() {
        return "<div class='personne'>" 
                +"<h3> nom: " + this.nom 
                + " prenom: " + this.prenom + "</h3>"
                + "</div>";
    };
};
