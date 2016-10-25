$(document).ready(function() {
    window.myPaint = initpaint();
 
});

function initpaint() {

    var canvas = document.getElementById("dessin");
    var ctx = canvas.getContext("2d");
    var xdown, ydown, xup, yup, lastX, lastY;
    // la "position" de mon affichage par rapport au dessin
    var translateX = 0;
    var translateY = 0;
    var deplX = 0;
    var deplY = 0;
    
    var rightButton = false;

    var left = canvas.getBoundingClientRect().left;
    var top = canvas.getBoundingClientRect().top;
    var canvasJquery = $("#dessin");

    // tableau des figures a afficher
    var figures = [];

    // module de gestion du canvas
    var paintModule = {

        // enregistre les coordonnées au bouton appuyé de la souris
        mouseDownListener: function(evt) {
            if (evt.button == 2) {
                rightButton = true;
                lastX = evt.clientX;
                lastY = evt.clientY;
            }
            else if (evt.button == 0) {
                xdown = evt.clientX - left;
                ydown = evt.clientY - top;
                xdown = (xdown < 0) ? 0 : xdown;
                ydown = (ydown < 0) ? 0 : ydown;
                xdown = xdown - translateX;
                ydown = ydown - translateY;
            }
        },
        // idem a la remontée du bouton, de plus, on appelle
        // ce qu'il faut pour gérer le 'click'
        mouseUpListener: function(evt) {
            if (evt.button == 2) {
                rightButton = false;
                lastX = evt.clientX;
                lastY = evt.clientY;
                translateX = translateX + deplX;
                translateY = translateY + deplY;
                deplX = 0;
                deplY = 0;
                paintModule.refreshPaint();
                evt.preventDefault();
                return false;
            }
            else if (evt.button == 0) {
                xup = evt.clientX - left;
                yup = evt.clientY - top;
                xup = (xup < 0) ? 0 : xup;
                yup = (yup < 0) ? 0 : yup;
                xup = xup - translateX;
                yup = yup - translateY;
                // ajoute la nouvelle figure, en fonction
                // de ce qui est choisi dans la liste
                paintModule.addFigure();
            }
        },
        mouseMoveListener: function(evt) {
            if (rightButton) {
                deplX = evt.clientX - lastX;
                deplY = evt.clientY - lastY;
                paintModule.refreshPaint();
            }
        },
        addFigure: function() {
            var choix = $("#figure").val();
            var color = $("#couleur").val();
            var texte = $("#texte").val();
            // en fonction de la valeur de la liste (select)
            // ajouter le bon type de figure a la liste
            // des figures
            switch(choix) {
                case "1":
                    figures.push({
                         "type": 1,
                          "x1" : xdown,
                          "x2" : xup,
                          "y1" : ydown,
                          "y2" : yup,
                          "color" : color
                        });
                    break;
                case "2":
                     figures.push({
                         "type": 2,
                          "x1" : xdown,
                          "x2" : xup,
                          "y1" : ydown,
                          "y2" : yup,
                          "color" : color
                        });
                    break;
                case "3":
                     figures.push({
                         "type": 3,
                          "x1" : xdown,
                          "x2" : xup,
                          "y1" : ydown,
                          "y2" : yup,
                          "color" : color
                        });
                    break;
                case "4":
                     figures.push({
                         "type": 4,
                          "x1" : xdown,
                          "x2" : xup,
                          "y1" : ydown,
                          "y2" : yup,
                          "color" : color
                        });
                    break;
                case "5":
                     figures.push({
                         "type": 5,
                          "x1" : xdown,
                          "x2" : xup,
                          "y1" : ydown,
                          "y2" : yup,
                          "color" : color,
                          "texte" : texte
                        });
                    break;
            }
            // rafraichir l'affichage
            paintModule.refreshPaint();
        },

        refreshPaint: function() {
              ctx.clearRect(0, 0 , canvas.width, canvas.height);

              // deplacement du dessin
              ctx.translate(translateX +deplX, translateY + deplY);

              for (var i = 0; i < figures.length; i++) {
                  var f = figures[i];
                  ctx.fillStyle = f.color;
                  ctx.strokeStyle = f.color;
                  switch(f.type) {
                      case 1:
                        ctx.fillRect(f.x1, f.y1, f.x2 - f.x1, f.y2 - f.y1);                        
                        break;
                      case 2:
                        ctx.beginPath();
                        ctx.rect(f.x1, f.y1, f.x2 - f.x1, f.y2 - f.y1);
                        ctx.stroke();
                        
                        break;
                      case 3:
                        ctx.beginPath();
                        ctx.moveTo(f.x1, f.y1);
                        ctx.lineTo(f.x2, f.y2);
                        ctx.stroke();
                        break;
                      case 4:
                        // je calcul l'orientation de ma fleche
                        var angle = Math.atan2(f.y2 - f.y1, f.x2 - f.x1);
                        ctx.beginPath();
                        ctx.moveTo(f.x1, f.y1);
                        // dessin de la ligne principale
                        ctx.lineTo(f.x2, f.y2);
                        // dessin d'une des branches de la fleche
                        ctx.lineTo(f.x2 - 10 * Math.cos(angle - Math.PI / 7),
                                   f.y2 - 10 * Math.sin(angle - Math.PI / 7));
                        ctx.moveTo(f.x2, f.y2);
                        // dessin de la deuxieme branche de la fleche
                        ctx.lineTo(f.x2 - 10 * Math.cos(angle + Math.PI / 7),
                                   f.y2 - 10 * Math.sin(angle + Math.PI / 7));
                        ctx.stroke();
                        break;
                      case 5:
                        ctx.font = 'italic 10pt Calibri';
                        ctx.fillText($("#texte").val(), f.x2, f.y2);
                        break;
                  }
              }
              ctx.setTransform(1, 0, 0, 1, 0, 0);
        },
        save: function() {
            console.log(JSON.stringify(figures));
            // quand je sauvegarde un dessin
            // je le sauvegarde dans une propriété de localStorage
            // dont le nom est en provenance du champ texte
            localStorage[$("#texte").val()] = JSON.stringify(figures);
        },
        load: function() {
            figures = JSON.parse(localStorage[$("#texte").val()] || "[]");
            paintModule.refreshPaint();
        }
    };

    canvasJquery.on('mousedown', paintModule.mouseDownListener);
    canvasJquery.on('mouseup', paintModule.mouseUpListener);
    canvasJquery.on('mousemove', paintModule.mouseMoveListener);
    canvasJquery.on("contextmenu", function(evt) {
        evt.preventDefault();
    });
    $('#saveButton').on('click', paintModule.save);
    $('#loadButton').on('click', paintModule.load);
    return paintModule;
}