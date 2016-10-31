angular.module("todoApp")
       .provider("tacheService", function() {

           var urlbase = "http://localhost";

           return {
               setUrlBase: function(url) {
                   urlbase = url;
               },

               $get : function($http) {
     //--------------Service-------------------------------              
    return  {
        liste : function(noPage, taillePage) {
                return $http.get(urlbase + "taches?pageNo="
                        + noPage + "&pageSize=" + taillePage);
        },
        findById : function(id) {
                return $http.get(urlbase + "taches/" + id);
        },
        save : function(t) {
            return $http.post(urlbase + 'taches/save', t);
        },
        remove : function(id) {
            return $http.post(urlbase + "taches/remove/" + id, {});
        }
    }
    //---------------------------------------------
               }
           };
       });