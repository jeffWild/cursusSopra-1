
console.log("bonjour depuis test1.js");

function fonction1() {
    console.log("bonjour depuis fonction1");
};

// appel de la fonction1
fonction1();

function fonction2() {
    console.log("bonjour depuis fonction2");
};

function fonction3() {
    var mavariable1 = "pizza";
    console.log(mavariable1);
    console.log(typeof(mavariable1));

    // meme si la variable a effectivement un type
    // celui-ci peu changer en fonction de ce qui est
    // stocké dans la variable

    mavariable1 = 42;
    console.log(mavariable1);
    console.log(typeof(mavariable1));

    var mavariable2 = 42;
    var mavariable3 = "42";

    console.log("mavariable1 == mavariable2 : " 
                + (mavariable1 == mavariable2));
    console.log("mavariable1 == mavariable3 : " 
                + (mavariable1 == mavariable3));
    console.log("mavariable1 === mavariable3 : " 
                + (mavariable1 === mavariable3)); 
    
    console.log("------------------------------");
    var mavariable4;
    console.log(mavariable4);
    console.log(typeof(mavariable4));

    var mavariable5 = null;
    console.log(mavariable5);
    console.log(typeof(mavariable5));
    // string, number, object, undefined, boolean, array
    
    var mavariable6 = true;
    console.log(mavariable6);
    console.log(typeof(mavariable6));

    var mavariable7 = [1, 2, 3, 5];
    console.log(mavariable7);
    console.log(typeof(mavariable7));
    console.log("length = " + mavariable7.length);

    var mavariable8 = NaN; // not a number
    console.log(mavariable8);
    console.log(typeof(mavariable8));

    var mavariable9 = mavariable1 / 0.0;
    console.log(mavariable9);
    console.log(typeof(mavariable9));
    
    var mavariableA = undefined;
    console.log(mavariableA);
    console.log(typeof(mavariableA));
    
    console.log(mavariableB);
    console.log(typeof(mavariableB));

    //  en fait, cette variable est reelement
    // déclarée au début de la fonction
    // ATTENTION, l'affectation elle se fait reelement
    // ou elle est ecrite
    var mavariableB = 15;

    var mavariableC = { "nom" : "courtalon", "prenom" : "vincent"};
    console.log(mavariableC);
    console.log(typeof(mavariableC));

};

function fonction4() {
    var v1 = 15;
    if (v1 < 20) {
        console.log("v1 est inférieur a 20");
    }
    else {
        console.log("v1 est supérieur ou égal a 20");
    }

    var v2 = "uk";

    switch(v2) {
        case "fr":
            console.log("francais");
            break;
        case "us":
        case "uk":
            console.log("anglais");
            break;
        default:
            console.log("autre");
            break;
    }

    for (var i = 1; i < 10; i++) {
        console.log("i = " + i);
    }
};

// tableaux et objets basiques
function fonction5() {
    var tab1 = ["lundi", "mardi", "mercredi"];
    console.log(tab1[1]);

    
    tab1[1] = 15.5;
    tab1.push(true);

    console.log(tab1);

    for (var idx = 0; idx < tab1.length; idx++) {
        console.log(idx + " -> " + tab1[idx]);
    }

    tab1[8] = "pizza";
    console.log('---------------------------');
    for (var idx = 0; idx < tab1.length; idx++) {
        console.log(idx + " -> " + tab1[idx]);
    }
    console.log('---------------------------');

    for(var prop in tab1) {
        console.log(prop + ' ----> ' + tab1[prop]);
    }
    console.log('---------------------------');
    // objet
    var obj1 = { "uk" : "anglais",
                 "fr": "francais",
                  "es" : "espagnol"};
    
    console.log(obj1.uk);
    console.log(obj1["uk"]);

    console.log(obj1.length); // pas de length
    console.log('---------------------------');
    for (var prop in obj1) {
        console.log(prop + " --> " + obj1[prop]);
    }

    //-----------------
    console.log('---------------------------');
    tab1["toto"] = 18.5;
    for(var prop in tab1) {
        console.log(prop + ' ----> ' + tab1[prop]);
    }

    console.log('---------------------------');

    obj1[5] = "bonjour";
    console.log('---------------------------');
    for (var prop in obj1) {
        console.log(prop + " --> " + obj1[prop]);
    }

    /*
    le foreach enumere les propriété d'un objet
    ce qui, pour un tableau, correspond au numéro de ses cases
    en fait, un tableau est un objet avec des "attributs" qui sont les
    numéros de cases, et quelques propriétés spéciales comme length 
    */

    obj1.autreprop = "hahaha";
    console.log('---------------------------');
    for (var prop in obj1) {
        console.log(prop + " --> " + obj1[prop]);
    }

    obj1["if else hoho"] = 28;
    console.log('---------------------------');
    for (var prop in obj1) {
        console.log(prop + " --> " + obj1[prop]);
    }

    delete obj1["if else hoho"];
    console.log('---------------------------');
    for (var prop in obj1) {
        console.log(prop + " --> " + obj1[prop]);
    }
    /*
     les tableaux, comme les objets, sont totalement dynamiques
     on peut creer/supprimer a tout moment un attribut
     la simple affectation a un attribut inexistant le creer
     automatiquement
    */

}

function fonction6() {
    var p1 = { 
            "nom": "courtalon",
            "prenom" : "vincent",
            "afficher" : function() {
                console.log(this.nom + ", " + this.prenom);
            }
        };
    p1.afficher();

    var f1 = p1.afficher;
    console.log(f1);
    console.log(typeof(f1));

    var p2 = {
        "nom": "eponge",
        "prenom" : "bob"
    };
    p2.presentation = f1;

    p2.presentation();
    //f1();

};

function fonction7() {
    var toto = 15;
    titi = 65;
    console.log("f7: titi = " + titi);
    sousfonction1();
    console.log("f7: toto = " + toto);
    console.log("f7: titi = " + titi);
    console.log("window.titi = " + window.titi);
    console.log("---------------------------");
    sousfonction2(10, 20);
    sousfonction2(15, 25, 35);
    sousfonction2(12);
    sousfonction3("patrick");
};


function sousfonction3(nom, prenom, civilite) {
    nom = nom || 'eponge'; // technique pour avoir une valeur par defaut
    prenom = prenom || 'bob';
    civilite = civilite || 'mr';
    console.log("bonjour " + civilite + " " + prenom + " " + nom);
};

//function sousfonction1() {
window.sousfonction1 = function() {
    var toto = 25;
    titi = titi + 10;
    console.log("sf1: toto = " + toto);
    console.log("sf1: titi = " + titi);
};

function sousfonction2(x, y) {
    console.log("x = " + x);
    console.log("y = " + y);
    console.log("nb arguments = " + arguments.length);
    for (var i = 0; i < arguments.length; i++) {
        console.log("arguments[" + i + "] = " + arguments[i]);
    }
};
