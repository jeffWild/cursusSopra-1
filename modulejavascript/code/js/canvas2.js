$(document).ready( function() {
    $("#bt1").click(draw1);
    $("#bt2").click(draw2);
    $("#bt3").click(startAnim);
    $("#bt4").click(stopAnim);
    $("#bt5").click(draw3);
    $("#bt6").click(selectColor);
    $("#bt7").click(saveArray);
    $("#bt8").click(loadArray);

    // préselectionne une couleur si on en a sauvegardée une
    $("#couleur").val(localStorage.rectangleColor || '#FF00000');


    var canvasJquery = $("#dessin"); 
    canvasJquery.on("mousemove", function(evt) {
        var canvas = document.getElementById("dessin");
        var rect = canvas.getBoundingClientRect();
        var x = evt.clientX - rect.left;
        var y = evt.clientY - rect.top;
        x = (x < 0) ? 0 : x;
        y = (y < 0) ? 0 : y;
        //console.log("x = " + x + " y = " + y);
    }); 
    canvasJquery.on("mouseenter", function(evt) {
        console.log("mouse entered");
    });
    canvasJquery.on("mouseleave", function(evt) {
        console.log("mouse leaved");
    });
    canvasJquery.on("mousedown", function(evt) {
        if (evt.button == 0) {
            console.log("mouse left down");
        }
        else if (evt.button == 1) {
            console.log("mouse middle down");
        }
        else if (evt.button == 2) {
            console.log("mouse right down");
        }
    });
    canvasJquery.on("mouseup", function(evt) {
        if (evt.button == 0) {
            console.log("mouse left up");
        }
        else if (evt.button == 1) {
            console.log("mouse middle up");
        }
        else if (evt.button == 2) {
            console.log("mouse right up");
            evt.preventDefault();
            return false;
        }
    });
    canvasJquery.on("click", function(evt) {
        console.log("mouse click");
    });

    canvasJquery.on("contextmenu", function(evt) {
        evt.preventDefault();
    })
    

});

function draw1() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.font = 'italic 20pt Calibri';
    context.fillText('bonjour monde', 100, 50);
};

function draw2() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.translate(canvas.width / 2, canvas.height / 2);
    context.scale(0.5, 2.0);
    context.rotate(Math.PI / 4);

    context.font = 'italic 20pt Calibri';
    context.fillText('bonjour monde', 0, 0);
    
    // ici je reinitialise la transformations
    // par exemple la, sans scanling/rotation/tanslation
    context.setTransform(1, 0, 0, 1, 0, 0);

    context.font = 'italic 20pt Calibri';
    context.fillText('bonjour monde', 10, 30);   
}

function startAnim() {
    var startTime = (new Date()).getTime();
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');
    window.rectangleAnimate = true;

    animate(canvas, context, startTime);  
};

function stopAnim() {
    window.rectangleAnimate = false;
};

function animate(canvas, context, startTime) {
    // combien de millisecondes depuis le début de l'animation
    var time = (new Date()).getTime() - startTime;

    var x = 50 + ((time % 3000) / 10);
    var y = 100;

    context.clearRect(0, 0 , canvas.width, canvas.height);

    context.fillStyle = 'blue';
    context.fillRect(x, y, 30, 20);

    if (window.rectangleAnimate) {
        // ici, le navigateur rapellera ce callback pour rafraichir
        // l'affichage
        requestAnimationFrame(function() {
            animate(canvas, context, startTime);
        });
    }
}

function draw3() {
    // localStorage persiste même apres fermeture de l'onglet
    var rectangleColor = localStorage.rectangleColor || '#FF1111';

    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.fillStyle = rectangleColor;
    context.fillRect(10, 10, 300, 200);
}

function selectColor() {
    localStorage.rectangleColor = $("#couleur").val();
    console.log("nouvelle couleur = " + localStorage.rectangleColor);
}

function saveArray() {
    var tab = [
        { "nom" : "bob l'eponge", "email": "bob@ananas.com"},
        { "nom" : "patrick", "email": "patrick@ananas.com"},
        { "nom" : "kylo ren", "email": "kylo@lovelordDark.com"}
    ];
    // sauvegarde sous forme de json de mon tableau dans
    // le localStorage
    localStorage.clients = JSON.stringify(tab);
}

function loadArray() {
    // recharge le tableau depuis le localStorage
    var tab = JSON.parse(localStorage.clients || "[]");
    for (var i = 0; i < tab.length; i++) {
        console.log(tab[i]);
    }
}
