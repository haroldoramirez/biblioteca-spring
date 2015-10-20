angular.module('biblioteca')
    .service('Author',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/authors/:id', {}, {
         update: {method: 'PUT', url: BaseUrl + '/authors/:id', isArray: false},
         query: {isArray: false}
      });
    }])
    .service('Category',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/categories/:id', {}, {
         update: {method: 'PUT', url: BaseUrl + '/categories/:id', isArray: false},
         query: {isArray: false}
      });
    }]);