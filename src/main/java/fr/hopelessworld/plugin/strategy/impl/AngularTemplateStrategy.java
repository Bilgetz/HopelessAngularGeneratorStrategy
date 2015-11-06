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
import fr.hopelessworld.plugin.utils.AnalizedEntityUtils;

public final class AngularTemplateStrategy extends AbstractUniqueFileGeneratorStrategy {

	protected final static String PAGING_TEMPLATE = "PagingTemplate:'<div class=\"row\"><div class=\"col-md-5\">"
			+ "<uib-pagination total-items=\"page.totalElements\" max-size=\"5\""
			+ " class=\"pagination-sm\" items-per-page=\"page.size\""
			+ " boundary-links=\"true\" rotate=\"false\" ng-change=\"pageChanged()\""
			+ " ng-model=\"page.number\"></uib-pagination></div>" + "<div class=\"col-md-1\"><label>Size:</label>"
			+ "<input type=\"number\" class=\"form-control\" ng-model=\"page.size\" ng-change=\"pageChanged()\">"
			+ "</div></div>'";

	protected final static String FILTERING_TEMPLATE = "FiletringTemplate:'<div><form ng-submit=\"addCriteria()\">"
			+ "<select name=\"selectedField\" id=\"selectedField\" ng-model=\"selectedField\""
			+ " ng-options=\"option.value for option in criteriaField\"></select>"
			+ "<select ng-show=\"selectedField.type != undefined && selectedField.type.indexOf(\\'number\\') != -1\""
			+ " name=\"selectedOperation\" id=\"selectedOperation\" ng-model=\"selectedOperation\""
			+ " ng-options=\"option.value for option in criteriaOperation\"></select>"
			+ "<span ng-show=\"selectedField.type != undefined && selectedField.type.indexOf(\\'text\\') != -1\">contains</span>"
			+ "<span ng-show=\"selectedField.type != undefined && selectedField.type.indexOf(\\'entity\\') != -1\">is</span>"
			+ "<input ng-show=\"selectedField.type != undefined && selectedField.type.indexOf(\\'entity\\') == -1\""
			+ " type=\"text\" ng-model=\"selectedValue\">"
			+ "<select ng-repeat=\"(resource, list) in subResources track by list\""
			+ " ng-show=\"selectedField.type != undefined && selectedField.id==\\'{{resource}}\\'\""
			+ " ng-options=\"option.name for option in list\" name=\"selectedEntity\" id=\"selected{{resource}}\""
			+ " ng-model=\"$parent.selectedEntity\"></select>"
			+ "<input ng-show=\"selectedField.type != undefined\" type=\"submit\" value=\"add\"></form>"
			+ "<ul class=\"list-group row\">"
			+ "<li ng-repeat=\"criteria in criterias\" class=\"list-group-item col-md-4\">" + "{{criteria.field.value}}"
			+ "{{criteria.operation.value}}"
			+ "{{criteria.value.value != undefined ? criteria.value.value : criteria.value.name != undefined ? criteria.value.name : \\'\\'}}"
			+ "<button type = \"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\" ng-click=\"supressCriteria($index)\">"
			+ "<span aria-hidden=\"true\">&times;</span></button></li></ul></div>'";

	private final Predicate<Field> nameSimpleNamePredicate = new SimpleNameFieldPredicate("name");

	/** The title simple name predicate. */
	private final Predicate<Field> titleSimpleNamePredicate = new SimpleNameFieldPredicate("title");

	/** The id field predicate. */
	private final Predicate<Field> idFieldPredicate = new IdFieldPredicate();

	@Override
	public CharSequence generate(Collection<AnalizedEntity> entities) {
		StringBuilder templates = new StringBuilder();
		templates.append("var angularTemplate = {");

		templates.append(PAGING_TEMPLATE);
		templates.append(",");
		templates.append(FILTERING_TEMPLATE);

		for (AnalizedEntity entity : entities) {
			templates.append(",");
			templates.append(this.getShowForEntity(entity));
			templates.append(",");
			templates.append(this.getEditForEntity(entity));
			templates.append(",");
			templates.append(this.getListForEntity(entity));
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
		output.append("<div ng-hide=\"onedit\">");

		for (Field field : entity.getFields()) {

			if (field.getAnnotation(Id.class) != null) {
				output.append("<!-- id not show -->");
			} else {
				output.append("{{\\'entity.").append(entityName).append(".").append(field.getSimpleName())
						.append("\\' | translate }}:");
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
						output.append(" | date:\\'medium\\'");
					}
					output.append("}}");
				}
				output.append("<br />");
			}
		}

		output.append("<button type=\"button\" ng-click=\"edit()\">{{\\'angular.edit\\' | translate }}</button>");
		output.append("<a href=\"");
		output.append(this.getUrlForEntities(entity));
		output.append("\">");
		output.append(AnalizedEntityUtils.getEntitiesName(entityName));
		output.append("</a>");

		output.append("</div>");
		output.append("<div ng-show=\"onedit\">");

		CharSequence angularEditDirective = AnalizedEntityUtils.getEditTagName(entity);

		output.append("<").append(angularEditDirective)
				.append(" data=\"editData\"  save=\"save()\" reset=\"reset()\" cancel=\"cancel()\"></")
				.append(angularEditDirective).append(">");
		output.append("</div>");

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
				output.append("<!-- id not show -->");
			} else {
				CharSequence fieldName = field.getSimpleName();

				output.append("<div ng-class=\"{\\'has-error\\': error != undefined && error.indexOf(\\'")
						.append(fieldName).append("\\') != -1}\" class=\"input-group\">");

				output.append("<span class=\"input-group-addon\">").append(fieldName).append("</span>");
				// <input type="text" placeholder="name" ng-model="data.name"
				// class="form-control">

				if (field.getAnnotation(ManyToOne.class) != null || field.getAnnotation(OneToOne.class) != null) {
					/** c'est 1 entité */

					output.append("<span  class=\"form-control\">{{data.team.name}}</span>");
					output.append("<div class=\"input-group-btn\">");
					output.append(
							"<button type=\"button\" ng-click=\"deleteEntity(\\'team\\')\" class=\"btn btn-default\" aria-label=\"delete\">");
					output.append("<span class=\"glyphicon glyphicon-remove\"></span>&nbsp;");
					output.append("</button>");
					output.append(
							"<button type=\"button\" ng-click=\"changeEntity(\\'team\\')\" class=\"btn btn-default\" aria-label=\"search\">");
					output.append("<span class=\"glyphicon glyphicon-search\"></span>&nbsp;");
					output.append("</button>");

				} else if (field.getAnnotation(OneToMany.class) != null
						|| field.getAnnotation(ManyToMany.class) != null) {
					/* une liste */
					output.append("<!-- TODO -->");
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
						output.append("<!-- unknow type -->");
					}

				}
				output.append("</div>");
			}
		}

		output.append("<input type=\"submit\">");
		output.append(
				"<button type=\"button\" ng-click=\"reset()\">{{\\'angular.edit.reset\\' | translate }}</button>");
		output.append(
				"<button type=\"button\" ng-click=\"cancel()\">{{\\'angular.edit.cancel\\' | translate }}</button>");
		output.append("</form>");
		output.append("'");
		return output;
	}

	/**
	 * Gets the list for entity.
	 *
	 * @param entity
	 *            the entity
	 * @return the list for entity
	 */
	private CharSequence getListForEntity(AnalizedEntity entity) {
		StringBuilder output = new StringBuilder();

		String entityName = entity.getSimpleName();

		output.append(entityName).append("List:'");
		output.append("<div ng-hide=\"loading\">");
		output.append("<ng-paging page=\"page\" page-changed=\"pageChanged()\" ></ng-paging>");
		output.append("<ng-filtering sub-resources=\"subResources\" ");
		output.append("criteria-field=\"criteriaField\" criterias =\"criterias\" ");
		output.append("criteria-changed=\"pageChanged()\" ></ng-filtering>");
		output.append("<div ng-repeat=\"entity in entities\">");
		output.append("<a href=\"");
		output.append(this.getUrlForEntity("entity", entity));
		output.append("\">{{");
		output.append(this.getShowName("entity.", entity));
		output.append("}}</a>");
		output.append("<hr />");
		output.append("</div>");
		output.append("</div>");

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
		String nameOfFieldEntity = AnalizedEntityUtils.getEntitiesName(fieldEntity.getSimpleName()).toLowerCase();
		Field idField = CollectionUtils.find(fieldEntity.getFields(), this.idFieldPredicate);
		return StringUtils.join("#/", nameOfFieldEntity, "/{{", name, ".", idField.getSimpleName(), "}}");
	}

	private String getUrlForEntities(AnalizedEntity fieldEntity) {
		String nameOfFieldEntity = AnalizedEntityUtils.getEntitiesName(fieldEntity.getSimpleName()).toLowerCase();
		return StringUtils.join("#/", nameOfFieldEntity);
	}

	/**
	 * Gets the show name.
	 *
	 * @param name
	 *            the name
	 * @param entity
	 *            the entity
	 * @return the show name
	 */
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
