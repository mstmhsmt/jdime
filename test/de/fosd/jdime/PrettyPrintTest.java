/**
 * 
 */
package de.fosd.jdime;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.fosd.jdime.common.ASTNodeArtifact;
import de.fosd.jdime.common.FileArtifact;

/**
 * @author lessenic
 *
 */
public class PrettyPrintTest {

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ClassUtils
			.getShortClassName(PrettyPrintTest.class));

	/**
	 * Sets up the test fixture. (Called before every test case method.)
	 */
	@Before
	public void setUp() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
	}

	/**
	 * Test method for
	 * {@link de.fosd.jdime.common.ASTNodeArtifact#ASTNodeArtifact(de.fosd.jdime.common.FileArtifact)}
	 * .
	 */
	@Test
	public final void testASTNodeArtifactFileArtifact() {
		try {
			new ASTNodeArtifact(new FileArtifact(new File(
					"testfiles/left/SimpleTests/Bag/Bag2.java")));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	/**
	 * Test method for
	 * {@link de.fosd.jdime.common.ASTNodeArtifact#prettyPrint()}.
	 */
	@Test
	public final void testPrettyPrint() {
		try {
			ASTNodeArtifact artifact = new ASTNodeArtifact(new FileArtifact(
					new File("testfiles/left/SimpleTests/Bag/Bag2.java")));
			String prettyPrinted = artifact.prettyPrint();
			String expected = "class Bag {\n  int[] values;\n  Bag(int[] v) {\n    super();\n    values = v;\n  }\n  int[] get() {\n    return values;\n  }\n}";
			assertEquals(expected, prettyPrinted);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
