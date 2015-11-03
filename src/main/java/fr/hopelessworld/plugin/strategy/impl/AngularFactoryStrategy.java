package fr.hopelessworld.plugin.strategy.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularFactoryStrategy extends AbstractUniqueFileGeneratorStrategy {

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder factories = new StringBuilder();

		for (AnalizedEntity entity : entities) {

			String entityName = entity.getSimpleName();
			String entitiesName;
			if (StringUtils.endsWith(entityName, "y")) {
				entitiesName = StringUtils.removeEnd(entityName, "y") + "ies";
			} else {
				entitiesName = entityName + "s";
			}

			factories.append("angularApp.factory('").append(entityName)
					.append("Factory',['$http','$q','SpringDataRestAdapter',").append(entityName).append("Factory]);");
			factories.append("function ").append(entityName).append("Factory($http,$q,SpringDataRestAdapter) {");
			factories.append("return {");

			factories.append(this.getFindForEntity(entity));
			factories.append(",");
			factories.append(this.getGetForEntity(entity));
			factories.append(",");
			factories.append(this.getAddForEntity(entity));
			factories.append(",");
			factories.append(this.getSaveForEntity(entity));
			factories.append(",");
			factories.append(this.getDeleteForEntity(entity));

			factories.append("};");
			factories.append("};");

		}

		return factories;
	}

	private CharSequence getFindForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("find:function(page, limit, criterias,subToLoad){");

		method.append("var deferred = $q.defer(),search='', url;");
		method.append("for (var i = 0, l = criterias.length; i < l; i++) {");
		method.append("search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','");
		method.append("}");

		method.append("url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;");
		method.append("if(criterias.length > 0 ) {");
		method.append("url += '&search=' + search;");
		method.append("}");

		method.append("var httpPromise = $http.get(url);");
		method.append("SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {");

		method.append(
				"var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];");
		method.append("processedResponse.page.number++;");

		/** check if entite have one To many or many to one field */
		List<Field> subEntityFields = new ArrayList<>();
		for (Field field : entity.getFields()) {
			if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null
					|| field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
				subEntityFields.add(field);
			}
		}
		if (CollectionUtils.isNotEmpty(subEntityFields)) {
			method.append("for (var i = 0, l=entities.length; i < l; i++) {");
			for (Field field : subEntityFields) {
				CharSequence fieldname = field.getSimpleName();
				method.append("if(entities[i].").append(fieldname).append(" != undefined && entities[i].")
						.append(fieldname).append("._embeddedItems != undefined) {");
				method.append("entities[i].").append(fieldname).append(" = entities[i].").append(fieldname)
						.append("._embeddedItems ;");
				method.append("}");
			}

			method.append("}");
		}

		method.append("deferred.resolve({");
		method.append("entities :processedResponse._embeddedItems,");
		method.append("page : processedResponse.page");
		method.append("});");

		method.append("}");

		return method;
	}

	private CharSequence getGetForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("get: function(id){");
		method.append("}");

		return method;
	}

	private CharSequence getAddForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("add: function(data){");
		method.append("}");

		return method;
	}

	private CharSequence getSaveForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("save: function(data){");
		method.append("}");

		return method;
	}

	private CharSequence getDeleteForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("delete: function(data){");
		method.append("}");

		return method;
	}

}
