package fr.hopelessworld.plugin.strategy.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;
import fr.hopelessworld.plugin.utils.AnalizedEntityUtils;

public class AngularFactoryStrategy extends AbstractUniqueFileGeneratorStrategy {

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder factories = new StringBuilder();

		for (AnalizedEntity entity : entities) {

			String entityName = entity.getSimpleName();

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

		String entityName = entity.getSimpleName();
		String entitiesName = AnalizedEntityUtils.getEntitiesName(entityName).toLowerCase();

		method.append("find:function(page, limit, criterias,subToLoad){");

		method.append("var deferred = $q.defer(),search='', url;");
		method.append("for (var i = 0, l = criterias.length; i < l; i++) {");
		method.append("search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','");
		method.append("}");

		method.append("url = 'rest/").append(entitiesName)
				.append("/search/findByCriteria?page='+ (page -1) + '&size=' +limit;");
		method.append("if(criterias.length > 0 ) {");
		method.append("url += '&search=' + search;");
		method.append("}");

		method.append("var httpPromise = $http.get(url);");
		method.append("SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {");

		method.append(
				"var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];");
		method.append("processedResponse.page.number++;");

		/** check if entite have one To many or many to one field */

		CharSequence ifs = getSubRessourceForGetAndFind(entity);

		if (StringUtils.isNotBlank(ifs)) {
			method.append("for (var i = 0, l=entities.length; i < l; i++) {");
			method.append("var entity=entities[i];");
			method.append(ifs);
			method.append("}");
		}

		method.append("deferred.resolve({");
		method.append("entities :entities,");
		method.append("page : processedResponse.page");
		method.append("});");

		method.append("});");

		method.append("return deferred.promise;}");

		return method;
	}

	private CharSequence getGetForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		String entityName = entity.getSimpleName();
		String entitiesName = AnalizedEntityUtils.getEntitiesName(entityName).toLowerCase();

		method.append("get: function(id,subToLoad){");

		method.append("var deferred = $q.defer();");
		method.append("var httpPromise = $http.get('rest/").append(entitiesName).append("/' +id);");
		method.append("SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {");
		method.append("var entity = processedResponse;");
		/** check if entite have one To many or many to one field */
		CharSequence ifs = getSubRessourceForGetAndFind(entity);

		if (StringUtils.isNotBlank(ifs)) {
			method.append(ifs);
		}

		method.append("deferred.resolve(entity)");
		method.append("},function(response, status) {");
		method.append("var responseText = response != undefined ? response.statusText : 'no response';");
		method.append("deferred.reject('error on loading' + responseText);");
		method.append("});");
		method.append("return deferred.promise;");

		method.append("}");

		return method;
	}

	private CharSequence getAddForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("add: function(data,subToLoad){");
		method.append("}");

		return method;
	}

	private CharSequence getSaveForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		String entityName = entity.getSimpleName();
		String entitiesName = AnalizedEntityUtils.getEntitiesName(entityName).toLowerCase();
		CharSequence ifs;

		method.append("save: function(data,subToLoad){");

		method.append("var deferred = $q.defer();");

		ifs = getSubRessourceForSaveAndAdd(entity);

		if (StringUtils.isNotBlank(ifs)) {
			method.append(ifs);
		}

		method.append("var dataJson = angular.toJson(data);");
		method.append("var httpPromise = $http.put('rest/").append(entitiesName).append("/' + data.id, dataJson);");
		method.append("SpringDataRestAdapter.process(httpPromise, subToLoad).then(function(entity) {");

		/** check if entite have one To many or many to one field */
		ifs = getSubRessourceForGetAndFind(entity);

		if (StringUtils.isNotBlank(ifs)) {
			method.append(ifs);
		}

		method.append("deferred.resolve(entity);");
		method.append("}, function (response) {");
		method.append("var result;");
		method.append(
				"if(response != undefined &&  response.data != undefined && response.data.errors != undefined) {");
		method.append("result = response.data.errors;");
		method.append("} else  {");
		method.append("var responseText = response != undefined ? response.statusText : 'no response';");
		method.append("result = [{property: 'Cannot save ' , message:  responseText }];");
		method.append("}");
		method.append("deferred.reject(result);");
		method.append("});");
		method.append("return deferred.promise;");

		method.append("}");

		return method;
	}

	private CharSequence getDeleteForEntity(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();

		method.append("delete: function(data){");
		method.append("}");

		return method;
	}

	/**
	 * Gets the sub ressource for get and find.
	 *
	 * @param entity
	 *            the entity
	 * @return the sub ressource for get and find
	 */
	private StringBuilder getSubRessourceForGetAndFind(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();
		List<Field> subEntityFields = new ArrayList<>();
		for (Field field : entity.getFields()) {
			if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null
					|| field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
				subEntityFields.add(field);
			}
		}

		for (Field field : subEntityFields) {
			CharSequence fieldname = field.getSimpleName();
			method.append("if(entity.").append(fieldname).append(" != undefined && entity.").append(fieldname)
					.append("._embeddedItems != undefined) {");
			method.append("entity.").append(fieldname).append(" = entity.").append(fieldname)
					.append("._embeddedItems ;");
			method.append("}");
		}
		return method;
	}

	private CharSequence getSubRessourceForSaveAndAdd(AnalizedEntity entity) {
		StringBuilder method = new StringBuilder();
		List<Field> subEntityFields = new ArrayList<>();
		List<Field> subEntitiesFields = new ArrayList<>();
		for (Field field : entity.getFields()) {
			if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null) {
				subEntityFields.add(field);
			}
			if (field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
				subEntitiesFields.add(field);
			}
		}

		for (Field field : subEntityFields) {
			CharSequence fieldname = field.getSimpleName();
			method.append("if(data.").append(fieldname).append(" != undefined && data.").append(fieldname)
					.append("._links != undefined ) {");
			method.append("data.").append(fieldname).append(" = data.").append(fieldname).append("._links.self.href;");
			method.append("}");
		}

		for (Field field : subEntitiesFields) {
			CharSequence fieldname = field.getSimpleName();

			method.append("if(data.").append(fieldname).append(" != undefined && data.").append(fieldname)
					.append(".length >0  ) {");
			method.append("for(var i=0, l = data.").append(fieldname).append(".length; i < l; i++) {");
			method.append("if(data.").append(fieldname).append("[i] != undefined && data.").append(fieldname)
					.append("[i]._links != undefined) {");
			method.append("data.").append(fieldname).append("[i] = data.").append(fieldname)
					.append("[i]._links.self.href;");
			method.append("}");
			method.append("}");
			method.append("}");

		}

		return method;
	}

}
