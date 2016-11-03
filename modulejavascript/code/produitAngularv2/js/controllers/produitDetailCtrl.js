angular.module("produitApp")
       .controller("produitDetailCtrl",
        function($scope, $location, $routeParams, produitService) {
            
            //console.log($routeParams);
            if (angular.isDefined($routeParams.pid)) {
                //recupérer le produit a editer
                produitService.findById($routeParams.pid)
                            .then(function(reponse) {
                                $scope.produit = reponse.data;
                            }, function (reponse) {
                                // pas de produits....
                                console.log("produit inconnu");
                                $location.url("/produits");
                            });
            }

       });