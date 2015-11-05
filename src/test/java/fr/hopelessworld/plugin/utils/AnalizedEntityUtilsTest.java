package fr.hopelessworld.plugin.utils;

import org.junit.Assert;
import org.junit.Test;

public class AnalizedEntityUtilsTest {

	/**
	 * Test to tag name.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testToTagName() throws Exception {

		Assert.assertEquals("ng-player-edit", AnalizedEntityUtils.toTagName("ngPlayerEdit").toString());
		Assert.assertEquals("ng-player", AnalizedEntityUtils.toTagName("ngPlayer").toString());

	}

}
