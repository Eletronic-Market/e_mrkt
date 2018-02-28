angular.module("ConsumidorApp", [])
        .value('urlBase', 'http://localhost:8080/mrkt/mrktRest/')
        .controller("ConsumidorController", function ($http, urlBase) {

    var self = this;
    self.usuario = 'Lenno Sousa';

    self.chamados = [];
    self.chamado = undefined;


    self.novo = function () {
      self.chamado = {};
    };

    self.salvar = function () {
      var metodo = 'POST';
      if(self.chamado.id){
        metodo = 'PUT';
      }

      $http({
        method: metodo,
        url: urlBase + 'consumidor/',
        data: self.chamado
      }).then(function successCallback(response){
          self.atualizarTabela();
      }, function errorCallback(response){
        self.ocorreuErro();
      });
    };

    self.alterar = function (chamado) {
      self.chamado = chamado;
    };

    self.deletar = function (chamado) {
      self.chamado = chamado;

      $http({
        method: 'DELETE',
        url: urlBase + 'consumidor/' + self.chamado.id + '/'
      }).then(function successCallback(response){
        self.atualizarTabela();
      }, function errorCallback(response){
        self.ocorreuErro();
      });
    };

    self.concluir = function (chamado) {
      alert("TO DO");
    };

    self.ocorreuErro = function () {
      alert("Ocorreu um erro inesperado!");
    };

    self.atualizarTabela = function () {
      $http({
        method: 'GET',
        url: urlBase + 'consumidor/'
      }).then(function successCallback(response){
        self.chamados = response.data;
        self.chamado = undefined;
      }, function errorCallback(response){
        self.ocorreuErro();
      });
    };

    self.activate = function () {
      self.atualizarTabela();
    };

    self.activate();

});
