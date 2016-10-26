angular.module("myApp", [])
       .controller("appCtrl", function($scope) {
           
           $scope.champTri = 'prix';
           $scope.setChampTri = function(nomChamp) {
               $scope.champTri = nomChamp;
           };

           if (angular.isDefined(localStorage.produits)) {
               $scope.produits = JSON.parse(localStorage.produits);
           }
           else {
            $scope.produits = [
                {"id": 1,
                    "nom": "tofu toufou",
                    "prix": 18.99,
                    "poids": 0.5,
                    "stock": 15},
                    {"id": 2,
                    "nom": "steak de lama",
                    "prix": 38.99,
                    "poids": 0.45,
                    "stock": 2},
                    {"id": 3,
                    "nom": "graine de courges",
                    "prix": 5.99,
                    "poids": 0.35,
                    "stock": 25},
                    {"id": 4,
                    "nom": "biere oceania",
                    "prix": 7.99,
                    "poids": 1.0,
                    "stock": 10},
                    {"id": 5,
                    "nom": "pain au chocolat sans gluten",
                    "prix": 1.99,
                    "poids": 0.150,
                    "stock": 0}
                ];
                localStorage.produits = JSON.stringify($scope.produits);
           }

        $scope.addProduit = function(newProduit) {
            $scope.produits.push(newProduit);
            localStorage.produits = JSON.stringify($scope.produits);
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
                localStorage.produits = JSON.stringify($scope.produits);
            }
        };
       });
