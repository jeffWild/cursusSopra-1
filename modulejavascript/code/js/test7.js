// la fonction $ en jquery est un selecteur amélioré

// exemple le callback 'ready' est un ajout de jquery
$(document).ready(function() {
    // ajouter un handler du click sur l'element d'identifiant btn1
    $("#btn1").click(function() {
        //console.log('click btn1');
        $("div#contenu li").toggleClass("souligne");
    });

    $("#btn2").on('click', function() {
        var msg = $("#saisie input#message").val();
        $("div#reponse").append("<p>" + msg + "</p>");
    });
    $("#btn3").on('click', function() {
        var msg = $("#saisie input#message").val();
        $("div#reponse").prepend("<p>" + msg + "</p>");
    });
    $("#btn4").on('click', function() {
         $("div#reponse").fadeToggle(2000);
    });
    $("#btn5").on('click', function() {
        $("div#reponse").empty().hide();
        $("div#reponse").load('ajaxcontent.php', function() {
            $("div#reponse").slideDown(1000);
        });
    });
    

});