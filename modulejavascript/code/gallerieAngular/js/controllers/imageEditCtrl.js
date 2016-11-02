angular.module("gallerieApp")
       .controller("produitEditCtrl",
        function($scope, $location, $routeParams, produitService) {
            //console.log($routeParams);
            if (angular.isDefined($routeParams.pid)) {
                //recupérer le produit a editer
                produitService.findById($routeParams.pid)
                            .then(function(reponse) {
                                $scope.newProduit = reponse.data;
                            }, function (reponse) {
                                // pas de produits....
                                console.log("produit inconnu");
                                $location.url("/produits");
                            });
            }
            else {
                // nouveau produit
                $scope.newProduit = {
                    id: 0,
                    nom: "",
                    prix: 0.0,
                    poids: 0.0
                };
            }

            $scope.saveProduit= function(produit) {
                console.log($scope.produitForm);
                // ne sauvegarder que si valide
               if ($scope.produitForm.$valid) {
                    produitService.save(produit)
                        .then(function(reponse) {
                            console.log("save successfull");
                            console.log(reponse);
                            // revenir a la page d'acceuil
                            $location.url("/produits");
                        }, function(reponse) {
                            console.log("error on save");
                            console.log(reponse);
                        });
                }
           };

       });