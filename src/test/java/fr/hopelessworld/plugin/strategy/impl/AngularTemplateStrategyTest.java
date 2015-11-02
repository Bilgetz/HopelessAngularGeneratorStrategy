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

public class AngularTemplateStrategyTest {

	private AngularTemplateStrategy strategy = new AngularTemplateStrategy();

	@Test
	public void testGenerateNoEntities() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		CharSequence sequence = strategy.generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		Assert.assertEquals("Sequence bad generate", "var angularTemplate = {};", sequence.toString());
	}

	@Test
	public void testGenerateOneEntities() throws Exception {
		Collection<AnalizedEntity> entities = new ArrayList<>();
		AnalizedEntity entity = mock(AnalizedEntity.class);
		entities.add(entity);

		when(entity.getSimpleName()).thenReturn("Player");

		Collection<Field> fields = new ArrayList<>();
		when(entity.getFields()).thenReturn(fields);

		fields.add(createIdField());
		fields.add(createMockField("name", String.class.toString()));

		CharSequence sequence = strategy.generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "{{!-- id not show --}}"
				+ "{{'entity.Player.name' | translate }}:{{data.name}}<br />'"
				+ "};";
		// @formatter:on

		Assert.assertEquals("Sequence bad generate", expected, sequence.toString());
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

		CharSequence sequence = strategy.generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "{{!-- id not show --}}"
				+ "{{'entity.Player.name' | translate }}:{{data.name}}<br />"
				+ "{{'entity.Player.creation' | translate }}:{{data.creation | date:'medium'}}<br />'"
				+ "};";
		// @formatter:on

		Assert.assertEquals("Sequence bad generate", expected, sequence.toString());
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
		teamFields.add(createMockField("name", String.class.toString()));

		playerfields.add(createEntityMockField("team", teamEntity));

		entities.add(playerEntity);
		entities.add(teamEntity);

		CharSequence sequence = strategy.generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "{{!-- id not show --}}"
				+ "{{'entity.Player.name' | translate }}:{{data.name}}<br />"
				+ "{{'entity.Player.team' | translate }}:<a href=\"#/team/{{data.team.id}}\">{{data.team.name}}</a><br />'"
				+ "," //une autre entité
				+ "Team:'<h1>{{data.name}}</h1>"
				+ "{{!-- id not show --}}"
				+ "{{'entity.Team.name' | translate }}:{{data.name}}<br />'"
				+ "};";
		// @formatter:on

		Assert.assertEquals("Sequence bad generate", expected, sequence.toString());
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
		teamFields.add(createMockField("name", String.class.toString()));
		teamFields.add(createOneToManyMockField("players", "player", playerEntity));

		playerfields.add(createEntityMockField("team", teamEntity));

		entities.add(playerEntity);
		entities.add(teamEntity);

		CharSequence sequence = strategy.generate(entities);

		Assert.assertNotNull("Sequence is null", sequence);
		// @formatter:off
		String expected = "var angularTemplate = {" 
				+ "Player:'<h1>{{data.name}}</h1>" 
				+ "{{!-- id not show --}}"
				+ "{{'entity.Player.name' | translate }}:{{data.name}}<br />"
				+ "{{'entity.Player.team' | translate }}:<a href=\"#/team/{{data.team.id}}\">{{data.team.name}}</a><br />'"
				+ "," //une autre entité
				+ "Team:'<h1>{{data.name}}</h1>"
				+ "{{!-- id not show --}}"
				+ "{{'entity.Team.name' | translate }}:{{data.name}}<br />"
				+ "{{'entity.Team.players' | translate }}:<ul><li ng-repeat=\"entity in data.players\">"
				+ "<a href=\"#/player/{{entity.id}}\">{{entity.name}}</a>"
				+ "</li></ul><br />'"
				+ "};";
		// @formatter:on

		Assert.assertEquals("Sequence bad generate", expected, sequence.toString());
	}

}
