angular.module('biblioteca')
  .controller('AuthorCreateController', function ($scope, $location, Author, toastr) {

      $scope.save = function() {
          Author.save($scope.author, function(data) {
              toastr.success('foi salvo com Sucesso.', 'O autor: ' + $scope.author.name);
              $location.path('/authors');
          }, function(data) {
              toastr.error(data.data.message, 'Não foi possível Salvar.');
          });
      };

      $scope.cancel = function() {
          $location.path('/authors');
      };


  }).controller('AuthorListController', function ($scope, Author, toastr, $routeParams) {

      $scope.init = function() {
          $scope.nameFilter = '';

          Author.query(function(data) {
             $scope.authors = data.content;
             $scope.quantity = $scope.authors.length;
          }, function(data) {
              toastr.error(data.data, 'Não foi possível Carregar.');
          });
      };

      $scope.filter = function() {

         if ($scope.nameFilter) {
              Author.query({filter:$scope.nameFilter}, $scope.author, function(data) {
                  $scope.authors = data.content;
              }, function(data) {
                   toastr.error(data.data, 'Não foi possível Carregar.');
                 });
         } else {
                Autor.query(function(data) {
                $scope.authors = data;
              });
         };
      };


  }).controller('AuthorDetailController', function ($scope, $modal, $routeParams, $location, Author, toastr) {


    $scope.init = function() {
        $scope.author = Author.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };


    $scope.delete = function() {

      $scope.author = Author.get({id:$routeParams.id}, function(data) {
          $scope.authorExcluded = $scope.author.name;
      });

        Author.delete({id:$routeParams.id}, function() {
            toastr.warning('foi removido com Sucesso.', 'O autor: ' + $scope.authorExcluded);
            $modalInstance.close();
            $location.path('/authors');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data.message, 'Não foi possível Remover.');
        });
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'AuthorDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  }).controller('AuthorEditController', function ($scope, $modal, $routeParams, $location, Author, toastr) {


    $scope.init = function() {
        $scope.author = Author.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };

    $scope.update = function() {
        Author.update({id:$routeParams.id}, $scope.author, function(data) {
            toastr.info('foi atualizado com Sucesso.', 'O autor: ' + $scope.author.name);
            $location.path('/authors');
        },function(data) {
           toastr.error(data.data.message, 'Não foi possível Atualizar.');
        });
    };

  });