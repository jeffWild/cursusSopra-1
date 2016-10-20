
function todoInitialise() {

    todoManager = {
        "taches" : [],
        "ajouterTache" : function() {
            var description = document.getElementById("description").value;
            var contexte = document.getElementById("contexte").value;
            var priorite = parseInt(document.getElementById("priorite").value);
            var t = new Tache(description, contexte, priorite);
            this.taches.push(t); 
            this.rafraichirTaches();
        },
        "viderTaches" : function() {
            this.taches = [];
            this.rafraichirTaches();

        },
        "rafraichirTaches" : function () {
            var todoliste = document.getElementById("todoliste");
            todoliste.innerHTML = "";
            var html = "";
            for( var i = 0; i < this.taches.length; i++) {
                html += this.taches[i].afficher();
            }
            todoliste.innerHTML = html;
            var liste = document.getElementsByClassName("tache");
            for (var i = 0; i < liste.length; i++) {
                var div = liste[i];
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

    var btnAdd = document.getElementById("btAddTache");
    btnAdd.addEventListener('click', function() {
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