var myApp = angular.module('DemoApp', ['ngRoute']);
myApp.config(function ($routeProvider) {
    $routeProvider
            .when("/all", {
                templateUrl: "allCars.html",
                controller: ['CarFactory', function (CarFactory) {
                        this.cars = CarFactory.getCars();
                        this.title = "Cars Demo App";
                        this.predicate = "year";
                        this.reverse = false;
                        this.deleteCar = function(id) {
                            CarFactory.deleteCar(id);
                        };
                        this.setCar = function (car) {
                            currentCar = car;
                        };
                    }],
                controllerAs: "allCtrl"
            })
            .when("/new", {
                templateUrl: "addCar.html",
                controller: ['CarFactory', function (CarFactory) {
                        this.addCar = function(car) {
                            CarFactory.addEditCar(car);
                        };
                    }],
                controllerAs: "newCtrl"
            })
            .when("/edit", {
                templateUrl: "editCar.html",
                controller: ['CarFactory', function (CarFactory) {
                        this.oldCar = currentCar;
                        this.editCar = function(car) {
                            car.id = currentCar.id;
                            CarFactory.addEditCar(car);
                        };
                    }],
                controllerAs: "editCtrl"
            })
            .otherwise({
                template: "<h1>Nothing has been selected</h1>"
            });
});
myApp.factory('CarFactory', function () {
    var cars = [
        {id: 1, year: 1997, registered: new Date(1999, 3, 15), make: 'Ford', model: 'E350', description: 'ac, abs, moon', price: 3000}
        , {id: 2, year: 1999, registered: new Date(1996, 3, 12), make: 'Chevy', model: 'Venture', description: 'None', price: 4900}
        , {id: 3, year: 2000, registered: new Date(199, 12, 22), make: 'Chevy', model: 'Venture', description: '', price: 5000}
        , {id: 4, year: 1996, registered: new Date(2002, 3, 15), make: 'Jeep', model: 'Grand Cherokee', description: 'Moon roof', price: 4799}];
    var nextId = cars.length;

    var getCars = function () {
        return cars;
    };
    var deleteCar = function (id) {
        for (var i = 0; i < cars.length; i++) {
            if (cars[i].id == id) {
                cars.splice(i, 1);
                return;
            }
        }
    };
    var addEditCar = function (newcar) {
        if (newcar.id == null) {
            newcar.id = nextId++;
            cars.push(newcar);
        } else {
            for (var i = 0; i < cars.length; i++) {
                if (cars[i].id == newcar.id) {
                    cars[i] = newcar;
                    break;
                }
            }
        }
    };
    return {
        getCars: getCars,
        deleteCar: deleteCar,
        addEditCar: addEditCar
    };
});
var currentCar;