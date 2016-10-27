// le module todoApp dépend du module customFilters
angular.module("todoApp", ["customFilters"])
       .controller("todoCtrl", function($scope) {

        // controle la visibilite du formulaire   
        $scope.formVisible = false;
        $scope.showForm = function() {
            $scope.formVisible = !($scope.formVisible);
        };

        // controle le tri de la table
        $scope.champTri = 'titre';
        $scope.setChampTri = function(nomChamp) {
            $scope.champTri = nomChamp;
        };

        $scope.ctx = 'tous';
        $scope.chooseContext = function(ctx) {
            $scope.ctx = ctx;
            console.log("new contexte: " + ctx);
        };

        $scope.isContextSelected = function(ctx) {
            return ctx == $scope.ctx;
        };;

        $scope.taches = [
            {"id": 1,
                "titre": "faire menage",
                "priorite": 2,
                "contexte": "maison",
                "dateLimite": new Date(2016, 10, 27),
                "termine" : false 
            },
            {"id": 2,
                "titre": "conquerir le monde",
                "priorite": 4,
                "contexte": "personnel",
                "dateLimite": new Date(2026, 10, 27),
                "termine" : false 
            },
            {"id": 3,
                "titre": "faire compta",
                "priorite": 3,
                "contexte": "bureau",
                "dateLimite": new Date(2016, 11, 27),
                "termine" : false 
            },
            {"id": 4,
                "titre": "faire vaisselle",
                "priorite": 5,
                "contexte": "maison",
                "dateLimite": new Date(2016, 10, 29),
                "termine" : false 
            },
            {"id": 5,
                "titre": "envoyer mail commande",
                "priorite": 1,
                "contexte": "bureau",
                "dateLimite": new Date(2016, 11, 10),
                "termine" : false 
            }
            ];

        $scope.addTache = function(newTache) {
            if (angular.isUndefined(newTache.termine)) {
                newTache.termine = false;
            }
            $scope.taches.push(newTache);
            $scope.newTache = {};
        };

        $scope.terminerTache = function(tid) {
            for (var i =0; i < $scope.taches.length; i++) {
                if ($scope.taches[i].id == tid) {
                    $scope.taches[i].termine = true;
                    break;
                }
            }
        };
       });
