angular.module("produitApp")
       .controller("produitListCtrl", function($scope, $http) {
           
           $scope.champTri = 'prix';
           $scope.setChampTri = function(nomChamp) {
               $scope.champTri = nomChamp;
           };

           $scope.produits = [];


           $http.get("http://localhost:8080/springMVCProduitForm/produit")
                .then(function(reponse) {
                    // en cas de success
                    console.log("success!");
                    //console.log(reponse);
                    $scope.produits = reponse.data.content;
                }, function (reponse) {
                    // en cas d'erreur
                    console.log("erreur!");
                    console.log(reponse);
                });
                


           $scope.addProduit = function(newProduit) {
               $scope.produits.push(newProduit);
               $scope.newProduit = {};
           };

            $scope.supprimerProduit = function(pid) {
                var index = -1;
                for (var i =0; i < $scope.produits.length; i++) {
                    if ($scope.produits[i].id == pid) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    $scope.produits.splice(index, 1);
                }
            };
       });
