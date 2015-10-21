angular
    .module
        ('biblioteca',
            ['ngRoute',
             'ngResource',
             'toastr',
             'ngAnimate',
             'angular-loading-bar',
             'ui.bootstrap'
            ]
        )
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/app/views/home.html',
                controller: 'HomeController'
            })
            .when('/authors/new', {
                templateUrl: '/app/views/authors/create.html',
                controller: 'AuthorCreateController'
            })
            .when('/authors/edit/:id', {
                templateUrl: '/app/views/authors/edit.html',
                controller: 'AuthorEditController'
            })
            .when('/authors/detail/:id', {
                templateUrl: '/app/views/authors/detail.html',
                controller: 'AuthorDetailController'
            })
            .when('/authors', {
                templateUrl: '/app/views/authors/list.html',
                controller: 'AuthorListController'
            })
            .when('/categories/new', {
                templateUrl: '/app/views/categories/create.html',
                controller: 'CategoryCreateController'
            })
            .when('/categories/edit/:id', {
                templateUrl: '/app/views/categories/edit.html',
                controller: 'CategoryEditController'
            })
            .when('/categories/detail/:id', {
                templateUrl: '/app/views/categories/detail.html',
                controller: 'CategoryDetailController'
            })
            .when('/categories', {
                templateUrl: '/app/views/categories/list.html',
                controller: 'CategoryListController'
            })
            .when('/publishers/new', {
                templateUrl: '/app/views/publishers/create.html',
                controller: 'PublisherCreateController'
            })
            .when('/publishers/edit/:id', {
                templateUrl: '/app/views/publishers/edit.html',
                controller: 'PublisherEditController'
            })
            .when('/publishers/detail/:id', {
                templateUrl: '/app/views/publishers/detail.html',
                controller: 'PublisherDetailController'
            })
            .when('/publishers', {
                templateUrl: '/app/views/publishers/list.html',
                controller: 'PublisherListController'
            });
   })
   .config(function(toastrConfig) {
        angular.extend(toastrConfig, {
           allowHtml: false,
           autoDismiss: false,
           closeButton: true,
           closeHtml: '<button>&times;</button>',
           containerId: 'toast-container',
           extendedTimeOut: 5000,
           iconClasses: {
             error: 'toast-error',
             info: 'toast-info',
             success: 'toast-success',
             warning: 'toast-warning'
           },
           maxOpened: 0,
           messageClass: 'toast-message',
           newestOnTop: true,
           onHidden: null,
           onShown: null,
           positionClass: 'toast-bottom-right',
           preventDuplicates: false,
           preventOpenDuplicates: false,
           progressBar: true,
           tapToDismiss: true,
           target: 'body',
           templates: {
             toast: 'directives/toast/toast.html',
             progressbar: 'directives/progressbar/progressbar.html'
           },
           timeOut: 5000,
           titleClass: 'toast-title',
           toastClass: 'toast'
        });
   });