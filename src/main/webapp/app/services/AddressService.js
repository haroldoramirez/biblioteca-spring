angular.module('biblioteca')
    .service('Author',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/authors/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/authors', isArray: false},
         update: {method: 'PUT', url: BaseUrl + '/authors/:id', isArray: false},
         query: {isArray: false}
      });
    }])
    .service('Category',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/categories/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/categories', isArray: false},
         update: {method: 'PUT', url: BaseUrl + '/categories/:id', isArray: false},
         query: {isArray: false}
      });
    }])
    .service('Publisher',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/publishers/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/publishers', isArray: false},
         update: {method: 'PUT', url: BaseUrl + '/publishers/:id', isArray: false},
         query: {isArray: false}
      });
    }])
    .service('Book',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/books/:id', {}, {
         update: {method: 'PUT', url: BaseUrl + '/books/:id', isArray: false},
         query: {isArray: false}
      });
    }]);