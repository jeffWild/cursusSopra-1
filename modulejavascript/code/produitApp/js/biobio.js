$(document).ready(function() {
    window.biobio = biobioInit();
    biobio.requestProduits(0, 5);
});

function biobioInit() {
    var currentProduitPage = null;
    var divreponse = $("div#reponse");
    var serverUrl = "http://localhost:8080/springMVCProduitForm/produit";
    var currentNomSearch = "";
    var inputNomSearch = $("input#searchNom");
    var currentPrixSearch = "";
    var inputPrixSearch = $("input#searchPrix");


    var produitModule = {
        checkSearch: function() {
            var newSearch = inputNomSearch.val();
            var mustRequest = false;
            if (newSearch != currentNomSearch) {
                currentNomSearch = newSearch;
                mustRequest = true;
            }
            newSearch = inputPrixSearch.val();
            if (newSearch != currentPrixSearch) {
                currentPrixSearch = newSearch;
                mustRequest = true;
            }
            if (mustRequest) {
                produitModule.requestProduits(0, 5);;
            }
            else {
                setTimeout(produitModule.checkSearch, 1000);
            }
        },

        refreshProduits : function() {
            divreponse.empty();
            for (var i = 0; i < currentProduitPage.content.length; i++) {
                var produit = currentProduitPage.content[i];
                divreponse.append("<div class='produit'>"
                                + "<h3>" + produit.nom + "</h3>"
                                + "<p> prix: " + produit.prix 
                                + " poids: " + produit.poids + "</p>"
                                + "</div>");
            }
            produitModule.generatePageLinks();
        },
        requestProduits: function(noPage, taillePage) {
            noPage = noPage || 0;
            taillePage = taillePage || 5;
            var url = serverUrl;
            if (currentNomSearch != "" && currentPrixSearch == "") {
                url += "/filtreNom/" + currentNomSearch;
            }
            else if (currentNomSearch == "" && currentPrixSearch != "") {
                url += "/filtrePrix/" + currentPrixSearch;
            }
            else if (currentNomSearch != "" && currentPrixSearch != "") {
                url += "/filtreNomEtPrix/"
                    + currentNomSearch + "/"
                    + currentPrixSearch;
            }
            $.getJSON(url + "?noPage=" + noPage
                    + "&taillePage=" + taillePage,
                    function(data) {
                        currentProduitPage = data;
                        produitModule.refreshProduits();
                        // reverifier le champ de recherche
                        // dans une seconde
                        setTimeout(produitModule.checkSearch, 1000);
                    });
        },
        generatePageLinks: function() {
            divreponse.append("<hr />");
            for (var index=1; index <= currentProduitPage.totalPages; index++) {
                var lien = $("<a href='#' class='navlien' data-pageNo='"
                        + (index - 1) + "'>" + index + "</a>");
                divreponse.append(lien);
                if (index < currentProduitPage.totalPages) {
                    divreponse.append("<span>,</span>");
                }
            }
            $("a.navlien").click(function() {
                var noPage = parseInt($(this).attr("data-pageNo"));
                produitModule.requestProduits(noPage, 5);
                // empecher de suivre le lien
                return false;
            })
        }, 
    };

    return produitModule;
};