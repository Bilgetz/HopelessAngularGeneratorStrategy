package fr.hopelessworld.plugin.strategy.impl;

import java.util.Collection;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
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
		directive.append("error: '='");
		directive.append("},");

		directive.append("controller :['$scope',  function($scope) {");

		directive.append("var template = {");

		boolean first = true;
		for (Field field : entity.getFields()) {
			if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null
					|| field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
				if (!first) {
					directive.append("'");
				} else {
					first = false;
				}

				directive.append(field.getSimpleName()).append(": '");
				CharSequence tagName = AnalizedEntityUtils.getEntitiesTagName(field.asAnalyzedEntity());
				directive.append("<").append(tagName);
				directive.append(" select=\"select(entity)\"></").append(tagName).append(">");
				directive.append("<button type=\"button\" ng-click=\"cancel()\" >Cancel</button>");

				directive.append("'");
			}
		}
		directive.append("};");

		directive.append("$scope.deleteEntity = function(entityName, index) {");
		directive.append("if(index != undefined && index >  0) {");
		directive.append("$scope.data[entityName].splice(index, 1);");
		directive.append("} else  {");
		directive.append("$scope.data[entityName] = {};");
		directive.append("}");
		directive.append("};");

		directive.append("$scope.changeEntity = function(entityName, index) {");

		directive.append("var modalInstance = $uibModal.open({");
		directive.append("template: template[entityName],");
		directive.append("controller: ['$scope', '$modalInstance',function ($scope, $uibModalInstance) {");
		directive.append("$scope.select = function (entity) {");
		directive.append("$uibModalInstance.close(entity);");
		directive.append("};");
		directive.append("$scope.cancel = function () {");
		directive.append("$uibModalInstance.dismiss('cancel');");
		directive.append("};");
		directive.append("}]");
		directive.append("});");

		directive.append("modalInstance.result.then(function (entity) {");
		directive.append("if(index != undefined && index >  0) {");
		directive.append("$scope.data[entityName][index] =entity ;");
		directive.append("} else  {");
		directive.append("$scope.data[entityName] = entity;");
		directive.append("}");
		directive.append("}, function () {});");

		directive.append("};");

		directive.append("}]");

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
		directive.append("select : '&'");
		directive.append("},");
		directive.append("controller : '").append(entitiesName).append("Ctrl'");
		directive.append("}");
		directive.append("});");

		return directive;
	}
}
