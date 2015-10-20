angular.module('biblioteca')
  .controller('CategoryCreateController', function ($scope, $location, Category, toastr) {

      $scope.save = function() {
          Category.save($scope.category, function(data) {
              toastr.success('foi salvo com Sucesso.', 'A categoria: ' + $scope.category.name);
              $location.path('/categories');
          }, function(data) {
              toastr.error(data.data.message, 'Não foi possível Salvar.');
          });
      };

      $scope.cancel = function() {
          $location.path('/categories');
      };


  }).controller('CategoryListController', function ($scope, Category, toastr, $routeParams) {

      $scope.init = function() {
          $scope.nameFilter = '';

          Category.query(function(data) {
             $scope.categories = data.content;
             $scope.quantity = $scope.categories.length;
          }, function(data) {
              toastr.error(data.data, 'Não foi possível Carregar.');
          });
      };

      $scope.filter = function() {

         if ($scope.nameFilter) {
              Category.query({filter:$scope.nameFilter}, $scope.category, function(data) {
                  $scope.categories = data.content;
              }, function(data) {
                   toastr.error(data.data, 'Não foi possível Carregar.');
                 });
         } else {
                Category.query(function(data) {
                $scope.categories = data;
              });
         };
      };


  }).controller('CategoryDetailController', function ($scope, $modal, $routeParams, $location, Category, toastr) {


    $scope.init = function() {
        $scope.category = Category.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };


    $scope.delete = function() {

      $scope.category = Category.get({id:$routeParams.id}, function(data) {
          $scope.categoryExcluded = $scope.category.name;
      });

        Category.delete({id:$routeParams.id}, function() {
            toastr.warning('foi removido com Sucesso.', 'A categoria: ' + $scope.categoryExcluded);
            $modalInstance.close();
            $location.path('/categories');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data.message, 'Não foi possível Remover.');
        });
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'CategoryDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  }).controller('CategoryEditController', function ($scope, $modal, $routeParams, $location, Category, toastr) {


    $scope.init = function() {
        $scope.category = Category.get({id:$routeParams.id}, function(data) {
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };

    $scope.update = function() {
        Category.update({id:$routeParams.id}, $scope.category, function(data) {
            toastr.info('foi atualizado com Sucesso.', 'A categoria: ' + $scope.category.name);
            $location.path('/categories');
        },function(data) {
           toastr.error(data.data.message, 'Não foi possível Atualizar.');
        });
    };

  });