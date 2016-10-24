$(document).ready( function() {
    $("#bt1").click(draw1);
    $("#bt2").click(draw2);
    $("#bt3").click(draw3);
    $("#bt4").click(draw4);
    $("#bt5").click(draw5);
    $("#bt6").click(draw6);
});

function draw1() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    // differente techniques, mais la principale
    // c'est d'utiliser un chemin (path)
    context.beginPath();
    context.rect(10, 10, 100, 50);
    context.fillStyle = 'yellow';
    context.fill();
    context.lineWidth = 1;
    context.strokeStyle = 'black';
    context.stroke();
    context.closePath();
};

function draw2() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.fillStyle='red';
    //context.strokeStyle = 'blue';
    context.fillRect(120, 100, 100, 50);
};

function draw3() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.beginPath();
    context.moveTo(250, 30);
    context.lineTo(canvas.width - 40, 30);
    context.lineWidth = 20;
    context.strokeStyle = "#4411FF";
    context.lineCap = 'butt';
    // c'est au moment du stroke que l'affichage se fait réelement
    context.stroke();
    context.closePath();

    context.beginPath();
    context.moveTo(250, 70);
    context.lineTo(canvas.width - 40, 70);
    context.lineWidth = 20;
    context.strokeStyle = "#4411FF";
    context.lineCap = 'round';
    // c'est au moment du stroke que l'affichage se fait réelement
    context.stroke();
    context.closePath();

    context.beginPath();
    context.moveTo(250, 110);
    context.lineTo(canvas.width - 40, 110);
    context.lineWidth = 20;
    context.strokeStyle = "#4411FF";
    context.lineCap = 'square';
    // c'est au moment du stroke que l'affichage se fait réelement
    context.stroke();
    context.closePath();
    
}

function draw4() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');

    context.beginPath();
    context.arc(50, 300, 30, 1.1 * Math.PI, 1.9 * Math.PI, false);
    context.lineWidth = 5;
    context.strokeStyle = 'black';
    context.stroke();
    context.closePath();

    context.beginPath();
    context.moveTo(50, 360);
    context.quadraticCurveTo(150, 100, 200, 150);
    context.lineWidth = 5;
    context.strokeStyle = 'black';
    context.stroke();
    context.closePath();

};

function draw5() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');
    
    context.beginPath();
    context.rect(200, 150, 300, 150);
    var grad  = context.createLinearGradient(200, 150, 500, 300);
    grad.addColorStop(0, '#EE1111');
    grad.addColorStop(0.5, '#EEEE11');
    grad.addColorStop(1, '#11EE11');
    context.fillStyle = grad;
    context.fill();
    context.closePath();
};

function draw6() {
    var canvas = document.getElementById("dessin");
    var context = canvas.getContext('2d');
    
    var x = 100;
    var y = 100;
    var width = 150;
    var height = 100;
    var poulet = new Image();

    poulet.onload = function() {
        context.drawImage(poulet, x, y, width, height);
    };

    poulet.src = "img/chicken.jpg";
}
