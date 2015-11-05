package fr.hopelessworld.plugin.strategy.impl;

import java.util.Collection;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;
import fr.hopelessworld.plugin.utils.AnalizedEntityUtils;

public final class AngularDirectiveStrategy extends AbstractUniqueFileGeneratorStrategy {
	// @formatter:off
	protected final static String PAGING_DIRECTIVE = "angularApp.directive('ngPaging', function() {" 
			+ "return {"
			+ "restrict : 'E'," 
			+ "template: angularTemplate.PagingTemplate,"
			+ "scope : {page :'=',pageChanged : '&'}"
			+ "}"
			+ "});";
	protected final static String FILTERING_DIRECTIVE = ""
			+"angularApp.directive('ngFiltering', function() {"
			+"return {"
			+"restrict:'E',"
			+"template: angularTemplate.FiletringTemplate,"
			+"scope : {criteriaField :'=',subResources :'=',criterias :'=',criteriaChanged : '&'},"
			+"controller : function($scope) {"
			+"$scope.selectedField={};"
			+"$scope.selectedOperation={};"
			+"$scope.selectedValue='';"
			+"$scope.selectedEntity={};"
			+"$scope.criteriaOperation=[{id:':',value:'='},{id:'<',value:'inf'},{id:'>',value:'sup'}];"
			+"$scope.addCriteria=function() {"
			+"if($scope.selectedField.type == 'number') {"
			+"$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
			+"} else if($scope.selectedField.type == 'text'){"
			+"$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});"
			+"} else if($scope.selectedField.type == 'entity'){"
			+"$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });"
			+"}"
			+"$scope.selectedField={};"
			+"$scope.selectedOperation={};"
			+"$scope.selectedValue='';"
			+"$scope.criteriaChanged();"
			+"};"
			
			+"$scope.supressCriteria = function(index) {"
			+"$scope.criterias.splice(index, 1);"
			+"$scope.criteriaChanged();"
			+"};"
			
			+"}"
			+"}"
			+"});";
	// @formatter:on
	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder directives = new StringBuilder();
		directives.append(PAGING_DIRECTIVE);
		directives.append(FILTERING_DIRECTIVE);
		for (AnalizedEntity entity : entities) {
			directives.append(this.getShowForEntity(entity));
			directives.append(this.getEditForEntity(entity));
			directives.append(this.getListForEntity(entity));
		}

		return directives;
	}

	private Object getShowForEntity(AnalizedEntity entity) {
		StringBuilder directive = new StringBuilder();
		String entityName = entity.getSimpleName();
		directive.append("angularApp.directive('ng").append(entityName).append("', function() {");
		directive.append("return {");
		directive.append("restrict : 'E',");
		directive.append("template : angularTemplate.").append(entityName).append(",");
		directive.append("scope : {data :'='}");
		directive.append("}");
		directive.append("});");
		return directive;
	}

	private Object getEditForEntity(AnalizedEntity entity) {
		StringBuilder directive = new StringBuilder();
		String entityName = entity.getSimpleName();

		directive.append("angularApp.directive('ng").append(entityName).append("Edit', function() {");
		directive.append("return {");
		directive.append("restrict : 'E',");
		directive.append("template : angularTemplate.").append(entityName).append("Edit,");
		directive.append("scope : {");
		directive.append("data :'=',");
		directive.append("save : '&',");
		directive.append("reset : '&',");
		directive.append("cancel : '&',");
		directive.append("delete : '&',");
		directive.append("change : '&',");
		directive.append("error: '='");
		directive.append("},");

		directive.append("link: function ($scope, elem, attr) {");
		directive.append("$scope.deleteEntity= function(entityName) {");
		directive.append("$scope.delete({ entityName : entityName});");
		directive.append("}");
		directive.append("$scope.changeEntity= function(entityName) {");
		directive.append("$scope.change({ entityName : entityName});");
		directive.append("}");
		directive.append("}");

		directive.append("}");
		directive.append("});");

		return directive;
	}

	private Object getListForEntity(AnalizedEntity entity) {
		StringBuilder directive = new StringBuilder();
		String entityName = entity.getSimpleName();
		String entitiesName = AnalizedEntityUtils.getEntitiesName(entityName);

		directive.append("angularApp.directive('ng").append(entitiesName).append("', function() {");
		directive.append("return {");
		directive.append("restrict : 'E',");
		directive.append("template : angularTemplate.").append(entityName).append("List,");
		directive.append("scope : {");
		directive.append("entities :'=',");
		directive.append("pageChanged : '&',");
		directive.append("subResources: '=',");
		directive.append("criterias: '=',");
		directive.append("criteriaField: '='");
		directive.append("}");
		directive.append("}");
		directive.append("});");

		return directive;
	}
}
