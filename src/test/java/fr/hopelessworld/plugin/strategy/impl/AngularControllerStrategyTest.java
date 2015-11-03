package fr.hopelessworld.plugin.strategy.impl;

import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularControllerStrategyTest extends AbstractTestStrategy {

	@Override
	protected AbstractUniqueFileGeneratorStrategy getStrategy() {
		return new AngularControllerStrategy();
	}

	@Override
	protected String getExpectedNoEntity() {
		// @formatter:off
			String expected ="";

		// @formatter:on						
		return expected;
	}

	@Override
	protected String getExpectedOneEntity() {
		// @formatter:off
		String expected =""
				// multiple
				+ "angularApp.controller('PlayersCtrl',['$scope','$rootScope','$translate','PlayerFactory',PlayersCtrl]);"
				+ "function PlayersCtrl($scope,$rootScope,$translate,$PlayerFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = [{id: 'name', value:'name', type: 'text'}];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
					+ "$rootScope.loading = true;"
					+ "$PlayerFactory.find($scope.page.number, $scope.page.size,$scope.criterias,[]).then(function(result) {"
						+ "$scope.entities = result.entities;"
						+ "$rootScope.page = result.page;"
						+ "$rootScope.loading = false;"
					+ "}, function(msg) {"
						+ "alert(msg);"
						+ "$rootScope.loading = false;"
					+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
					+ "$scope.criterias.splice(index, 1);"
					+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
					+ "if($scope.selectedField.type == 'number') {"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'text'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'entity'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
					+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
			
			
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single
				+ "angularApp.controller('PlayerCtrl',['$scope','$rootScope','PlayerFactory','$routeParams',PlayerCtrl]);"
				+ "function PlayerCtrl($scope,$rootScope,$PlayerFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
					
				+ "$PlayerFactory.get($routeparams.id,[]).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				
				+ "";
		// @formatter:on						
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith2Field() {
		// @formatter:off
		String expected =""
				// multiple
				+ "angularApp.controller('PlayersCtrl',['$scope','$rootScope','$translate','PlayerFactory',PlayersCtrl]);"
				+ "function PlayersCtrl($scope,$rootScope,$translate,$PlayerFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = ["
				+ "{id: 'name', value:'name', type: 'text'},"
				+ "{id: 'creation', value:'creation', type: 'text'}"
				+ "];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
					+ "$rootScope.loading = true;"
					+ "$PlayerFactory.find($scope.page.number, $scope.page.size,$scope.criterias,[]).then(function(result) {"
						+ "$scope.entities = result.entities;"
						+ "$rootScope.page = result.page;"
						+ "$rootScope.loading = false;"
					+ "}, function(msg) {"
						+ "alert(msg);"
						+ "$rootScope.loading = false;"
					+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
					+ "$scope.criterias.splice(index, 1);"
					+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
					+ "if($scope.selectedField.type == 'number') {"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'text'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'entity'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
					+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
			
			
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single
				+ "angularApp.controller('PlayerCtrl',['$scope','$rootScope','PlayerFactory','$routeParams',PlayerCtrl]);"
				+ "function PlayerCtrl($scope,$rootScope,$PlayerFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
					
				+ "$PlayerFactory.get($routeparams.id,[]).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				
				+ "";
		// @formatter:on						
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith1EntityField() {
		// @formatter:off
		String expected =""
				// multiple Player
				+ "angularApp.controller('PlayersCtrl',['$scope','$rootScope','$translate','PlayerFactory','TeamFactory',PlayersCtrl]);"
				+ "function PlayersCtrl($scope,$rootScope,$translate,$PlayerFactory,$TeamFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = ["
				+ "{id: 'name', value:'name', type: 'text'},"
				+ "{id: 'team', value:'team', type: 'entity'}"
				+ "];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
					+ "$rootScope.loading = true;"
					+ "$PlayerFactory.find($scope.page.number, $scope.page.size,$scope.criterias,['team']).then(function(result) {"
						+ "$scope.entities = result.entities;"
						+ "$rootScope.page = result.page;"
						+ "$rootScope.loading = false;"
					+ "}, function(msg) {"
						+ "alert(msg);"
						+ "$rootScope.loading = false;"
					+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
					+ "$scope.criterias.splice(index, 1);"
					+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
					+ "if($scope.selectedField.type == 'number') {"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'text'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'entity'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
					+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
			
			
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single Player
				+ "angularApp.controller('PlayerCtrl',['$scope','$rootScope','PlayerFactory','$routeParams',PlayerCtrl]);"
				+ "function PlayerCtrl($scope,$rootScope,$PlayerFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
					
				+ "$PlayerFactory.get($routeparams.id,['team']).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				
				//Team
				
				// multiple Team
				+ "angularApp.controller('TeamsCtrl',['$scope','$rootScope','$translate','TeamFactory',TeamsCtrl]);"
				+ "function TeamsCtrl($scope,$rootScope,$translate,$TeamFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = ["
				+ "{id: 'name', value:'name', type: 'text'}"
				+ "];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
				+ "$rootScope.loading = true;"
				+ "$TeamFactory.find($scope.page.number, $scope.page.size,$scope.criterias,[]).then(function(result) {"
				+ "$scope.entities = result.entities;"
				+ "$rootScope.page = result.page;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
				+ "$scope.criterias.splice(index, 1);"
				+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
				+ "if($scope.selectedField.type == 'number') {"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
				+ "} else if($scope.selectedField.type == 'text'){"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
				+ "} else if($scope.selectedField.type == 'entity'){"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
				+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single Team
				+ "angularApp.controller('TeamCtrl',['$scope','$rootScope','TeamFactory','$routeParams',TeamCtrl]);"
				+ "function TeamCtrl($scope,$rootScope,$TeamFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
				
				+ "$TeamFactory.get($routeparams.id,[]).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				

				
				
				+ "";
		// @formatter:on						
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWithOneToManyField() {
		// @formatter:off
		String expected =""
				// multiple Player
				+ "angularApp.controller('PlayersCtrl',['$scope','$rootScope','$translate','PlayerFactory','TeamFactory',PlayersCtrl]);"
				+ "function PlayersCtrl($scope,$rootScope,$translate,$PlayerFactory,$TeamFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = ["
				+ "{id: 'name', value:'name', type: 'text'},"
				+ "{id: 'team', value:'team', type: 'entity'}"
				+ "];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
					+ "$rootScope.loading = true;"
					+ "$PlayerFactory.find($scope.page.number, $scope.page.size,$scope.criterias,['team']).then(function(result) {"
						+ "$scope.entities = result.entities;"
						+ "$rootScope.page = result.page;"
						+ "$rootScope.loading = false;"
					+ "}, function(msg) {"
						+ "alert(msg);"
						+ "$rootScope.loading = false;"
					+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
					+ "$scope.criterias.splice(index, 1);"
					+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
					+ "if($scope.selectedField.type == 'number') {"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'text'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
					+ "} else if($scope.selectedField.type == 'entity'){"
					+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
					+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
			
			
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single Player
				+ "angularApp.controller('PlayerCtrl',['$scope','$rootScope','PlayerFactory','$routeParams',PlayerCtrl]);"
				+ "function PlayerCtrl($scope,$rootScope,$PlayerFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
					
				+ "$PlayerFactory.get($routeparams.id,['team']).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				
				//Team
				
				// multiple Team
				+ "angularApp.controller('TeamsCtrl',['$scope','$rootScope','$translate','TeamFactory','PlayerFactory',TeamsCtrl]);"
				+ "function TeamsCtrl($scope,$rootScope,$translate,$TeamFactory,$PlayerFactory) {"
				+ "$rootScope.loading = true;"
				
				+ "$scope.criterias = [];"
				+ "$scope.criteriaField = ["
				+ "{id: 'name', value:'name', type: 'text'},"
				+ "{id: 'players', value:'players', type: 'entity'}"
				+ "];"
				+ "$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];"
				+ "$scope.subResources = {};"
				
				+ "$scope.pageChanged = function() {"
				+ "$rootScope.loading = true;"
				+ "$TeamFactory.find($scope.page.number, $scope.page.size,$scope.criterias,['players']).then(function(result) {"
				+ "$scope.entities = result.entities;"
				+ "$rootScope.page = result.page;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "}"
				
				+ "$scope.supressCriteria = function(index) {"
				+ "$scope.criterias.splice(index, 1);"
				+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.addCriteria = function() {"
				+ "if($scope.selectedField.type == 'number') {"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
				+ "} else if($scope.selectedField.type == 'text'){"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
				+ "} else if($scope.selectedField.type == 'entity'){"
				+ "$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
				+ "}"
				+ "$scope.selectedField= {};"
				+ "$scope.selectedOperation= {};"
				+ "$scope.selectedValue = '';"
				+ "$scope.pageChanged();"
				+ "}"
				
				+ "$scope.pageChanged();"
				
				+ "};"
				
				//single Team
				+ "angularApp.controller('TeamCtrl',['$scope','$rootScope','TeamFactory','$routeParams',TeamCtrl]);"
				+ "function TeamCtrl($scope,$rootScope,$TeamFactory,$routeparams) {"
				+ "$rootScope.loading = true;"
				
				+ "$TeamFactory.get($routeparams.id,['players']).then(function(entity) {"
				+ "$scope.data=entity;"
				+ "$rootScope.loading = false;"
				+ "}, function(msg) {"
				+ "alert(msg);"
				+ "$rootScope.loading = false;"
				+ "});"
				+ "};"
				

				
				
				+ "";
		// @formatter:on						
		return expected;
	}

}
