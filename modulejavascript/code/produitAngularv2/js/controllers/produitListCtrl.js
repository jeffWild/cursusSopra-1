angular.module("produitApp")
       .controller("produitListCtrl", 
                    function($scope, $location, produitService) {
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
               produitService.liste($scope.currentPage, 5)
                    .then(function(reponse) {
                        console.log("success!");
                        //console.log(reponse);
                        $scope.produits = reponse.data.content;
                        $scope.pages = [];
                        for (var idx = 0; idx < reponse.data.totalPages; idx++) {
                            $scope.pages.push(idx);
                        }
                    }, function (reponse) {
                        console.log("erreur!");
                        console.log(reponse);
                    });
           };
           $scope.editerProduit = function(pid) {
               $location.url("/produitEdit/" + pid);
           };
           $scope.detailProduit = function(pid) {
               $location.url("/produitDetail/" + pid);
           };

           $scope.supprimerProduit = function(pid) {
               produitService.remove(pid)
                             .then(function(reponse) {
                                 $scope.refresh();
                             }, function(reponse) {
                                 console.log("erreur a la suppression");
                             });
           };
           
           $scope.refresh();
       });
