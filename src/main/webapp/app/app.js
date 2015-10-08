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
            .when('/autores/novo', {
                templateUrl: '/app/views/autores/create.html',
                controller: 'AutorCreateController'
            })
            .when('/autores/detalhe/:id', {
                templateUrl: '/app/views/autores/detail.html',
                controller: 'AutorDetailController'
            })
            .when('/autores', {
                templateUrl: '/app/views/autores/list.html',
                controller: 'AutorListController'
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
           progressBar: false,
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