package fr.hopelessworld.plugin.utils;

import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;

/**
 * The Class AnalizedEntityUtils.
 */
public final class AnalizedEntityUtils {

	/**
	 * Instantiates a new analized entity utils.
	 */
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

	/**
	 * Gets the directive name.
	 *
	 * @param entity
	 *            the entity
	 * @return the directive name
	 */
	public static String getDirectiveName(AnalizedEntity entity) {
		return "ng" + entity.getSimpleName();
	}

	/**
	 * Gets the edits the directive name.
	 *
	 * @param entity
	 *            the entity
	 * @return the edits the directive name
	 */
	public static String getEditDirectiveName(AnalizedEntity entity) {
		return getDirectiveName(entity) + "Edit";
	}

	public static String getEntitiesDirectiveName(AnalizedEntity entity) {
		return "ng" + getEntitiesName(entity.getSimpleName());
	}

	/**
	 * Gets the list directive name.
	 *
	 * @param entity
	 *            the entity
	 * @return the list directive name
	 */
	public static String getListDirectiveName(AnalizedEntity entity) {
		return getDirectiveName(entity) + "List";
	}

	protected static CharSequence toTagName(String directive) {
		if (StringUtils.isEmpty(directive)) {
			return directive;
		}

		StringBuffer tag = new StringBuffer();
		final char[] buffer = directive.toCharArray();

		for (int i = 0; i < buffer.length; i++) {
			final char ch = buffer[i];
			if (Character.isUpperCase(ch) || Character.isTitleCase(ch)) {
				tag.append('-').append(Character.toLowerCase(ch));
			} else if (Character.isLowerCase(ch)) {
				tag.append(ch);
			}
		}

		return tag;
	}

	/**
	 * Gets the edits the tage name.
	 *
	 * @param entity
	 *            the entity
	 * @return the edits the tage name
	 */
	public static CharSequence getEditTagName(AnalizedEntity entity) {
		return toTagName(getEditDirectiveName(entity));
	}

	/**
	 * Gets the entities tag name.
	 *
	 * @param entity
	 *            the entity
	 * @return the entities tag name
	 */
	public static CharSequence getEntitiesTagName(AnalizedEntity entity) {
		return toTagName(getEntitiesDirectiveName(entity));
	}

}
