angular.module("gallerieApp")
       .controller("imageListCtrl", 
                    function($scope, $location, imageService) {
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
           
           $scope.images = [];

           $scope.refresh = function() {
               imageService.liste($scope.currentPage, 9)
                    .then(function(reponse) {
                        console.log("success!");
                        //console.log(reponse);
                        $scope.images = reponse.data.content;
                        $scope.pages = [];
                        for (var idx = 0; idx < reponse.data.totalPages; idx++) {
                            $scope.pages.push(idx);
                        }
                    }, function (reponse) {
                        console.log("erreur!");
                        console.log(reponse);
                    });
           };
           $scope.editerImage = function(iid) {
               $location.url("/imageEdit/" + iid);
           };
           
           $scope.refresh();
       });
