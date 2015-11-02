package fr.hopelessworld.plugin.strategy.impl;

import java.util.Collection;
import java.util.Date;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
import fr.hopelessworld.plugin.predicate.IdFieldPredicate;
import fr.hopelessworld.plugin.predicate.SimpleNameFieldPredicate;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public class AngularTemplateStrategy extends AbstractUniqueFileGeneratorStrategy {

	private final Predicate<Field> nameSimpleNamePredicate = new SimpleNameFieldPredicate("name");

	/** The title simple name predicate. */
	private final Predicate<Field> titleSimpleNamePredicate = new SimpleNameFieldPredicate("title");

	/** The id field predicate. */
	private final Predicate<Field> idFieldPredicate = new IdFieldPredicate();

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder templates = new StringBuilder();
		templates.append("var angularTemplate = {");
		boolean first = true;

		for (AnalizedEntity entity : entities) {
			if (!first) {
				templates.append(",");
			} else {
				first = false;
			}
			templates.append(this.getShowForEntity(entity));
			templates.append(",");
			templates.append(this.getEditForEntity(entity));

		}
		templates.append("};");
		return templates;
	}

	/**
	 * Gets the show Template for entity.
	 *
	 * @param entity
	 *            the entity
	 * @return the show for entity
	 */
	private CharSequence getShowForEntity(AnalizedEntity entity) {
		StringBuilder output = new StringBuilder();

		String entityName = entity.getSimpleName();

		output.append(entityName).append(":'");

		output.append("<h1>{{").append(this.getShowName("data.", entity)).append("}}</h1>");

		for (Field field : entity.getFields()) {

			if (field.getAnnotation(Id.class) != null) {
				output.append("{{!-- id not show --}}");
			} else {
				output.append("{{'entity.").append(entityName).append(".").append(field.getSimpleName())
						.append("' | translate }}:");
				if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null) {
					/** c'est 1 entité */
					AnalizedEntity fieldEntity = field.asAnalyzedEntity();
					String nameOfFieldEntity = fieldEntity.getSimpleName().toLowerCase();
					// le a
					output.append("<a href=");
					// url du href
					output.append("\"").append(this.getUrlForEntity("data." + field.getSimpleName(), fieldEntity))
							.append("\"");
					// affichage du nom du field
					output.append(">{{data.").append(nameOfFieldEntity).append(this.getShowName(".", fieldEntity));
					output.append("}}</a>");
				} else if (field.getAnnotation(OneToMany.class) != null
						|| field.getAnnotation(ManyToMany.class) != null) {
					/* une liste */
					output.append("<ul>");
					output.append("<li ng-repeat=\"entity in data.").append(field.getSimpleName()).append("\">");
					if (field.asAnalyzedEntity() != null) {
						AnalizedEntity fieldEntity = field.asAnalyzedEntity();
						output.append("<a href=\"").append(this.getUrlForEntity("entity", fieldEntity)).append("\">{{");
						output.append(this.getShowName("entity.", fieldEntity));
						output.append("}}</a>");
					} else {
						output.append("{{entity.").append(field.getSimpleName()).append("}}");
					}
					output.append("</li>");
					output.append("</ul>");

				} else if (field.getAnnotation(Column.class) != null) {
					/* un champ basic */
					output.append("{{data.").append(field.getSimpleName());
					if (Date.class.getCanonicalName().equals(field.asType().toString())) {
						output.append(" | date:'medium'");
					}
					output.append("}}");
				}
				output.append("<br />");
			}
		}

		output.append("'");

		return output;
	}

	private CharSequence getEditForEntity(AnalizedEntity entity) {
		StringBuilder output = new StringBuilder();

		String entityName = entity.getSimpleName();

		output.append(entityName).append("Edit:'");
		output.append("<h1>{{").append(this.getShowName("data.", entity)).append("}}</h1>");

		output.append("<form action=\"#\" ng-submit=\"save()\">");

		for (Field field : entity.getFields()) {

			if (field.getAnnotation(Id.class) != null) {
				output.append("{{!-- id not show --}}");
			} else {
				CharSequence fieldName = field.getSimpleName();

				output.append("<div ng-class=\"{'has-error': error != undefined && error.indexOf('").append(fieldName)
						.append("') != -1}\" class=\"input-group\">");

				output.append("<span class=\"input-group-addon\">").append(fieldName).append("</span>");
				// <input type="text" placeholder="name" ng-model="data.name"
				// class="form-control">

				if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null) {
					/** c'est 1 entité */
					output.append("{{!-- TODO --}}");
				} else if (field.getAnnotation(OneToMany.class) != null
						|| field.getAnnotation(ManyToMany.class) != null) {
					/* une liste */
					output.append("{{!-- TODO --}}");
				} else if (field.getAnnotation(Column.class) != null) {
					/* un champ basic */
					TypeMirror typeMirror = field.asType();

					if (String.class.getCanonicalName().equals(typeMirror.toString())) {
						// type String
						output.append("<input type=\"text\" placeholder=\"").append(fieldName)
								.append("\" ng-model=\"data.").append(fieldName).append("\" class=\"form-control\">");

					} else if (Date.class.getCanonicalName().equals(typeMirror.toString())) {
						// type date
						output.append("<input type=\"datetime-local\" placeholder=\"").append(fieldName)
								.append("\" ng-model=\"data.").append(fieldName).append("\" class=\"form-control\">");
					} else if (field.asType().getKind() == TypeKind.LONG
							|| Long.class.getCanonicalName().equals(typeMirror.toString())
							|| field.asType().getKind() == TypeKind.INT
							|| Integer.class.getCanonicalName().equals(typeMirror.toString())) {
						// tye number
						output.append("<input type=\"number\" placeholder=\"").append(fieldName)
								.append("\" ng-model=\"data.").append(fieldName).append("\" class=\"form-control\">");
					} else if (field.asType().getKind() == TypeKind.BOOLEAN
							|| Boolean.class.getCanonicalName().equals(typeMirror.toString())) {
						// type boolean
						output.append("<input type=\"checkbox\" placeholder=\"").append(fieldName)
								.append("\" ng-model=\"data.").append(fieldName).append("\" class=\"form-control\">");
					} else {
						// autre type (inconu)
						output.append("{{!-- unknow type --}}");
					}

				}
				output.append("</div>");
			}
		}
		// @formatter:off
		// <form action="#" ng-submit="save()">
		// <div ng-class="{'has-error': error != undefined && error.indexOf('name') != -1} " class="input-group">
		// <span class="input-group-addon">name</span> <input type="text" placeholder="name" ng-model="data.name" class="form-control">
		// </div>
		// <div ng-class="{'has-error': error != undefined && error.indexOf('content') != -1} " class="input-group">
		// <span class="input-group-addon">content</span><textarea placeholder="content" ng-model="data.content" class="form-control"></textarea>
		// </div>
		// <input type="submit">
		// </form>
		// @formatter:on
		output.append("<input type=\"submit\">");
		output.append("</form>");
		output.append("'");
		return output;
	}

	/**
	 * Gets the url for entity.
	 *
	 * @param name
	 *            the name
	 * @param fieldEntity
	 *            the field entity
	 * @return the url for entity
	 */
	private String getUrlForEntity(String name, AnalizedEntity fieldEntity) {
		String nameOfFieldEntity = fieldEntity.getSimpleName().toLowerCase();
		Field idField = CollectionUtils.find(fieldEntity.getFields(), this.idFieldPredicate);
		return StringUtils.join("#/", nameOfFieldEntity, "/{{", name, ".", idField.getSimpleName(), "}}");
	}

	private String getShowName(String name, AnalizedEntity entity) {

		Field fieldName = CollectionUtils.find(entity.getFields(), this.nameSimpleNamePredicate);
		if (fieldName == null) {
			fieldName = CollectionUtils.find(entity.getFields(), this.titleSimpleNamePredicate);
		}
		Field idField = CollectionUtils.find(entity.getFields(), this.idFieldPredicate);
		if (fieldName != null) {
			return StringUtils.join(name, fieldName.getSimpleName());
		} else if (idField != null) {
			return StringUtils.join(name, idField.getSimpleName());
		} else {
			String finalName = null;
			for (Field field : entity.getFields()) {
				if (field.getAnnotation(Column.class) != null && field.getAnnotation(OneToMany.class) != null
						&& field.getAnnotation(OneToOne.class) != null && field.getAnnotation(ManyToOne.class) != null
						&& field.getAnnotation(ManyToMany.class) != null
						&& field.getAnnotation(JoinColumn.class) != null) {
					finalName = StringUtils.join(finalName, name, field.getSimpleName(), " ");
				}
			}
			return finalName;
		}

	}

}
