angular.module("angularApp", ["ngRoute", "mesServices"])
        .config(function(thirdServiceProvider){
            // je configure le provide de service "thirdService"
            thirdServiceProvider.setInitialCompteur(42);
        })
        .controller("rootController", function($scope,
                                               firstService,
                                               secondService,
                                               thirdService) {
            $scope.message = "hello from root";
            $scope.compteur = 1;
            $scope.data = {
                compteur: 1
            };
            $scope.$on("monEvent", function(evt, obj) {
                console.log("evt recu chez root " + evt);
                console.log(obj);
            });

            $scope.messageService = "none";
            $scope.appelServiceFirst = function() {
                 $scope.messageService= 
                    "valeur: " + firstService.incrementAndGet();
            };
            $scope.messageService2 = "none";
            $scope.appelServiceSecond = function() {
                 $scope.messageService2= 
                    "valeur: " + secondService.incrementAndGet();
            };
            $scope.messageService3 = "none";
            $scope.appelServiceThird = function() {
                 $scope.messageService3= 
                    "valeur: " + thirdService.incrementAndGet();
            };

        })
        .controller("childController", function($scope, firstService) {
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
            $scope.messageService = "none";
            $scope.appelServiceFirst = function() {
                 $scope.messageService= 
                    "valeur: " + firstService.incrementAndGet();
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