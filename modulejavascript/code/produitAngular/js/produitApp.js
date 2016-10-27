angular.module("produitApp", ["ngRoute"])
       .config(function($routeProvider) {
           // le service que l'on configure est "route"
           // donc on inject "routeProvider" dans la fonction
           // config
           $routeProvider.when("/produits", 
                {
                    templateUrl: "views/produitListe.html"   
                });
           $routeProvider.when("/produitEdit", 
                {
                    templateUrl: "views/produitEdit.html"   
                });
           $routeProvider.when("/about", 
                {
                    templateUrl: "views/about.html"   
                });
           $routeProvider.otherwise( 
                {
                    templateUrl: "views/produitListe.html"   
                });
       });