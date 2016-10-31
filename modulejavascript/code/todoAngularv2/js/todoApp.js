angular.module("todoApp", ["ngRoute"])
       .constant("serverUrl",
                 'http://localhost:8080/todoRestBackend/')
       .config(function($routeProvider, tacheServiceProvider, serverUrl) {
           // le service que l'on configure est "route"
           // donc on inject "routeProvider" dans la fonction
           // config
           $routeProvider.when("/taches", 
                {
                    templateUrl: "views/tacheListe.html",
                    controller: 'tacheListCtrl'   
                });
           $routeProvider.when("/tacheCreate", 
                {
                    templateUrl: "views/tacheEdit.html",
                    controller: 'tacheEditCtrl'   
                });
           $routeProvider.when("/tacheEdit/:tid", 
                {
                    templateUrl: "views/tacheEdit.html",
                    controller: 'tacheEditCtrl'   
                });
                
           $routeProvider.when("/about", 
                {
                    templateUrl: "views/about.html"   
                });
           $routeProvider.otherwise( 
                {
                    templateUrl: "views/tacheListe.html",
                    controller: 'tacheListCtrl'  
                });
           tacheServiceProvider.setUrlBase(serverUrl);
       });