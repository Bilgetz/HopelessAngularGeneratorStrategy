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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngTeams', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.TeamList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngPlayers', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.PlayerList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
				+"}"
				+"}"
				+"});"
				
				+"angularApp.directive('ngTeams', function() {"
				+"return {"
				+"restrict : 'E',"
				+"template : angularTemplate.TeamList,"
				+"scope : {"
				+"entities :'=',"
				+"pageChanged : '&',"
				+"subResources: '=',"
				+"criterias: '=',"
				+"criteriaField: '='"
				+"}"
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
