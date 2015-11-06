package fr.hopelessworld.plugin.strategy.impl;

import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularDirectiveStrategyTest extends AbstractTestStrategy {

	@Override
	protected AbstractUniqueFileGeneratorStrategy getStrategy() {
		return new AngularDirectiveStrategy();
	}

	@Override
	protected String getExpectedOneEntity() {
		// @formatter:off
		String expected = AngularDirectiveStrategy.PAGING_DIRECTIVE
				+ AngularDirectiveStrategy.FILTERING_DIRECTIVE
				+"angularApp.directive('ngPlayer', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Player,"
				+"scope : {data :'='}"
				+"}"
				+"});" 
				
				+"angularApp.directive('ngPlayerEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerEdit,"
				
				+"scope : {"
					+"data :'=',"
					+"save : '&',"
					+"reset : '&',"
					+"cancel : '&',"
					+"error: '='"
				+"},"
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"};"
				
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
						+ "return $uibModal.open({"
							+ "template: template[entityName],"
							+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
								+ "$scope.select = function (entity) {"
									+ "$uibModalInstance.close(entity);"
								+ "};"
								+ "$scope.cancel = function () {"
									+ "$uibModalInstance.dismiss('cancel');"
								+ "};"
							+ "}]"
						+ "});"
					+"}"
					
					+ "$scope.changeEntity = function(entityName, index) {"
						+ "var modalInstance = getModal(entityName);"
							
						+ "modalInstance.result.then(function (entity) {"
							+ "if(index != undefined && index >  0) {"
								+ "$scope.data[entityName][index] =entity ;"
							+ "} else  {"
									+ "$scope.data[entityName] = entity;"
							+ "}"
						+ "}, function () {});"
							
					+ "};"
					
					+ "$scope.addEntity = function(entityName) {"
						+ "var modalInstance = getModal(entityName);"
						+ "modalInstance.result.then(function (entity) {"
							+ "$scope.data[entityName].push(entity);"
						+ "}, function () {});"
					+ "}"

				+ "}]"
						
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
					+"return {"
						+"restrict : 'E',"
						+"template : angularTemplate.PlayerList,"
						+"scope : {"
							+"select : '&'"
						+"},"
						+ "controller : 'PlayersCtrl'"
					+"}"
				+"});"
				
				+ "";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith2Field() {
		// @formatter:off
		String expected = AngularDirectiveStrategy.PAGING_DIRECTIVE
				+ AngularDirectiveStrategy.FILTERING_DIRECTIVE
				+"angularApp.directive('ngPlayer', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Player,"
				+"scope : {data :'='}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayerEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerEdit,"
				+"scope : {"
				+"data :'=',"
				+"save : '&',"
				+"reset : '&',"
				+"cancel : '&',"
				+"error: '='"
				+"},"
				
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"};"
					
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
					+ "return $uibModal.open({"
						+ "template: template[entityName],"
						+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
							+ "$scope.select = function (entity) {"
								+ "$uibModalInstance.close(entity);"
							+ "};"
							+ "$scope.cancel = function () {"
								+ "$uibModalInstance.dismiss('cancel');"
							+ "};"
						+ "}]"
					+ "});"
				+"}"
				
				+ "$scope.changeEntity = function(entityName, index) {"
					+ "var modalInstance = getModal(entityName);"
						
					+ "modalInstance.result.then(function (entity) {"
						+ "if(index != undefined && index >  0) {"
							+ "$scope.data[entityName][index] =entity ;"
						+ "} else  {"
								+ "$scope.data[entityName] = entity;"
						+ "}"
					+ "}, function () {});"
						
				+ "};"
				
				+ "$scope.addEntity = function(entityName) {"
					+ "var modalInstance = getModal(entityName);"
					+ "modalInstance.result.then(function (entity) {"
						+ "$scope.data[entityName].push(entity);"
					+ "}, function () {});"
				+ "}"
				
				+ "}]"
				
						
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
					+"return {"
					+"restrict : 'E',"
						+"template : angularTemplate.PlayerList,"
							+"scope : {"
							+"select : '&'"
						+"},"
						+ "controller : 'PlayersCtrl'"
					+"}"
				+"});"
				
				+ ""; 
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith1EntityField() {
		// @formatter:off
		String expected = AngularDirectiveStrategy.PAGING_DIRECTIVE
				+ AngularDirectiveStrategy.FILTERING_DIRECTIVE
				+"angularApp.directive('ngPlayer', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Player,"
				+"scope : {data :'='}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayerEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerEdit,"
				+"scope : {"
				+"data :'=',"
				+"save : '&',"
				+"reset : '&',"
				+"cancel : '&',"
				+"error: '='"
				+"},"
				
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"team: '<ng-teams select=\"select(entity)\"></ng-teams><button type=\"button\" ng-click=\"cancel()\" >Cancel</button>'"
					+"};"
					
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
					+ "return $uibModal.open({"
						+ "template: template[entityName],"
						+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
							+ "$scope.select = function (entity) {"
								+ "$uibModalInstance.close(entity);"
							+ "};"
							+ "$scope.cancel = function () {"
								+ "$uibModalInstance.dismiss('cancel');"
							+ "};"
						+ "}]"
					+ "});"
				+"}"
				
				+ "$scope.changeEntity = function(entityName, index) {"
					+ "var modalInstance = getModal(entityName);"
						
					+ "modalInstance.result.then(function (entity) {"
						+ "if(index != undefined && index >  0) {"
							+ "$scope.data[entityName][index] =entity ;"
						+ "} else  {"
								+ "$scope.data[entityName] = entity;"
						+ "}"
					+ "}, function () {});"
						
				+ "};"
				
				+ "$scope.addEntity = function(entityName) {"
					+ "var modalInstance = getModal(entityName);"
					+ "modalInstance.result.then(function (entity) {"
						+ "$scope.data[entityName].push(entity);"
					+ "}, function () {});"
				+ "}"
				
				+ "}]"
				
				
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
						+"restrict : 'E',"
						+"template : angularTemplate.PlayerList,"
						+"scope : {"
							+"select : '&'"
						+"},"
						+ "controller : 'PlayersCtrl'"
					+"}"
				+"});"
				
				
				+"angularApp.directive('ngTeam', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Team,"
				+"scope : {data :'='}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngTeamEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.TeamEdit,"
				+"scope : {"
				+"data :'=',"
				+"save : '&',"
				+"reset : '&',"
				+"cancel : '&',"
				+"error: '='"
				+"},"
				
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"};"
					
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
					+ "return $uibModal.open({"
						+ "template: template[entityName],"
						+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
							+ "$scope.select = function (entity) {"
								+ "$uibModalInstance.close(entity);"
							+ "};"
							+ "$scope.cancel = function () {"
								+ "$uibModalInstance.dismiss('cancel');"
							+ "};"
						+ "}]"
					+ "});"
				+"}"
				
				+ "$scope.changeEntity = function(entityName, index) {"
					+ "var modalInstance = getModal(entityName);"
						
					+ "modalInstance.result.then(function (entity) {"
						+ "if(index != undefined && index >  0) {"
							+ "$scope.data[entityName][index] =entity ;"
						+ "} else  {"
								+ "$scope.data[entityName] = entity;"
						+ "}"
					+ "}, function () {});"
						
				+ "};"
				
				+ "$scope.addEntity = function(entityName) {"
					+ "var modalInstance = getModal(entityName);"
					+ "modalInstance.result.then(function (entity) {"
						+ "$scope.data[entityName].push(entity);"
					+ "}, function () {});"
				+ "}"
				
				+ "}]"
				


				+"}"
				+"});"
				
				+"angularApp.directive('ngTeams', function() {"
				+"return {"
					+"restrict : 'E',"
					+"template : angularTemplate.TeamList,"
					+"scope : {"
						+"select : '&'"
					+"},"
					+ "controller : 'TeamsCtrl'"
				+"}"
				+"});"
				
				
				+ ""; 
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWithOneToManyField() {
		// @formatter:off
		String expected = AngularDirectiveStrategy.PAGING_DIRECTIVE
				+ AngularDirectiveStrategy.FILTERING_DIRECTIVE
				+"angularApp.directive('ngPlayer', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Player,"
				+"scope : {data :'='}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayerEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerEdit,"
				+"scope : {"
				+"data :'=',"
				+"save : '&',"
				+"reset : '&',"
				+"cancel : '&',"
				+"error: '='"
				+"},"
				
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"team: '<ng-teams select=\"select(entity)\"></ng-teams><button type=\"button\" ng-click=\"cancel()\" >Cancel</button>'"
					+"};"
					
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
					+ "return $uibModal.open({"
						+ "template: template[entityName],"
						+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
							+ "$scope.select = function (entity) {"
								+ "$uibModalInstance.close(entity);"
							+ "};"
							+ "$scope.cancel = function () {"
								+ "$uibModalInstance.dismiss('cancel');"
							+ "};"
						+ "}]"
					+ "});"
				+"}"
				
				+ "$scope.changeEntity = function(entityName, index) {"
					+ "var modalInstance = getModal(entityName);"
						
					+ "modalInstance.result.then(function (entity) {"
						+ "if(index != undefined && index >  0) {"
							+ "$scope.data[entityName][index] =entity ;"
						+ "} else  {"
								+ "$scope.data[entityName] = entity;"
						+ "}"
					+ "}, function () {});"
						
				+ "};"
				
				+ "$scope.addEntity = function(entityName) {"
					+ "var modalInstance = getModal(entityName);"
					+ "modalInstance.result.then(function (entity) {"
						+ "$scope.data[entityName].push(entity);"
					+ "}, function () {});"
				+ "}"
				
				+ "}]"
				
				
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
					+"restrict : 'E',"
					+"template : angularTemplate.PlayerList,"
					+"scope : {"
						+"select : '&'"
					+"},"
					+ "controller : 'PlayersCtrl'"
				+"}"
				+"});"
				
				
				+"angularApp.directive('ngTeam', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.Team,"
				+"scope : {data :'='}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngTeamEdit', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.TeamEdit,"
				+"scope : {"
				+"data :'=',"
				+"save : '&',"
				+"reset : '&',"
				+"cancel : '&',"
				+"error: '='"
				+"},"
				
				+ "controller :['$scope','$uibModal',function($scope,$uibModal) {"
				
					+"var template = {"
					+"players: '<ng-players select=\"select(entity)\"></ng-players><button type=\"button\" ng-click=\"cancel()\" >Cancel</button>'"
					+"};"
					
					+ "$scope.deleteEntity = function(entityName, index) {"
					+ "if(index != undefined && index >  0) {"
						+ "$scope.data[entityName].splice(index, 1);"
					+ "} else  {"
						+ "$scope.data[entityName] = {};"
					+ "}"
					+ "};"
					
					+"function getModal(entityName) {"
					+ "return $uibModal.open({"
						+ "template: template[entityName],"
						+ "controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {"
							+ "$scope.select = function (entity) {"
								+ "$uibModalInstance.close(entity);"
							+ "};"
							+ "$scope.cancel = function () {"
								+ "$uibModalInstance.dismiss('cancel');"
							+ "};"
						+ "}]"
					+ "});"
				+"}"
				
				+ "$scope.changeEntity = function(entityName, index) {"
					+ "var modalInstance = getModal(entityName);"
						
					+ "modalInstance.result.then(function (entity) {"
						+ "if(index != undefined && index >  0) {"
							+ "$scope.data[entityName][index] =entity ;"
						+ "} else  {"
								+ "$scope.data[entityName] = entity;"
						+ "}"
					+ "}, function () {});"
						
				+ "};"
				
				+ "$scope.addEntity = function(entityName) {"
					+ "var modalInstance = getModal(entityName);"
					+ "modalInstance.result.then(function (entity) {"
						+ "$scope.data[entityName].push(entity);"
					+ "}, function () {});"
				+ "}"
				
				+ "}]"
				

				+"}"
				+"});"
				
				+"angularApp.directive('ngTeams', function() {"
				+"return {"
					+"restrict : 'E',"
					+"template : angularTemplate.TeamList,"
					+"scope : {"
						+"select : '&'"
					+"},"
					+ "controller : 'TeamsCtrl'"
				+"}"
				+"});"
				
				+ ""; 
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedNoEntity() {
		// @formatter:off
		String expected = AngularDirectiveStrategy.PAGING_DIRECTIVE
				+ AngularDirectiveStrategy.FILTERING_DIRECTIVE; 
		// @formatter:on
		return expected;
	}

}
