angular.module('biblioteca')
    .service('Author',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/authors/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/authors', isArray: true},
         update: {method: 'PUT', url: BaseUrl + '/authors/:id', isArray: false},
         getFilter: {method: 'GET', url: BaseUrl + '/authors/filter/:filter', isArray: true}
      });
    }]);