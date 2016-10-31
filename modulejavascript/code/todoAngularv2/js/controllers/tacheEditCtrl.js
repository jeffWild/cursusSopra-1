angular.module("todoApp")
       .controller("tacheEditCtrl",
        function($scope, $location, $routeParams, tacheService) {
            $scope.today = new Date();
            //console.log($routeParams);
            if (angular.isDefined($routeParams.tid)) {
                //recupérer la tache a editer
                tacheService.findById($routeParams.tid)
                            .then(function(reponse) {
                                $scope.newTache = reponse.data;
                                // si je veux qu'angularjs puisse valider la dateLimite
                                // le plus simple est de founir une date javascript dans le champ
                                $scope.newTache.dateLimite = new Date($scope.newTache.dateLimite);
                            }, function (reponse) {
                                // pas de produits....
                                console.log("tache inconnue");
                                $location.url("/taches");
                            });
            }
            else {
                // nouveau produit
                $scope.newTache = {
                    id: 0,
                    titre: "",
                    priorite: 5,
                    contexte: "aucun",
                    termine: false,
                    dateLimite: new Date()
                };
            }

            $scope.saveTache= function(tache) {
                console.log($scope.tacheForm);
                // ne sauvegarder que si valide
               if ($scope.tacheForm.$valid) {
                    tacheService.save(tache)
                        .then(function(reponse) {
                            console.log("save successfull");
                            console.log(reponse);
                            // revenir a la page d'acceuil
                            $location.url("/taches");
                        }, function(reponse) {
                            console.log("error on save");
                            console.log(reponse);
                        });
                }
           };

       });