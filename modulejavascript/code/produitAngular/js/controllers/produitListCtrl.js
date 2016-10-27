angular.module("produitApp")
       .controller("produitListCtrl", function($scope) {
           
           $scope.champTri = 'prix';
           $scope.setChampTri = function(nomChamp) {
               $scope.champTri = nomChamp;
           };

           $scope.produits = [];

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
