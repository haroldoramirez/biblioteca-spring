angular.module('biblioteca')
  .controller('BookCreateController', function ($scope, $location, Book, Author, Category, Publisher, toastr) {

  $scope.book = {
      authors: []
  };

      $scope.init = function(){
          Author.getAll(function(data){
              $scope.authors = data.content;
          });

          Category.getAll(function(data){
              $scope.categories = data.content;
          });

          Publisher.getAll(function(data){
              $scope.publishers = data.content;
          });
      };

        $scope.addAuthor = function(authorSelected) {
            $scope.book.authors.push(authorSelected);
            var index2=$scope.authors.indexOf(authorSelected);
            $scope.authors.splice(index2,1);
            $scope.authorSelected = null;
        };

        $scope.removeAuthor = function(author) {
            var index=$scope.book.authors.indexOf(author);
            $scope.book.authors.splice(index,1);
            $scope.authors.push(author);
        };

      $scope.save = function() {
                    console.log($scope.book);
          Book.save($scope.book, function(data) {
              toastr.success('foi salvo com Sucesso.', 'O livro: ' + $scope.book.title);
              $location.path('/books');
          }, function(data) {
              toastr.error(data.data.message, 'Não foi possível Salvar.');
          });
      };

      $scope.cancel = function() {
          $location.path('/books');
      };


  }).controller('BookListController', function ($scope, Book, toastr, $routeParams) {

      $scope.init = function() {
          $scope.nameFilter = '';

          Book.query(function(data) {
             $scope.books = data.content;
             $scope.quantity = $scope.books.length;
          }, function(data) {
              toastr.error(data.data, 'Não foi possível Carregar.');
          });
      };

      $scope.filter = function() {

         if ($scope.nameFilter) {
              Book.query({filter:$scope.nameFilter}, $scope.books, function(data) {
                  $scope.books = data.content;
              }, function(data) {
                   toastr.error(data.data, 'Não foi possível Carregar.');
                 });
         } else {
                Book.query(function(data) {
                $scope.books = data;
              });
         };
      };


  }).controller('BookDetailController', function ($scope, $modal, $routeParams, $location, Author, Book, Category, Publisher, toastr) {


    $scope.init = function() {
        $scope.book = Book.get({id:$routeParams.id}, function(data) {
        $scope.author = Author.getAll();
        $scope.category = Category.getAll();
        $scope.publisher = Publisher.getAll();
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };


    $scope.delete = function() {

      $scope.book = Book.get({id:$routeParams.id}, function(data) {
          $scope.bookExcluded = $scope.book.title;
      });

        Book.delete({id:$routeParams.id}, function() {
            toastr.warning('foi removido com Sucesso.', 'O livro: ' + $scope.bookExcluded);
            $modalInstance.close();
            $location.path('/books');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data.message, 'Não foi possível Remover.');
        });
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'BookDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  }).controller('BookEditController', function ($scope, $modal, $routeParams, $location, Author, Book, Publisher, Category, toastr) {


    $scope.book = {
      authors: []
    };

    $scope.init = function() {
        $scope.book = Book.get({id:$routeParams.id}, function(data) {
        $scope.authors = Author.getAll();
        $scope.categories = Category.getAll();
        $scope.publishers = Publisher.getAll();
        },function(data) {
            toastr.error(data.data, 'Não foi possível Carregar.');
        });
    };

    $scope.addAuthor = function(authorSelected) {
        $scope.book.authors.push(authorSelected);
        var index2=$scope.authors.indexOf(authorSelected);
        $scope.authors.splice(index2,1);
        $scope.authorSelected = null;
    };

    $scope.removeAuthor = function(author) {
        var index=$scope.book.authors.indexOf(author);
        $scope.book.authors.splice(index,1);
        $scope.authors.push(author);
    };


    $scope.update = function() {
        Book.update({id:$routeParams.id}, $scope.books, function(data) {
            toastr.info('foi atualizado com Sucesso.', 'O livro: ' + $scope.book.title);
            $location.path('/book');
        },function(data) {
           toastr.error(data.data.message, 'Não foi possível Atualizar.');
        });
    };

  });