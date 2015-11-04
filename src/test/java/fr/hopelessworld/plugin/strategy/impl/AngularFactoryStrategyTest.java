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
				//resole end
				+ "});" 
				//function end
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				
				+ "var deferred = $q.defer();"
				+ "var httpPromise = $http.get('rest/players/' +id);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				+ "var entity = processedResponse;"
				+ "deferred.resolve(entity)"
				+ "},function(response, status) {"
				+ "var responseText = response != undefined ? response.statusText : 'no response';"
				+ "deferred.reject('error on loading' + responseText);"
				+ "});"
				+ "return deferred.promise;"
				
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
				+ "var entity=entities[i];"
				
				+ "if(entity.team != undefined && entity.team._embeddedItems != undefined) {"
				+ "entity.team = entity.team._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				//resole end
				+ "});" 
				//function end
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				
				+ "var deferred = $q.defer();"
				+ "var httpPromise = $http.get('rest/players/' +id);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				+ "var entity = processedResponse;"
				
				+ "if(entity.team != undefined && entity.team._embeddedItems != undefined) {"
				+ "entity.team = entity.team._embeddedItems ;"
				+ "}"
				
				+ "deferred.resolve(entity)"
				+ "},function(response, status) {"
				+ "var responseText = response != undefined ? response.statusText : 'no response';"
				+ "deferred.reject('error on loading' + responseText);"
				+ "});"
				+ "return deferred.promise;"
				
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
				
				+ "url = 'rest/teams/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
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
				//resole end
				+ "});" 
				//function end
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				
				+ "var deferred = $q.defer();"
				+ "var httpPromise = $http.get('rest/teams/' +id);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				+ "var entity = processedResponse;"
				+ "deferred.resolve(entity)"
				+ "},function(response, status) {"
				+ "var responseText = response != undefined ? response.statusText : 'no response';"
				+ "deferred.reject('error on loading' + responseText);"
				+ "});"
				+ "return deferred.promise;"
				
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
				+ "var entity=entities[i];"
				+ "if(entity.team != undefined && entity.team._embeddedItems != undefined) {"
				+ "entity.team = entity.team._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				//resole end
				+ "});" 
				//function end
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				
				+ "var deferred = $q.defer();"
				+ "var httpPromise = $http.get('rest/players/' +id);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				+ "var entity = processedResponse;"
				
				+ "if(entity.team != undefined && entity.team._embeddedItems != undefined) {"
				+ "entity.team = entity.team._embeddedItems ;"
				+ "}"
				
				+ "deferred.resolve(entity)"
				+ "},function(response, status) {"
				+ "var responseText = response != undefined ? response.statusText : 'no response';"
				+ "deferred.reject('error on loading' + responseText);"
				+ "});"
				+ "return deferred.promise;"
				
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
				
				+ "url = 'rest/teams/search/findByCriteria?page='+ (page -1) + '&size=' +limit;"
				+ "if(criterias.length > 0 ) {"
				+ "url += '&search=' + search;"
				+ "}"
							
				+ "var httpPromise = $http.get(url);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				
				+ "var entities = processedResponse._embeddedItems != undefined ? processedResponse._embeddedItems : [];"
				+ "processedResponse.page.number++;"
				
				+ "for (var i = 0, l=entities.length; i < l; i++) {"
				+ "var entity=entities[i];"
				+ "if(entity.players != undefined && entity.players._embeddedItems != undefined) {"
				+ "entity.players = entity.players._embeddedItems ;"
				+ "}"
				
				+ "}"
				
				
				+ "deferred.resolve({"
				+ "entities :processedResponse._embeddedItems,"
				+ "page : processedResponse.page"
				//resole end
				+ "});" 
				//function end
				+ "});"
				
				+ "}"
				+ ","
							
				+ "get: function(id){"
				
				+ "var deferred = $q.defer();"
				+ "var httpPromise = $http.get('rest/teams/' +id);"
				+ "SpringDataRestAdapter.process(httpPromise, subToLoad).then(function (processedResponse) {"
				+ "var entity = processedResponse;"
				
				+ "if(entity.players != undefined && entity.players._embeddedItems != undefined) {"
				+ "entity.players = entity.players._embeddedItems ;"
				+ "}"
				
				+ "deferred.resolve(entity)"
				+ "},function(response, status) {"
				+ "var responseText = response != undefined ? response.statusText : 'no response';"
				+ "deferred.reject('error on loading' + responseText);"
				+ "});"
				+ "return deferred.promise;"
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
