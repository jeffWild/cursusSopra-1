// tous les fonctionnalités angular seront regroupées par module
// on va déclarer notre propre module

/*var myapp = */
// ici, déclaration d'un nouveau module
angular.module("myApp", []);

// si on omet les dependances, on récupere un module existant
// la fonction controller permet de creer un controller dans le module
// deux argument, le nom du controller, et la fonction qui
// sera appelée pour creer le controller (le constructeur)
// cette fonction de construction d'un controller peu declarer
// des arguments, qui seront automatiquement injectés par angular
// ici, le $scope, dont on va reparler juste en dessous
angular.module("myApp")
       .controller("appCtrl", function($scope) {
           // le $scope représente l'état de l'application
           // pour ce qui est géré par ce controller
           // le controller sera affecté à une partie
           // de la page, et dans cette partie, nous aurons
           // accès a ce même $scope
           $scope.message = "bonjour depuis myApp";
           $scope.nom = "anonyme";

           $scope.changeMessage = function() {
               $scope.message = "bonjour, " + $scope.nom;
           };

           $scope.$watch("nom", function(newval, oldval){
               console.log("nom changed: "
                        + oldval + " -> " + newval);
           });
       });
