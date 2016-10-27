angular.module("todoApp", [])
       .filter("checkedTache", function() {
           // fabrique de filtre nommé checkedTache
           // chaque fois qu'angular a besoin de ce filtre
           // il appellera cette fonction pour recuperer
           // le filtre
           return function(items, showTerminated) {
              if (angular.isArray(items)) {
                // filteredItems est le tableau que je retournerais
                // tache in taches | checkedTache | orderBy
                // ng-repeat:       [toutes les taches] -> items
                // checkedTache:    [items] -> filtrage -> [filteredItems]
                // orderBy:         [filteredItems] -> trier -> [itemsTrie]

                var filteredItems = [];
                angular.forEach(items, function(item) {
                    // ne garder la tache que si elle n'est pas terminée
                    // ou si on a parametrer le filtre en lui
                    // disant de garder aussi les taches terminee (showTerminated)
                    if (item.termine == false || showTerminated == true) {
                        filteredItems.push(item);
                    }
                });
                return filteredItems;
              }
              else {
                 // si ce n'est pas un tableau, retourner tel quel
                 return items;
              }
           };
       })
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
