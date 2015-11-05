package fr.hopelessworld.plugin.strategy.impl;

import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularTemplateStrategyTest extends AbstractTestStrategy {

	@Override
	protected AbstractUniqueFileGeneratorStrategy getStrategy() {
		return new AngularTemplateStrategy();
	}

	@Override
	protected String getExpectedNoEntity() {
		// @formatter:off
		String expected = "var angularTemplate = {" 
		+ AngularTemplateStrategy.PAGING_TEMPLATE 
		+ ","
		+ AngularTemplateStrategy.FILTERING_TEMPLATE 
		+ "};";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntity() {
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ AngularTemplateStrategy.PAGING_TEMPLATE 
				+ ","
				+ AngularTemplateStrategy.FILTERING_TEMPLATE 
				+ ","
				
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "<!-- id not show -->"
				+ "{{\\'entity.Player.name\\' | translate }}:{{data.name}}<br />"
				+ "<a href=\"#/players\">Players</a>"
				+ "'"
				
				+ ","
				
				+ "PlayerEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"PlayerList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/players/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				+ "};";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith2Field() {
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ AngularTemplateStrategy.PAGING_TEMPLATE 
				+ ","
				+ AngularTemplateStrategy.FILTERING_TEMPLATE 
				+ ","
				
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "<!-- id not show -->"
				+ "{{\\'entity.Player.name\\' | translate }}:{{data.name}}<br />"
				+ "{{\\'entity.Player.creation\\' | translate }}:{{data.creation | date:\\'medium\\'}}<br />"
				+ "<a href=\"#/players\">Players</a>"
				+ "'"
				+ ","
				
				+ "PlayerEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'creation\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">creation</span>"
				+ "<input type=\"datetime-local\" placeholder=\"creation\" ng-model=\"data.creation\" class=\"form-control\">"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"PlayerList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/players/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				+ "};";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWith1EntityField() {
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ AngularTemplateStrategy.PAGING_TEMPLATE 
				+ ","
				+ AngularTemplateStrategy.FILTERING_TEMPLATE 
				+ ","
				
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "<!-- id not show -->"
				+ "{{\\'entity.Player.name\\' | translate }}:{{data.name}}<br />"
				+ "{{\\'entity.Player.team\\' | translate }}:<a href=\"#/teams/{{data.team.id}}\">{{data.team.name}}</a><br />"
				+ "<a href=\"#/players\">Players</a>"
				+ "'"
				+ ","
				+ "PlayerEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'team\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">team</span>"
				+ "<!-- TODO -->"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"PlayerList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/players/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				
				+ "," //une autre entité
				
				+ "Team:'<h1>{{data.name}}</h1>"
				+ "<!-- id not show -->"
				+ "{{\\'entity.Team.name\\' | translate }}:{{data.name}}<br />"
				+ "<a href=\"#/teams\">Teams</a>"
				+ "'"
				
				+ "," 
				
				+ "TeamEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"TeamList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/teams/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				+ "};";
		// @formatter:on
		return expected;
	}

	@Override
	protected String getExpectedOneEntityWithOneToManyField() {
		// @formatter:off
		String expected = "var angularTemplate = {" 
				
				+ AngularTemplateStrategy.PAGING_TEMPLATE 
				+ ","
				+ AngularTemplateStrategy.FILTERING_TEMPLATE 
				+ ","
				
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "<!-- id not show -->"
				+ "{{\\'entity.Player.name\\' | translate }}:{{data.name}}<br />"
				+ "{{\\'entity.Player.team\\' | translate }}:<a href=\"#/teams/{{data.team.id}}\">{{data.team.name}}</a><br />"
				+ "<a href=\"#/players\">Players</a>"
				+ "'"
				
				+ ","
				+ "PlayerEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'team\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">team</span>"
				+ "<!-- TODO -->"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"PlayerList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/players/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				
				+ "," //une autre entité
				+ "Team:'<h1>{{data.name}}</h1>"
				+ "<!-- id not show -->"
				+ "{{\\'entity.Team.name\\' | translate }}:{{data.name}}<br />"
				+ "{{\\'entity.Team.players\\' | translate }}:<ul><li ng-repeat=\"entity in data.players\">"
				+ "<a href=\"#/players/{{entity.id}}\">{{entity.name}}</a>"
				+ "</li></ul><br />"
				+ "<a href=\"#/teams\">Teams</a>"
				+ "'"
				
				+ ","
				
				+ "TeamEdit:'" 
				+ "<h1>{{data.name}}</h1>" 
				+ "<form action=\"#\" ng-submit=\"save()\">"
				+ "<!-- id not show -->"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'name\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">name</span>"
				+ "<input type=\"text\" placeholder=\"name\" ng-model=\"data.name\" class=\"form-control\">"
				+ "</div>"
				+ "<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'players\\') != -1}\" class=\"input-group\">"
				+ "<span class=\"input-group-addon\">players</span>"
				+ "<!-- TODO -->"
				+ "</div>"
				+ "<input type=\"submit\">"
				+ "<button ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>"
				+ "<button ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>"
				+ "</form>'"
				
				+ ","
				
				+"TeamList:'"
				+"<div ng-hide=\"loading\">"
				+"<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>"
				+"<ng-filtering sub-resources=\"subResources\" "
				+"criteria-field=\"criteriaField\" criterias =\"criterias\" "
				+"criteria-changed=\"pageChanged()\" ></ng-filtering>"
				+"<div ng-repeat=\"entity in entities\">"
				+"<a href=\"#/teams/{{entity.id}}\">"
				+"{{entity.name}}"
				+"</a>"
				+"<hr />"
				+"</div>"
				+"</div>'"
				
				+ "};";
		// @formatter:on
		return expected;
	}

}
