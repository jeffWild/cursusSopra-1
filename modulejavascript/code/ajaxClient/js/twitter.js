$(document).ready(function() {
    window.twitter = twitterInit();
    twitter.requestTweets(0, 5);
});

function twitterInit() {
    var currentTweetPage = null;
    var divreponse = $("div#reponse");
    var serverUrl = "http://localhost:8080/firstMVCAjaxForm/message";
    var currentSearch = "";
    var inputSearch = $("input#search");


    var twitterModule = {
        checkSearch: function() {
            var newSearch = inputSearch.val();
            if (newSearch != currentSearch) {
                // le contenu du champ de recherche a changé
                currentSearch = newSearch;
                twitterModule.requestTweets(0, 5);
            }
            else {
                // revérifier dans une seconde mon champ de recherche
                setTimeout(twitterModule.checkSearch, 1000);
            }
        },

        refreshTweets : function() {
            divreponse.empty();
            for (var i = 0; i < currentTweetPage.content.length; i++) {
                var message = currentTweetPage.content[i];
                divreponse.append("<div class='message'>"
                                + "<h3>" + message.titre + "</h3>"
                                + "<p>" + message.corps + "</p>"
                                + "</div>");
            }
            twitterModule.generatePageLinks();
        },
        requestTweets: function(noPage, taillePage) {
            noPage = noPage || 0;
            taillePage = taillePage || 5;
            var url = serverUrl;
            // je fait varier l'url ou on envoie la requette
            // pour intégrer le terme de recherche s'il n'est
            // pas vide
            // sinon, on utilise l'URL classique
            if (currentSearch != "") {
                url += "/titre/" + currentSearch;
            }
            $.getJSON(url + "?noPage=" + noPage
                    + "&taillePage=" + taillePage,
                    function(data) {
                        currentTweetPage = data;
                        twitterModule.refreshTweets();
                        // reverifier le champ de recherche
                        // dans une seconde
                        setTimeout(twitterModule.checkSearch, 1000);
                    });
        },
        generatePageLinks: function() {
            divreponse.append("<hr />");
            for (var index=1; index <= currentTweetPage.totalPages; index++) {
                var lien = $("<a href='#' class='navlien' data-pageNo='"
                        + (index - 1) + "'>" + index + "</a>");
                divreponse.append(lien);
                if (index < currentTweetPage.totalPages) {
                    divreponse.append("<span>,</span>");
                }
            }
            $("a.navlien").click(function() {
                var noPage = parseInt($(this).attr("data-pageNo"));
                twitterModule.requestTweets(noPage, 5);
                // empecher de suivre le lien
                return false;
            })
        }, 
    };

    return twitterModule;
};