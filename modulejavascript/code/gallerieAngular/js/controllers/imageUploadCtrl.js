angular.module("gallerieApp")
.controller("imageUploadCtrl", 
    function($scope, $location, Upload, $timeout, serverUrl) {
    // le module "Upload" est en fait "ngFileUpload"

    $scope.fileProgressBars = {};
    $scope.uploadAlerts = [];

    // appel upload si le tableau files est modifié
    $scope.$watch('files', function() {
        $scope.upload($scope.files);
    });

    $scope.upload = function(files) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                if (!file.$error) {
                    // creation de la progressBar
                    var p = {};
                    $scope.fileProgressBars[file.name] = 0;
                    var url =  serverUrl + 'images/upload';
                    Upload.upload({
                        url: url,
                        data: {
                            fichier: file
                        }
                    }).progress(function(evt) {
                        var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                        $scope.fileProgressBars[evt.config.data.fichier.name] = progressPercentage;
                    }).success(function(data, status, headers, config) {
                        $timeout(function() {
                            delete $scope.fileProgressBars[config.data.fichier.name];
                            var alertUpload = {type: "success"};
                            alertUpload.message= "successfully uploaded " + config.data.fichier.name;
                            $scope.uploadAlerts.push(alertUpload);
                        });

                    })
                }
            }
        }            
    };
    // enlever la notification au timeout (au bout de 4 secondes)
    $scope.closeAlertUpload = function(index) {
         $scope.uploadAlerts.splice(index, 1);
    };
});
