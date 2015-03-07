(function(){
    home.controller('loginController', ['$scope','$http', function($scope,$http){
       console.log("LoginController INITIALIZED");
            this.login;
            this.password;
            this.HashedPassword;

            this.loggIn = function(){
                this.HashedPassword = calcMD5(this.password);
                $scope.showLoader = true;
                var test = URL + "Login/" + this.login + "/" + this.HashedPassword;
                debugger;
                $http.get(URL + "Login/" + this.login + "/" + this.HashedPassword)
                        .success(function(data){
                            if(data !== ""){
                                if(data.role === "P") {
                                    var tmp = data.login.substring(1,data.login.length);
                                    localStorage.setItem("login", tmp);
                                } else {
                                    localStorage.setItem("login", data.login);
                                }
                                
                                localStorage.setItem("name", data.firstName + " " + data.lastName);
                                localStorage.setItem("password", data.password);
                                localStorage.setItem("studyGroup", data.studyGroupID);
                                localStorage.setItem("email", data.email);
                                localStorage.setItem("role", data.role);
                                $scope.$emit('reloadPage', null);
                                initPage();
                            }   else {
                                alert("Přihlášení se nepodařilo :( asi na <> heslo");
                            } 
                            initPage($scope);
                            $scope.showLoader = false;
                            location.reload(true);
                }).error(function(){
                    alert("neco je spatne :(");
                    $scope.showLoader = false;
                });
            };
            this.loggOut = function(){
                localStorage.setItem("login", "undefined");
                localStorage.setItem("name", "undefined");
                localStorage.setItem("password", "undefined");
                localStorage.setItem("studyGroup", "undefined");
                localStorage.setItem("email", "undefined");
                localStorage.setItem("role", "undefined");
                $scope.$emit('reloadPage', null);
                $scope.loggedUserIsParrent = false;
                logOut();
                location.reload(true);
            };
    }]);


})();



