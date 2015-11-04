package fr.hopelessworld.plugin.utils;

import org.apache.commons.lang3.StringUtils;

public final class AnalizedEntityUtils {

	private AnalizedEntityUtils() {

	}

	/**
	 * Gets the entities name.
	 *
	 * @param entityName
	 *            the entity name
	 * @return the entities name
	 */
	public static String getEntitiesName(String entityName) {
		String entitiesName;
		if (StringUtils.endsWith(entityName, "y")) {
			entitiesName = StringUtils.removeEnd(entityName, "y") + "ies";
		} else {
			entitiesName = entityName + "s";
		}
		return entitiesName;
	}

}
