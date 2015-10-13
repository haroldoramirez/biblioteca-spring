angular.module('biblioteca')
    .service('Author',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/authors/:id', {}, {
         update: {method: 'PUT', url: BaseUrl + '/authors/:id', isArray: false},
         query: {isArray: false}
      });
    }]);