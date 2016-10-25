$(document).ready(function() {
    $("#loadButton").on('click', function() {
        $.getJSON('http://localhost:8080/firstMVCAjaxForm/message',
                function(data) {
                    //console.log(data);
                    var divreponse = $("div#reponse");
                    divreponse.empty();
                    for (var i = 0; i < data.length; i++) {
                        var message = data[i];
                        divreponse.append("<div class='message'>"
                                + "<h3>" + message.titre + "</h3>"
                                + "<p>" + message.corps + "</p>"
                                + "</div>");
                    }
                
                });
    });
});