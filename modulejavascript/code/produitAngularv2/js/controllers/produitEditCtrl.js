angular.module("produitApp")
       .controller("produitEditCtrl",
        function($scope, $location, $routeParams, produitService, Upload, $timeout, serverUrl) {
            //console.log($routeParams);
            if (angular.isDefined($routeParams.pid)) {
                //recupérer le produit a editer
                produitService.findById($routeParams.pid)
                            .then(function(reponse) {
                                $scope.newProduit = reponse.data;
                                $scope.existingProduit = true;
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
                $scope.existingProduit = false;
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

           $scope.fileProgressBars = {};
    $scope.uploadAlerts = [];

    // appel upload si le tableau files est modifié
    $scope.$watch('files', function() {
        $scope.upload($scope.files);
    });

    $scope.upload = function(files) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                if (!file.$error) {
                    // creation de la progressBar
                    var p = {};
                    $scope.fileProgressBars[file.name] = 0;
                    var url =  serverUrl + 'images/upload';
                    Upload.upload({
                        url: url,
                        data: {
                            pid: $scope.newProduit.id,
                            fichier: file
                        }
                    }).progress(function(evt) {
                        var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                        $scope.fileProgressBars[evt.config.data.fichier.name] = progressPercentage;
                    }).success(function(data, status, headers, config) {
                        $timeout(function() {
                            delete $scope.fileProgressBars[config.data.fichier.name];
                            var alertUpload = {type: "success"};
                            alertUpload.message= "successfully uploaded " + config.data.fichier.name;
                            $scope.uploadAlerts.push(alertUpload);
                        });

                    })
                }
            }
        }            
    };
    // enlever la notification au timeout (au bout de 4 secondes)
    $scope.closeAlertUpload = function(index) {
         $scope.uploadAlerts.splice(index, 1);
    };

});