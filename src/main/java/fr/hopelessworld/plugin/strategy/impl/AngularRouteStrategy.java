package fr.hopelessworld.plugin.strategy.impl;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;
import fr.hopelessworld.plugin.utils.AnalizedEntityUtils;

public class AngularRouteStrategy extends AbstractUniqueFileGeneratorStrategy {

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder routes = new StringBuilder();
		routes.append("function generateRoute($routeProvider){");

		if (CollectionUtils.isNotEmpty(entities)) {
			routes.append("$routeProvider");
			for (AnalizedEntity entity : entities) {
				String entityName = entity.getSimpleName();
				String entitiesName = AnalizedEntityUtils.getEntitiesName(entityName);

				routes.append(".when('/").append(entitiesName.toLowerCase()).append("',{template: angularTemplate.")
						.append(entityName).append("List,controller: '").append(entitiesName).append("Ctrl'})");
				routes.append(".when('/").append(entitiesName.toLowerCase()).append("/:id',{template: angularTemplate.")
						.append(entityName).append(",controller: '").append(entityName).append("Ctrl'})");

			}
			routes.append(";");
		}

		routes.append("};");
		return routes;
	}

}
