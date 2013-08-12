/**
 * 
 */
package de.fosd.jdime.strategy;

import java.io.IOException;

import de.fosd.jdime.common.ASTNodeArtifact;
import de.fosd.jdime.common.MergeContext;
import de.fosd.jdime.common.MergeTriple;
import de.fosd.jdime.common.NotYetImplementedException;
import de.fosd.jdime.common.operations.MergeOperation;
import de.fosd.jdime.stats.Stats;

/**
 * @author Olaf Lessenich
 * 
 */
public class ASTNodeStrategy extends MergeStrategy<ASTNodeArtifact> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fosd.jdime.strategy.MergeInterface#merge(
	 * de.fosd.jdime.common.operations
	 * .MergeOperation, de.fosd.jdime.common.MergeContext)
	 */
	@Override
	public final void merge(final MergeOperation<ASTNodeArtifact> operation,
			final MergeContext context) 
					throws IOException, InterruptedException {
		assert (operation != null);
		assert (context != null);
		
		MergeTriple<ASTNodeArtifact> triple = operation.getMergeTriple();
		
		assert (triple.isValid());
		
		ASTNodeArtifact left = triple.getLeft();
		ASTNodeArtifact base = triple.getBase();
		ASTNodeArtifact right = triple.getRight();
		ASTNodeArtifact target = operation.getTarget();
		
		ASTNodeArtifact[] revisions = { left, base, right };
		
		for (ASTNodeArtifact node : revisions) {
			assert (node.exists());
		}
		
		assert (target != null);
		
		
		// FIXME: remove me when implementation is complete
		throw new NotYetImplementedException(
				"ASTNodeStrategy: Implement me!");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fosd.jdime.strategy.MergeStrategy#toString()
	 */
	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return "astnode";
	}

	@Override
	public final Stats createStats() {
		return new Stats(new String[] {"nodes"});
	}

	@Override
	public final String getStatsKey(final ASTNodeArtifact artifact) {
		// FIXME: remove me when implementation is complete
		throw new NotYetImplementedException(
				"ASTNodeStrategy: Implement me!");
	}

	@Override
	public void dump(final ASTNodeArtifact artifact) throws IOException {
		System.out.println(artifact.dumpTree());
	}

}
