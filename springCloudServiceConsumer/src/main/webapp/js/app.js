angular.module('test', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'ret.html',
        controller: 'retCtr'
    })
}).controller('retCtr', function ($scope, $http) {
    $http.get('service').success(function (data) {
    	alert(data+"");
        $scope.ret = data;
    });
});