angular.module("gallerieApp", ["ngRoute"])
       .constant("serverUrl",
                 'http://localhost:8080/superGallerie/')
       .config(function($routeProvider, imageServiceProvider, serverUrl) {
           // le service que l'on configure est "route"
           // donc on inject "routeProvider" dans la fonction
           // config
           $routeProvider.when("/images", 
                {
                    templateUrl: "views/imageListe.html",
                    controller: 'imageListCtrl'   
                });
           $routeProvider.when("/imageUpload", 
                {
                    templateUrl: "views/imageUpload.html",
                    controller: 'imageUploadCtrl'   
                });
           $routeProvider.when("/imageEdit/:pid", 
                {
                    templateUrl: "views/imageEdit.html",
                    controller: 'imageEditCtrl'   
                });
                
           $routeProvider.when("/about", 
                {
                    templateUrl: "views/about.html"   
                });
           $routeProvider.otherwise( 
                {
                    templateUrl: "views/imageListe.html",
                    controller: 'imageListCtrl'  
                });
           // configuration de mon service de requettage d'images
           imageServiceProvider.setUrlBase(serverUrl);
       });