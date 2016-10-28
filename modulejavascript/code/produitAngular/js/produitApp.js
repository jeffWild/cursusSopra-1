angular.module("produitApp", ["ngRoute"])
       .constant("serverUrl",
                 'http://localhost:8080/springMVCProduitForm/')
       .config(function($routeProvider, produitServiceProvider, serverUrl) {
           // le service que l'on configure est "route"
           // donc on inject "routeProvider" dans la fonction
           // config
           $routeProvider.when("/produits", 
                {
                    templateUrl: "views/produitListe.html",
                    controller: 'produitListCtrl'   
                });
           $routeProvider.when("/produitCreate", 
                {
                    templateUrl: "views/produitEdit.html",
                    controller: 'produitEditCtrl'   
                });
           $routeProvider.when("/produitEdit/:pid", 
                {
                    templateUrl: "views/produitEdit.html",
                    controller: 'produitEditCtrl'   
                });
                
           $routeProvider.when("/about", 
                {
                    templateUrl: "views/about.html"   
                });
           $routeProvider.otherwise( 
                {
                    templateUrl: "views/produitListe.html",
                    controller: 'produitListCtrl'  
                });
           // configuration de mon service de requettage de produits
           produitServiceProvider.setUrlBase(serverUrl);
       });