angular.module('biblioteca')
  .factory('BaseUrl', function($location) {
     return 'http://' + $location.host() + ':3333' ;
   });