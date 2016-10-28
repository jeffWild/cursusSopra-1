angular.module("produitApp")
       .controller("produitListCtrl", function($scope, $http, serverUrl) {
           $scope.pages = [];
           $scope.currentPage = 0;
           
           $scope.setCurrentPage= function(noPage){
               $scope.currentPage = noPage;
               console.log("new page =" + noPage); 
               $scope.refresh();
           };
           $scope.isPageActive= function(noPage){
              $scope.currentPage == noPage;
           };
           

           $scope.champTri = 'prix';
           $scope.setChampTri = function(nomChamp) {
               $scope.champTri = nomChamp;
           };

           $scope.produits = [];

           $scope.refresh = function() {
            $http.get(serverUrl + "produit?noPage="
                        + $scope.currentPage + "&taillePage=5")
                    .then(function(reponse) {
                        // en cas de success
                        console.log("success!");
                        //console.log(reponse);
                        $scope.produits = reponse.data.content;
                        $scope.pages = [];
                        for (var idx = 0; idx < reponse.data.totalPages; idx++) {
                            $scope.pages.push(idx);
                        }
                    }, function (reponse) {
                        // en cas d'erreur
                        console.log("erreur!");
                        console.log(reponse);
                    });
           };

           $scope.refresh();
       });
