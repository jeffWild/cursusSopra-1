function initialise() {
    ticketManager = {
        "currentType" : 'panne',
        "tickets" : [],
        "ajouterTicket" : function() {
            var type = document.getElementById("type").value;
            var t = null;
            if (type === 'panne') {
                t = new TicketPanne();
            }
            else {
                t = new TicketLogiciel();
            }
            t.fillFromForm();
            this.tickets.push(t); 
            //attention, le this est obligatoire pour acceder au attributs
            // de notre objet, pas de this -> on accede a window
            this.rafraichirTickets();
        },
        "viderTicket" : function() {
            this.tickets = [];
            this.rafraichirTickets();

        },
        "rafraichirTickets" : function () {
            // recupération du div a remplir
            var divtickets = document.getElementById("tickets");
            // on le vide
            divtickets.innerHTML = "";
            var html = "";
            for( var i = 0; i < this.tickets.length; i++) {
                html += this.tickets[i].generateDiv();
            }
            // inserer ce code dans le div
            divtickets.innerHTML = html;
            // pour les div de classe tache
            // attacher une fonction au click sur celui-ci 
          /*  var liste = document.getElementsByClassName("tache");
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
            }*/
        },
        "rafraichirForm" : function() {
            var divpanne = document.getElementById("formPanne");
            var divlogiciel = document.getElementById("formLogiciel");
            if (this.currentType == 'panne') {
                divpanne.className = 'formVisible';
                divlogiciel.className = 'formCache';
            }
            else {
                divpanne.className = 'formCache';
                divlogiciel.className = 'formVisible';
            }
        }
    };

    ticketManager.rafraichirForm();
    
    document.getElementById("addButton")
            .addEventListener('click', function() {
        ticketManager.ajouterTicket();
    });

    document.getElementById("type")
            .addEventListener('change', function() {
        var type = this.value;
        console.log('type changed: ' + type);
        ticketManager.currentType = type;
        ticketManager.rafraichirForm();
    });


};


/*
*   classe Ticket
*
*/

function Ticket(description, urgence) {
    this.description = description || 'pas de description';
    this.urgence = urgence || 5;
};

Ticket.prototype.generateDiv = function () {
    return "<div class='" + this.getCssClass() + "'>"
            + this.generateContent()
            + "</div>";
};

Ticket.prototype.generateContent = function() {
    return "<h3>" + this.description + "</h3>"
            + "<span> urgence: " + this.urgence + "</span>";
};

Ticket.prototype.fillFromForm = function() {
    this.description = document.getElementById("description").value;
    this.urgence = parseInt(document.getElementById("urgence").value);
};

Ticket.prototype.getCssClass = function() {
    return 'ticket';
};

/*
*   classe TicketPanne : Ticket
*
*/

function TicketPanne(description, urgence, noMateriel) {
    Ticket.call(this, description, urgence);
    this.noMateriel = noMateriel || '0000000';
};

TicketPanne.prototype = Object.create(Ticket.prototype);
TicketPanne.prototype.constructor = TicketPanne;

TicketPanne.prototype.generateContent = function() {
    return Ticket.prototype.generateContent.call(this)
        + " - <span> noMateriel: " + this.noMateriel + "</span>";
};

TicketPanne.prototype.fillFromForm = function() {
    Ticket.prototype.fillFromForm.call(this);
    this.noMateriel = document.getElementById("noMateriel").value;
};

TicketPanne.prototype.getCssClass = function() {
    return 'ticket panne';
};

/*
*   classe TicketLogiciel : Ticket
*
*/

function TicketLogiciel(description, urgence, nomLogiciel, version) {
    Ticket.call(this, description, urgence);
    this.nomLogiciel = nomLogiciel || 'anonymous software';
    this.version = version || '1.0';
};

TicketLogiciel.prototype = Object.create(Ticket.prototype);
TicketLogiciel.prototype.constructor = TicketLogiciel;

TicketLogiciel.prototype.generateContent = function() {
    return Ticket.prototype.generateContent.call(this)
        + " - <span> nom logiciel: " + this.nomLogiciel + "</span>"
        + " - <span> version : " + this.version + "</span>";
};

TicketLogiciel.prototype.fillFromForm = function() {
    Ticket.prototype.fillFromForm.call(this);
    this.nomLogiciel = document.getElementById("nomLogiciel").value;
    this.version = document.getElementById("version").value;
};

TicketLogiciel.prototype.getCssClass = function() {
    return 'ticket logiciel';
};
