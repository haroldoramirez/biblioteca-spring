angular.module('biblioteca')
  .controller('PublisherCreateController', function ($scope, $location, Publisher, toastr) {

      $scope.save = function() {
          Publisher.save($scope.publisher, function(data) {
              toastr.success('foi salvo com Sucesso.', 'A editora: ' + $scope.publisher.name);
              $location.path('/publishers');
          }, function(data) {
              toastr.error(data.data.message, 'Não foi possível Salvar.');
          });
      };

      $scope.cancel = function() {
          $location.path('/publishers');
      };


  }).controller('PublisherListController', function ($scope, Publisher, toastr, $routeParams) {

      $scope.init = function() {
          $scope.nameFilter = '';

          Publisher.query(function(data) {
             $scope.publishers = data.content;
             $scope.quantity = $scope.publishers.length;
          }, function(data) {
              toastr.error(data.data, 'Não foi possível Carregar.');
          });
      };

      $scope.filter = function() {

         if ($scope.nameFilter) {
              Publisher.query({filter:$scope.nameFilter}, $scope.publisher, function(data) {
                  $scope.publishers = data.content;
              }, function(data) {
                   toastr.error(data.data, 'Não foi possível Carregar.');
                 });
         } else {
                Publisher.query(function(data) {
                $scope.publishers = data;
              });
         };
      };


  }).controller('PublisherDetailController', function ($scope, $modal, $routeParams, $location, Publisher, toastr) {


    $scope.init = function() {
        $scope.publisher = Publisher.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };


    $scope.delete = function() {

      $scope.publisher = Publisher.get({id:$routeParams.id}, function(data) {
          $scope.publisherExcluded = $scope.publisher.name;
      });

        Publisher.delete({id:$routeParams.id}, function() {
            toastr.warning('foi removido com Sucesso.', 'A editora: ' + $scope.publisherExcluded);
            $modalInstance.close();
            $location.path('/publishers');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data.message, 'Não foi possível Remover.');
        });
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'PublisherDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  }).controller('PublisherEditController', function ($scope, $modal, $routeParams, $location, Publisher, toastr) {


    $scope.init = function() {
        $scope.publisher = Publisher.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };

    $scope.update = function() {
        Publisher.update({id:$routeParams.id}, $scope.publisher, function(data) {
            toastr.info('foi atualizado com Sucesso.', 'A editora: ' + $scope.publisher.name);
            $location.path('/publishers');
        },function(data) {
           toastr.error(data.data.message, 'Não foi possível Atualizar.');
        });
    };

  });