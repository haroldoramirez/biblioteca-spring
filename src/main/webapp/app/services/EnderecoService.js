angular.module('biblioteca')
    .service('Autor',['$resource', 'BaseUrl',
    function($resource, BaseUrl){
      return $resource(BaseUrl + '/autores/:id', {}, {
         getAll: {method: 'GET', url: BaseUrl + '/autores', isArray: true},
         update: {method: 'PUT', url: BaseUrl + '/autores/:id', isArray: false},
         getFiltroAutor: {method: 'GET', url: BaseUrl + '/autores/filtro/:filtro', isArray: true}
      });
    }]);