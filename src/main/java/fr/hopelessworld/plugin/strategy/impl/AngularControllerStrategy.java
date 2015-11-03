package fr.hopelessworld.plugin.strategy.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularControllerStrategy extends AbstractUniqueFileGeneratorStrategy {

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {

		StringBuilder controllers = new StringBuilder();

		for (AnalizedEntity entity : entities) {

			controllers.append(this.getForMultiple(entity));
			controllers.append(this.getForSingle(entity));
		}

		return controllers;
	}

	private Object getForMultiple(AnalizedEntity entity) {
		StringBuilder controller = new StringBuilder();
		boolean first = true;
		String entityName = entity.getSimpleName();
		String entitiesName = this.getEntitiesName(entityName);

		List<Field> subEntityFields = getSubResource(entity);

		String controllerName = entitiesName + "Ctrl";
		String factoryName = entityName + "Factory";

		controller.append("angularApp.controller('").append(controllerName).append("',");
		controller.append("['$scope','$rootScope','$translate',");
		controller.append("'").append(factoryName).append("',");

		for (Field field : subEntityFields) {
			controller.append("'").append(field.asAnalyzedEntity().getSimpleName()).append("Factory'");
			controller.append(",");
		}
		controller.append(controllerName).append("]);");

		controller.append("function ").append(controllerName).append("($scope,$rootScope,$translate,");
		controller.append("$").append(factoryName);

		for (Field field : subEntityFields) {
			controller.append(",");
			controller.append("$").append(field.asAnalyzedEntity().getSimpleName()).append("Factory");
		}
		controller.append(") {");
		// Corp du controller
		controller.append("$rootScope.loading = true;");

		controller.append("$scope.criterias = [];");
		controller.append("$scope.criteriaField = [");

		first = true;
		for (Field field : entity.getFields()) {

			if (field.getAnnotation(Id.class) == null) {
				CharSequence fieldName = field.getSimpleName();
				if (!first) {
					controller.append(",");
				} else {
					first = false;
				}

				controller.append("{id: '").append(fieldName).append("',");
				// TODO : use translate
				controller.append(" value:'").append(fieldName).append("',");

				controller.append(" type: '");

				if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null
						|| field.getAnnotation(OneToMany.class) != null
						|| field.getAnnotation(ManyToMany.class) != null) {
					/* une entity ou une liste */
					controller.append("entity");
				} else if (field.getAnnotation(Column.class) != null) {
					/* un champ basic */
					TypeMirror typeMirror = field.asType();

					if (String.class.getCanonicalName().equals(typeMirror.toString())) {
						// type String
						controller.append("text");
					} else if (Date.class.getCanonicalName().equals(typeMirror.toString())) {
						// type date
						controller.append("text");
					} else if (field.asType().getKind() == TypeKind.LONG
							|| Long.class.getCanonicalName().equals(typeMirror.toString())
							|| field.asType().getKind() == TypeKind.INT
							|| Integer.class.getCanonicalName().equals(typeMirror.toString())) {
						// type number
						controller.append("number");
					} else if (field.asType().getKind() == TypeKind.BOOLEAN
							|| Boolean.class.getCanonicalName().equals(typeMirror.toString())) {
						// type boolean
						controller.append("boolean");
					} else {
						// autre type (inconu)
						controller.append("text");
					}

				}
				controller.append("'}");
			}
		}
		controller.append("];");

		controller.append(
				"$scope.criteriaOperation = [{id: ':', value:'='}, {id: '<', value:'<='}, {id: '>', value:'>='}];");
		// TODO : subressource
		controller.append("$scope.subResources = {");
		controller.append(this.subResourceToLoad(subEntityFields, "", ":[]"));
		controller.append("};");

		for (Field field : subEntityFields) {
			controller.append("$").append(field.asAnalyzedEntity().getSimpleName())
					.append("Factory.find().then(function(result) {");
			controller.append("$scope.subResources.").append(field.getSimpleName()).append(" = result.entities;");
			controller.append("}, function(msg) {");
			controller.append("alert(msg);");
			controller.append("});");
		}

		// pageChanged function in scope

		controller.append("$scope.pageChanged = function() {");
		controller.append("$rootScope.loading = true;");
		controller.append("$").append(factoryName)
				.append(".find($scope.page.number, $scope.page.size,$scope.criterias,[");

		controller.append(subResourceToLoad(subEntityFields));

		controller.append("]).then(function(result) {");
		controller.append("$scope.entities = result.entities;");
		controller.append("$rootScope.page = result.page;");
		controller.append("$rootScope.loading = false;");
		controller.append("}, function(msg) {");
		controller.append("alert(msg);");
		controller.append("$rootScope.loading = false;");
		controller.append("});");
		controller.append("}");

		// supressCriteria function in scope
		controller.append("$scope.supressCriteria = function(index) {");
		controller.append("$scope.criterias.splice(index, 1);");
		controller.append("$scope.pageChanged();");
		controller.append("}");

		// addCriteria function in scope

		controller.append("$scope.addCriteria = function() {");
		controller.append("if($scope.selectedField.type == 'number') {");
		controller.append(
				"$scope.criterias.push({field : $scope.selectedField , operation : $scope.selectedOperation, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});");
		controller.append("} else if($scope.selectedField.type == 'text'){");
		controller.append(
				"$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'contains'}, value:{ id: $scope.selectedValue , value:$scope.selectedValue }});");
		controller.append("} else if($scope.selectedField.type == 'entity'){");
		controller.append(
				"$scope.criterias.push({field : $scope.selectedField , operation : {id:':', value:'is'}, value:$scope.selectedEntity });");
		controller.append("}");
		controller.append("$scope.selectedField= {};");
		controller.append("$scope.selectedOperation= {};");
		controller.append("$scope.selectedValue = '';");
		controller.append("$scope.pageChanged();");
		controller.append("}");

		// call to pageChanged();"
		controller.append("$scope.pageChanged();");

		// end of JS function
		controller.append("};");

		return controller;
	}

	private Object getForSingle(AnalizedEntity entity) {
		StringBuilder controller = new StringBuilder();

		List<Field> subEntityFields = getSubResource(entity);

		String entityName = entity.getSimpleName();

		String controllerName = entityName + "Ctrl";
		String factoryName = entityName + "Factory";

		controller.append("angularApp.controller('");
		controller.append(controllerName);
		controller.append("',['$scope','$rootScope','");
		controller.append(factoryName);
		controller.append("','$routeParams',");
		controller.append(controllerName);
		controller.append("]);");

		controller.append("function ");
		controller.append(controllerName);
		controller.append("($scope,$rootScope,$");
		controller.append(factoryName);
		controller.append(",$routeparams) {");

		controller.append("$rootScope.loading = true;");
		controller.append("$").append(factoryName);
		controller.append(".get($routeparams.id,[");

		controller.append(this.subResourceToLoad(subEntityFields));

		controller.append("]).then(function(entity) {");
		controller.append("$scope.data=entity;");
		controller.append("$rootScope.loading = false;");
		controller.append("}, function(msg) {");
		controller.append("alert(msg);");
		controller.append("$rootScope.loading = false;");
		controller.append("});");

		controller.append("};");

		return controller;
	}

	/**
	 * Gets the entities name.
	 *
	 * @param entityName
	 *            the entity name
	 * @return the entities name
	 */
	private String getEntitiesName(String entityName) {
		String entitiesName;
		if (StringUtils.endsWith(entityName, "y")) {
			entitiesName = StringUtils.removeEnd(entityName, "y") + "ies";
		} else {
			entitiesName = entityName + "s";
		}
		return entitiesName;
	}

	/**
	 * Gets the sub resource.
	 *
	 * @param entity
	 *            the entity
	 * @return the sub resource
	 */
	private List<Field> getSubResource(AnalizedEntity entity) {
		List<Field> subEntityFields = new ArrayList<>();
		for (Field field : entity.getFields()) {
			if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null
					|| field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
				subEntityFields.add(field);
			}
		}
		return subEntityFields;
	}

	/**
	 * Sub resource to load.
	 *
	 * @param subEntityFields
	 *            the sub entity fields
	 * @return the char sequence
	 */
	private CharSequence subResourceToLoad(List<Field> subEntityFields) {

		return this.subResourceToLoad(subEntityFields, "'", "'");
	}

	private CharSequence subResourceToLoad(List<Field> subEntityFields, String before, String after) {
		StringBuilder controller = new StringBuilder();
		boolean first;
		first = true;
		for (Field field : subEntityFields) {
			if (!first) {
				controller.append(",");
			} else {
				first = false;
			}
			controller.append(before).append(field.getSimpleName()).append(after);
		}
		return controller;
	}

}
