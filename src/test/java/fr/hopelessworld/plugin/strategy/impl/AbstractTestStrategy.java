package fr.hopelessworld.plugin.strategy.impl;

import static fr.hopelessworld.plugin.strategy.impl.TestUtils.createEntityMockField;
import static fr.hopelessworld.plugin.strategy.impl.TestUtils.createIdField;
import static fr.hopelessworld.plugin.strategy.impl.TestUtils.createMockField;
import static fr.hopelessworld.plugin.strategy.impl.TestUtils.createOneToManyMockField;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import fr.hopelessworld.plugin.analyzer.AnalizedEntity;
import fr.hopelessworld.plugin.analyzer.Field;
import fr.hopelessworld.plugin.strategy.AbstractUniqueFileGeneratorStrategy;

public abstract class AbstractTestStrategy {

	protected abstract AbstractUniqueFileGeneratorStrategy getStrategy();

	protected abstract String getExpectedNoEntity();

	protected abstract String getExpectedOneEntity();

	protected abstract String getExpectedOneEntityWith2Field();

	protected abstract String getExpectedOneEntityWith1EntityField();

	protected abstract String getExpectedOneEntityWithOneToManyField();

	@Test
	public void testGenerateNoEntities() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		CharSequence sequence = getStrategy().generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate", getExpectedNoEntity(), sequence.toString());
	}

	@Test
	public void testGenerateOneEntity() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		AnalizedEntity entity = mock(AnalizedEntity.class);
		entities.add(entity);

		when(entity.getSimpleName()).thenReturn("Player");

		Collection<Field> fields = new ArrayList<>();
		when(entity.getFields()).thenReturn(fields);

		fields.add(createIdField());
		fields.add(createMockField("name", String.class.getCanonicalName()));

		CharSequence sequence = getStrategy().generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate ( " + this.getClass().getSimpleName() + ")", getExpectedOneEntity(),
				sequence.toString());
	}

	@Test
	public void testGenerateOneEntitiesWith2Field() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		AnalizedEntity entity = mock(AnalizedEntity.class);
		entities.add(entity);

		when(entity.getSimpleName()).thenReturn("Player");

		Collection<Field> fields = new ArrayList<>();
		when(entity.getFields()).thenReturn(fields);

		fields.add(createIdField());
		fields.add(createMockField("name", String.class.getCanonicalName()));
		fields.add(createMockField("creation", Date.class.getCanonicalName()));

		CharSequence sequence = getStrategy().generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate ( " + this.getClass().getSimpleName() + ")",
				getExpectedOneEntityWith2Field(), sequence.toString());
	}

	@Test
	public void testGenerateOneEntitiesWith1EntityField() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		/** Player entity **/
		AnalizedEntity playerEntity = mock(AnalizedEntity.class);
		when(playerEntity.getSimpleName()).thenReturn("Player");

		Collection<Field> playerfields = new ArrayList<>();
		when(playerEntity.getFields()).thenReturn(playerfields);
		playerfields.add(createIdField());
		playerfields.add(createMockField("name", String.class.getCanonicalName()));

		/** Team Entity **/
		AnalizedEntity teamEntity = mock(AnalizedEntity.class);
		when(teamEntity.getSimpleName()).thenReturn("Team");

		Collection<Field> teamFields = new ArrayList<>();
		when(teamEntity.getFields()).thenReturn(teamFields);
		teamFields.add(createIdField());
		teamFields.add(createMockField("name", String.class.getCanonicalName()));

		playerfields.add(createEntityMockField("team", teamEntity));

		entities.add(playerEntity);
		entities.add(teamEntity);

		CharSequence sequence = getStrategy().generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate ( " + this.getClass().getSimpleName() + ")",
				getExpectedOneEntityWith1EntityField(), sequence.toString());
	}

	@Test
	public void testGenerateOneEntitiesWithOneToManyField() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		/** Player entity **/
		AnalizedEntity playerEntity = mock(AnalizedEntity.class);
		when(playerEntity.getSimpleName()).thenReturn("Player");

		Collection<Field> playerfields = new ArrayList<>();
		when(playerEntity.getFields()).thenReturn(playerfields);
		playerfields.add(createIdField());
		playerfields.add(createMockField("name", String.class.getCanonicalName()));

		/** Team Entity **/
		AnalizedEntity teamEntity = mock(AnalizedEntity.class);
		when(teamEntity.getSimpleName()).thenReturn("Team");

		Collection<Field> teamFields = new ArrayList<>();
		when(teamEntity.getFields()).thenReturn(teamFields);
		teamFields.add(createIdField());
		teamFields.add(createMockField("name", String.class.getCanonicalName()));
		teamFields.add(createOneToManyMockField("players", "player", playerEntity));

		playerfields.add(createEntityMockField("team", teamEntity));

		entities.add(playerEntity);
		entities.add(teamEntity);

		CharSequence sequence = getStrategy().generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate ( " + this.getClass().getSimpleName() + ")",
				getExpectedOneEntityWithOneToManyField(), sequence.toString());
	}

}
