
// cette fonction est appelée pour
// initialiser notre javascript
// elle n'est appelée que lorsque la page est fini d'etre chargée
function todoInitialise() {

    // regroupement des fonctionnalité de notre page
    // dans un objet todoManager qui sera disponnible globalement
    // cela evite de déclarer plein de fonctions/attribut dans
    // window, cela permet aussi d'organiser notre code
    todoManager = {
        "taches" : [], // la collection des taches
        "ajouterTache" : function() {
            var description = document.getElementById("description").value;
            var contexte = document.getElementById("contexte").value;
            var priorite = parseInt(document.getElementById("priorite").value);
            var t = new Tache(description, contexte, priorite);
            this.taches.push(t); 
            //attention, le this est obligatoire pour acceder au attributs
            // de notre objet, pas de this -> on accede a window
            this.rafraichirTaches();
        },
        "viderTaches" : function() {
            this.taches = [];
            this.rafraichirTaches();

        },
        "rafraichirTaches" : function () {
            // recupération du div a remplire
            var todoliste = document.getElementById("todoliste");
            // on le vide
            todoliste.innerHTML = "";
            var html = "";
            // pour chaque Tache, générer le code HTML
            for( var i = 0; i < this.taches.length; i++) {
                html += this.taches[i].afficher();
            }
            // inserer ce code dans le div
            todoliste.innerHTML = html;
            // pour les div de classe tache
            // attacher une fonction au click sur celui-ci 
            var liste = document.getElementsByClassName("tache");
            for (var i = 0; i < liste.length; i++) {
                var div = liste[i];
                // en stockant dans un attribut 'custom' du div
                // de la tache sa position dans le tableau
                // ce qui permettra de facilement le retirer
                // du tableau par la suite
                div.setAttribute('data-pos', "" + i);
                div.addEventListener('click', function() {
                    var pos = parseInt(this.getAttribute('data-pos'));
                    todoManager.removeTache(pos);
                });
            }
        },
        "removeTache" : function(pos) {
             this.taches.splice(pos, 1);
             this.rafraichirTaches();
        }
    };
    // au démarrage, j'attache dynamiquement mes fonctions
    // aux evenements 'click' des boutons

    var btnAdd = document.getElementById("btAddTache");
 //   btnAdd.addEventListener('click', todoManager.ajouterTache);
    btnAdd.addEventListener('click', function() {
        // ici, this sera positionné sur le bouton cliqué
        // je rappelle ajouterTache via todoManager
        // pour que dans cette fonction, this soit bien positionné
        // sur le todoManager, car celui contient mes données
        // et autres méthodes
        todoManager.ajouterTache();
    });

    var btnClear = document.getElementById("btClearTache");
    btnClear.addEventListener('click', function() {
        todoManager.viderTaches();
    });

};

function Tache(description,contexte, priorite) {
    this.description = description;
    this.contexte = contexte;
    this.priorite = priorite;
    this.afficher = function() {
        return "<div class='tache'>" 
                +"<h3>" + this.description 
                + "</h3><p>" + this.contexte 
                + " pri: " + this.priorite+ "</p>"
                + "</div>";
    };
}