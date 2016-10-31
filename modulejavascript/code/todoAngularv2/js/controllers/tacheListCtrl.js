angular.module("todoApp")
       .controller("tacheListCtrl", 
                    function($scope, $location, tacheService) {
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
           

           $scope.champTri = 'priorite';
           $scope.setChampTri = function(nomChamp) {
               $scope.champTri = nomChamp;
           };

           $scope.taches = [];

           $scope.refresh = function() {
               tacheService.liste($scope.currentPage, 5)
                    .then(function(reponse) {
                        console.log("success!");
                        //console.log(reponse);
                        $scope.taches = reponse.data.content;
                        $scope.pages = [];
                        for (var idx = 0; idx < reponse.data.totalPages; idx++) {
                            $scope.pages.push(idx);
                        }
                    }, function (reponse) {
                        console.log("erreur!");
                        console.log(reponse);
                    });
           };
           $scope.editerTache = function(tid) {
               $location.url("/tacheEdit/" + tid);
           };
           $scope.supprimerTache = function(tid) {
               tacheService.remove(tid)
                             .then(function(reponse) {
                                 $scope.refresh();
                             }, function(reponse) {
                                 console.log("erreur a la suppression");
                             });
           };
           
           $scope.refresh();
       });
