angular.module("gallerieApp")
       .provider("imageService", function() {

           var urlbase = "http://localhost";

           return {
               setUrlBase: function(url) {
                   urlbase = url;
               },

               $get : function($http) {
     //--------------Service-------------------------------              
    return  {
        liste : function(noPage, taillePage) {
                return $http.get(urlbase + "images?noPage="
                        + noPage + "&taillePage=" + taillePage);
        },
        findById : function(id) {
                return $http.get(urlbase + "images/" + id);
        },
        save : function(img) {
            return $http.post(urlbase + 'images/save', img);
        }
    }
    //---------------------------------------------
               }
           };
       });