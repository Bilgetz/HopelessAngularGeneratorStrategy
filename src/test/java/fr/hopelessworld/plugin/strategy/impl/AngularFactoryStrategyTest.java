package fr.hopelessworld.plugin.strategy.impl;

import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularFactoryStrategyTest extends AbstractTestStrategy {

	@Override
	protected AbstractUniqueFileGeneratorStrategy getStrategy() {
		return new AngularFactoryStrategy();
	}

	@Override
	protected String getExpectedOneEntity() {
		// @formatter:off
		String expected = "angularApp.factory('PlayerFactory',['$http','$q','SpringDataRestAdapter',PlayerFactory]);"
				+ "function PlayerFactory($http,$q,SpringDataRestAdapter) {" 
				+ "return {"
				
				+ "find:function(page, limit, criterias,subToLoad){"
				
				+ "var deferred = $q.defer(),search='', url;"
				+ "for (var i = 0, l = criterias.length; i < l; i++) {"
				+ "search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','" 
				+ "}"
				
				+ "url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				+ "}"
				+ ","
				
				+ "add: function(data){"
				+ "}"
				+ ","
				
				+ "save: function(data){"
				+ "}"
				+ ","
				
				+ "delete: function(data){"
				+ "}"
				
				+ "};"
				+ "};";
		// @formatter:on
		return expected;

	}

	@Override
	protected String getExpectedOneEntityWith2Field() {
		return getExpectedOneEntity();
	}

	@Override
	protected String getExpectedOneEntityWith1EntityField() {
		// @formatter:off
		String expected = "angularApp.factory('PlayerFactory',['$http','$q','SpringDataRestAdapter',PlayerFactory]);"
				+ "function PlayerFactory($http,$q,SpringDataRestAdapter) {" 
				+ "return {"
				
				+ "find:function(page, limit, criterias,subToLoad){"
				
				+ "var deferred = $q.defer(),search='', url;"
				+ "for (var i = 0, l = criterias.length; i < l; i++) {"
				+ "search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','" 
				+ "}"
				
				+ "url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				+ "for (var i = 0, l=entities.length; i < l; i++) {"
				
				+ "if(entities[i].team != undefined && entities[i].team._embeddedItems != undefined) {"
				+ "entities[i].team = entities[i].team._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				+ "}"
				+ ","
				
				+ "add: function(data){"
				+ "}"
				+ ","
				
				+ "save: function(data){"
				+ "}"
				+ ","
				
				+ "delete: function(data){"
				+ "}"
				
				+ "};"
				+ "};"
				
				//pour team
				+"angularApp.factory('TeamFactory',['$http','$q','SpringDataRestAdapter',TeamFactory]);"
				+ "function TeamFactory($http,$q,SpringDataRestAdapter) {" 
				+ "return {"
				
				+ "find:function(page, limit, criterias,subToLoad){"
				
				+ "var deferred = $q.defer(),search='', url;"
				+ "for (var i = 0, l = criterias.length; i < l; i++) {"
				+ "search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','" 
				+ "}"
				
				+ "url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				+ "}"
				+ ","
				
				+ "add: function(data){"
				+ "}"
				+ ","
				
				+ "save: function(data){"
				+ "}"
				+ ","
				
				+ "delete: function(data){"
				+ "}"
				
				+ "};"
				+ "};"
				+ "";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWithOneToManyField() {
		// @formatter:off
		String expected = "angularApp.factory('PlayerFactory',['$http','$q','SpringDataRestAdapter',PlayerFactory]);"
				+ "function PlayerFactory($http,$q,SpringDataRestAdapter) {" 
				+ "return {"
				
				+ "find:function(page, limit, criterias,subToLoad){"
				
				+ "var deferred = $q.defer(),search='', url;"
				+ "for (var i = 0, l = criterias.length; i < l; i++) {"
				+ "search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','" 
				+ "}"
				
				+ "url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				+ "for (var i = 0, l=entities.length; i < l; i++) {"
				
				+ "if(entities[i].team != undefined && entities[i].team._embeddedItems != undefined) {"
				+ "entities[i].team = entities[i].team._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				+ "}"
				+ ","
				
				+ "add: function(data){"
				+ "}"
				+ ","
				
				+ "save: function(data){"
				+ "}"
				+ ","
				
				+ "delete: function(data){"
				+ "}"
				
				+ "};"
				+ "};"
				
				//pour team
				+"angularApp.factory('TeamFactory',['$http','$q','SpringDataRestAdapter',TeamFactory]);"
				+ "function TeamFactory($http,$q,SpringDataRestAdapter) {" 
				+ "return {"
				
				+ "find:function(page, limit, criterias,subToLoad){"
				
				+ "var deferred = $q.defer(),search='', url;"
				+ "for (var i = 0, l = criterias.length; i < l; i++) {"
				+ "search+= criterias[i].field.id + criterias[i].operation.id + criterias[i].value.id + ','" 
				+ "}"
				
				+ "url = 'rest/players/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				
				+ "for (var i = 0, l=entities.length; i < l; i++) {"
				
				+ "if(entities[i].players != undefined && entities[i].players._embeddedItems != undefined) {"
				+ "entities[i].players = entities[i].players._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				+ "}"
				+ ","
				
				+ "add: function(data){"
				+ "}"
				+ ","
				
				+ "save: function(data){"
				+ "}"
				+ ","
				
				+ "delete: function(data){"
				+ "}"
				
				+ "};"
				+ "};"
				+ "";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedNoEntity() {
		return "";
	}

}
