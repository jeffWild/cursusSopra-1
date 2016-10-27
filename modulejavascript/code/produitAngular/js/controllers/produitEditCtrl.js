angular.module("produitApp")
       .controller("produitEditCtrl",
        function($scope, $http, $location, serverUrl) {

            $scope.saveProduit= function(produit) {
               if (angular.isUndefined(produit.id)) {
                   produit.id = 0;
               }
               $http.post(
                   serverUrl + 'produit/save',
                   produit)
                   .then(function(reponse) {
                       console.log("save successfull");
                       console.log(reponse);
                       // revenir a la page d'acceuil
                       $location.url("/produits");
                   }, function(reponse) {
                       console.log("error on save");
                       console.log(reponse);
                   });
           };

       });