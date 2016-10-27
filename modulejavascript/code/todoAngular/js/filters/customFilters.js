angular.module("customFilters", [])
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
       .filter("unique", function() {
           return function(items, propName) {
               if (!(angular.isArray(items))) {
                   return items;
               }
               var uniqueValues = {};
               angular.forEach(items, function(item) {
                   uniqueValues[item[propName]] = true;
               });
               var result = [];
               for (var prop in uniqueValues) {
                   result.push(prop);
               }
               return result;
           }
       })
       .filter("whereContext", function() {
           return function(items, currentCtx) {
               if (!(angular.isArray(items)) || currentCtx == 'tous') {
                   return items;
               }
               var filteredItems = [];
               angular.forEach(items, function(item) {
                   if (item.contexte == currentCtx) {
                       filteredItems.push(item);
                   }
               });
               return filteredItems;
           };
       });