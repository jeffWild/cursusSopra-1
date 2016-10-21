
function initialise() {

    document.getElementById("bt1")
            .addEventListener('click', function () {
                var p1 = new Personne("bob", "joe");

                console.log(p1.affiche());

                var c1 = new Client("potter",
                                     "harry",
                                     "harry@poudlard.com",
                                      250.0);
                c1.contacter("bonjour");
                console.log(c1.affiche());
                parentClient.prenom = "franklin";
                console.log(c1.affiche());
                var c2 = new Client("granger",
                                     "hermione",
                                     "hermione@poudlard.com",
                                      350.0);
                console.log(c2.affiche());
                c2.prenom = "hermione";
                console.log(c2.affiche());


            });
    document.getElementById("bt2")
            .addEventListener('click', function () {
                var voiture1 = new Voiture("peugot", "205", "essence", "rouge");
                voiture1.demarrer();

                var vehicule1 = new Vehicule("dmc", "delorean");

                console.log(typeof(voiture1));
                console.log(typeof(vehicule1));
                console.log(voiture1 instanceof Voiture);
                console.log(vehicule1 instanceof Vehicule);
                console.log(voiture1 instanceof Vehicule);
                console.log(vehicule1 instanceof Voiture);
            });
};

// constructeur de Vehicule
function Vehicule(marque, modele) {
    this.marque = marque;
    this.modele = modele;
};

Vehicule.prototype.toString = function() {
    return this.marque + ", " + this.modele;;
}

// constructeur de Voiture
function Voiture(marque, modele, moteur, couleur) {
    // call permet d'appeler une fonction en choisissant
    // le this utilisé
    Vehicule.call(this, marque, modele);
    this.moteur = moteur;
    this.couleur = couleur;
}

// Object.create creer un nouvel objet vide avec comme prototype
// celui passé en parametre
Voiture.prototype = Object.create(Vehicule.prototype);
// indiquer le bon type pour l'objet
Voiture.prototype.constructor = Voiture;

Voiture.prototype.demarrer = function() {
    console.log("demarrage de la voiture " + this.toString());
};

Voiture.prototype.toString = function() {
    // comment rappeler une fonction du parent
    // si on l'a redefinit dans notre enfant
    return Vehicule.prototype.toString.call(this) + ", " + this.moteur;
};


//
//--------------------------------------
//
//


function Personne(nom, prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.affiche = function() {
        return this.nom + ", " + this.prenom;
    };
};

function Client(nom, prenom, email, solde) {
    this.nom = nom;
    //this.prenom = prenom;
    this.email = email;
    this.solde = solde;

    this.contacter = function(message) {
        console.log("contact de " + this.email + ": " + message);
    };
};

parentClient = new Personne("anonyme", "anonyme");

Client.prototype = parentClient;


