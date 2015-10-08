angular.module('biblioteca')
  .controller('AutorCreateController', function ($scope, $location, Autor, toastr) {

      $scope.save = function() {
          Autor.save($scope.autor, function(data) {
              toastr.success($scope.autor.nome, 'Salvo com Sucesso.');
              $location.path('/autores');
          }, function(data) {
              console.log(data);
              toastr.error(data.data.message, 'Não foi possível Salvar.');
          });
      };

      $scope.cancel = function() {
          $location.path('/autores');
      };


  }).controller('AutorListController', function ($scope, Autor, toastr, $routeParams) {

      $scope.init = function() {
          $scope.nomeFiltro = '';

          Autor.getAll(function(data) {
             $scope.autores = data;
             $scope.quantidade = $scope.autores.length;
          }, function(data) {
              toastr.error(data.data, 'Não autorizado.');
          });
      };

      $scope.busca = function() {

         if ($scope.nomeFiltro) {
              Autor.getFiltroAutor({filtro:$scope.nomeFiltro}, $scope.autor, function(data) {
                  $scope.autores = data;
              }, function(data) {
                   toastr.error(data.data, 'Não autorizado.');
                 });
         } else {
                Autor.getAll(function(data) {
                $scope.autores = data;
              });
         };
      };


  }).controller('AutorDetailController', function ($scope, $modal, $routeParams, $location, Autor, toastr) {


    $scope.init = function() {
        $scope.autor = Autor.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data);
        });
    };

    $scope.update = function() {
        Autor.update({id:$routeParams.id}, $scope.autor, function(data) {
            toastr.info($scope.autor.nome, 'Atualizado com Sucesso.');
            $location.path('/autores');
        },function(data) {
           toastr.error(data.data, 'Não foi possível Atualizar.');
        });
    };

    $scope.delete = function() {

      $scope.autor = Autor.get({id:$routeParams.id}, function(data) {
          $scope.autorExcluido = $scope.autor.nome;
      });

        Autor.delete({id:$routeParams.id}, function() {
            toastr.warning($scope.autorExcluido, 'Removido com Sucesso.');
            $modalInstance.close();
            $location.path('/autores');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data, 'Não foi possível Remover.');
        });
    };

    $scope.cancel = function() {
       $location.path('/autores');
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'AutorDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  });