package fr.hopelessworld.plugin.strategy.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.lang.model.type.TypeMirror;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;

public final class TestUtils {

	/**
	 * Instantiates a new test utils.
	 */
	private TestUtils() {
	}

	/**
	 * Creates the id field.
	 *
	 * @return the field
	 */
	public static Field createIdField() {
		Field idField = mock(Field.class);
		Id idAnnot = mock(Id.class);
		when(idField.getSimpleName()).thenReturn("id");
		when(idField.getAnnotation(Id.class)).thenReturn(idAnnot);
		return idField;
	}

	/**
	 * Creates the mock field for type @column.
	 *
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @return the field
	 */
	public static Field createMockField(String name, String type) {
		Field field = mock(Field.class);
		Column columFieldAnnot = mock(Column.class);
		when(field.getAnnotation(Column.class)).thenReturn(columFieldAnnot);
		when(field.getSimpleName()).thenReturn(name);
		TypeMirror fieldTypeMirror = mock(TypeMirror.class);
		when(field.asType()).thenReturn(fieldTypeMirror);
		when(fieldTypeMirror.toString()).thenReturn(type);
		return field;
	}

	/**
	 * Creates the entity mock field.
	 *
	 * @param name
	 *            the name
	 * @param entity
	 *            the entity
	 * @return the field
	 */
	public static Field createEntityMockField(String name, AnalizedEntity entity) {
		Field field = mock(Field.class);
		ManyToOne columFieldAnnot = mock(ManyToOne.class);
		when(field.getAnnotation(ManyToOne.class)).thenReturn(columFieldAnnot);
		when(field.getSimpleName()).thenReturn(name);
		// TypeMirror fieldTypeMirror = mock(TypeMirror.class);
		// when(field.asType()).thenReturn(fieldTypeMirror);
		// when(fieldTypeMirror.toString()).thenReturn(type);
		when(field.asAnalyzedEntity()).thenReturn(entity);
		return field;
	}

	/**
	 * Creates the one to many mock field.
	 *
	 * @param name
	 *            the name
	 * @param entity
	 *            the entity
	 * @return the field
	 */
	public static Field createOneToManyMockField(String name, String type, AnalizedEntity entity) {
		Field field = mock(Field.class);
		OneToMany columFieldAnnot = mock(OneToMany.class);
		when(field.getAnnotation(OneToMany.class)).thenReturn(columFieldAnnot);
		when(field.getSimpleName()).thenReturn(name);

		TypeMirror fieldTypeMirror = mock(TypeMirror.class);
		when(field.asType()).thenReturn(fieldTypeMirror);
		when(fieldTypeMirror.toString()).thenReturn("java.util.List<" + type + ">");
		when(field.asAnalyzedEntity()).thenReturn(entity);

		return field;
	}

}
