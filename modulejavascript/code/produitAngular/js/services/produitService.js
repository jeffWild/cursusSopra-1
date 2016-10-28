angular.module("produitApp")
       .provider("produitService", function() {

           var urlbase = "http://localhost";

           return {
               setUrlBase: function(url) {
                   urlbase = url;
               },

               $get : function($http) {
     //--------------Service-------------------------------              
    return  {
        liste : function(noPage, taillePage) {
                return $http.get(urlbase + "produit?noPage="
                        + noPage + "&taillePage=" + taillePage);
        },
        findById : function(id) {

        },
        save : function(p) {
                return $http.post(urlbase + 'produit/save', p);
        },
        remove : function(id) {

        }
    }
    //---------------------------------------------
               }
           };
       });