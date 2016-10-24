$(document).ready(function() {
    window.myPaint = initpaint();
 
});

function initpaint() {

    var canvas = document.getElementById("dessin");
    var ctx = canvas.getContext("2d");
    var xdown, ydown, xup, yup;
    var left = canvas.getBoundingClientRect().left;
    var top = canvas.getBoundingClientRect().top;
    var canvasJquery = $("#dessin");

    var figures = [];

    var paintModule = {

        mouseDownListener: function(evt) {
            xdown = evt.clientX - left;
            ydown = evt.clientY - top;
            xdown = (xdown < 0) ? 0 : xdown;
            ydown = (ydown < 0) ? 0 : ydown;
        },
        mouseUpListener: function(evt) {
            xup = evt.clientX - left;
            yup = evt.clientY - top;
            xup = (xup < 0) ? 0 : xup;
            yup = (yup < 0) ? 0 : yup;
            paintModule.addFigure();
        },

        addFigure: function() {
            var choix = $("#figure").val();
            var color = $("#couleur").val();
            var texte = $("#texte").val();
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
            paintModule.refreshPaint();
        },

        refreshPaint: function() {
              ctx.clearRect(0, 0 , canvas.width, canvas.height);
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
                      case 4:
                        ctx.beginPath();
                        ctx.moveTo(f.x1, f.y1);
                        ctx.lineTo(f.x2, f.y2);
                        ctx.stroke();
                                             
                        break;
                      case 5:
                        ctx.font = 'italic 10pt Calibri';
                        ctx.fillText($("#texte").val(), f.x2, f.y2);
                        break;
                  }
              }
        },
        save: function() {
            console.log(JSON.stringify(figures));
            localStorage[$("#texte").val()] = JSON.stringify(figures);
        },
        load: function() {
            figures = JSON.parse(localStorage[$("#texte").val()] || "[]");
            paintModule.refreshPaint();
        }
    };

    canvasJquery.on('mousedown', paintModule.mouseDownListener);
    canvasJquery.on('mouseup', paintModule.mouseUpListener);
    $('#saveButton').on('click', paintModule.save);
    $('#loadButton').on('click', paintModule.load);
    return paintModule;
}