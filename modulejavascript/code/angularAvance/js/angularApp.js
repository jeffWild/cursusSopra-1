angular.module("angularApp", ["ngRoute"])
        .controller("rootController", function($scope) {
            $scope.message = "hello from root";
            $scope.compteur = 1;
            $scope.data = {
                compteur: 1
            };
            $scope.$on("monEvent", function(evt, obj) {
                console.log("evt recu chez root " + evt);
                console.log(obj);
            });

        })
        .controller("childController", function($scope) {
            $scope.salutation = "hello from child";
            //$scope.compteur = 2;
            $scope.incremente = function() {
                $scope.compteur = $scope.compteur + 1;
                $scope.data.compteur = $scope.data.compteur + 1;
                //$scope.$parent.compteur = $scope.compteur + 1;
            };
            $scope.emitEvent = function() {
                $scope.$emit("monEvent",
                         { msg: "hello emit from child"});
            };
            $scope.broadcastEvent = function() {
                $scope.$broadcast("monEvent",
                 { msg: "hello broadcast from child"});
            };

        })
        .controller("grandChildController", function($scope, $rootScope) {
            $scope.compteur = 5;
            $rootScope.compteur2 = 1;
            $scope.incremente = function() {
                $scope.compteur = $scope.compteur + 1;
                // incremente dans le rootScope
                $rootScope.compteur2 = $rootScope.compteur2 + 1; 
            };
            $scope.$on("monEvent", function(evt, obj) {
                console.log("evt recu chez grandChild " + evt);
                console.log(obj);
            });
        });