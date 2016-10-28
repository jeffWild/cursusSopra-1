angular.module("mesServices", [])
       .service("firstService", function() {
           // avec cette methode
           // angular appelera new mafunction
           // pour creer le service
           this.compteur = 5;

           this.incrementAndGet = function() {
               return this.compteur++;
           };
       })
       .factory("secondService", function() {
           // angular appelera cette fonction
           // pour recupérer l'instance du service en question
           // 1 seule fois! (singleton)
           var monCompteurPrive = 2;

           return {
               "incrementAndGet" : function () {
                   return monCompteurPrive++;
               }
           };
       })
       .provider("thirdService", function() {
           var compteurInitialDefaut = 1;
           // nous retournons le provider de thirdService
           return {
               // configuration possible fournie par le provider
               "setInitialCompteur": function(compteur) {
                   compteurInitialDefaut = compteur;
               },

               // fonction retournant le service configuré par le
               // provider
               $get : function() {
                   var monCompteurPrive = compteurInitialDefaut;
                   return {
                       "incrementAndGet" : function () {
                           return monCompteurPrive++;
                       }
                   };
               }
           };
       });