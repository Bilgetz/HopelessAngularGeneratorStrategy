package fr.hopelessworld.plugin.strategy.impl;

import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularRouteStrategyTest extends AbstractTestStrategy {

	@Override
	protected AbstractUniqueFileGeneratorStrategy getStrategy() {
		// TODO Auto-generated method stub
		return new AngularRouteStrategy();
	}

	@Override
	protected String getExpectedNoEntity() {
		return "function generateRoute($routeProvider){};";
	}

	@Override
	protected String getExpectedOneEntity() {
		// @formatter:off
		String expected =""
			+ "function generateRoute($routeProvider){"
							
			+ "$routeProvider"
			+ ".when('/players',{template: angularTemplate.PlayerList,controller: 'PlayersCtrl'})"
			+ ".when('/players/:id',{template: angularTemplate.Player,controller: 'PlayerCtrl'});"
				
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
		String expected =""
			+ "function generateRoute($routeProvider){"
							
			+ "$routeProvider"
			+ ".when('/players',{template: angularTemplate.PlayerList,controller: 'PlayersCtrl'})"
			+ ".when('/players/:id',{template: angularTemplate.Player,controller: 'PlayerCtrl'})"
			+ ".when('/teams',{template: angularTemplate.TeamList,controller: 'TeamsCtrl'})"
			+ ".when('/teams/:id',{template: angularTemplate.Team,controller: 'TeamCtrl'});"
				
			+ "};";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWithOneToManyField() {
		return getExpectedOneEntityWith1EntityField();
	}

}
