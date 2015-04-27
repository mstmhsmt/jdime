package de.fosd.jdime.matcher;

import de.fosd.jdime.common.Artifact;
import de.fosd.jdime.common.UnorderedTuple;
import org.apache.commons.lang3.ClassUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Matchings<T extends Artifact<T>> {

	private static final Logger LOG = Logger.getLogger(ClassUtils.getShortClassName(Matchings.class));

	private Map<UnorderedTuple<T, T>, NewMatching<T>> matchings;

	public static <T extends Artifact<T>> Matchings<T> of(T left, T right, int score) {
		Matchings<T> result = new Matchings<>();
		result.add(new NewMatching<T>(left, right, score));

		return result;
	}

	public void add(NewMatching<T> matching) {
		getMatchings().put(matching.getMatchedArtifacts(), matching);
	}

	public void addAll(Collection<? extends NewMatching<T>> matchings) {
		for (NewMatching<T> matching : matchings) {
			add(matching);
		}
	}

	public void addAllMatchings(Matchings<T> matchings) {

		if (matchings.isEmpty()) {
			return;
		}

		for (Map.Entry<UnorderedTuple<T, T>, NewMatching<T>> entry : matchings.getMatchings().entrySet()) {
			if (!entry.getKey().equals(entry.getValue().getMatchedArtifacts())) {
				LOG.warning("Ignoring a call to Matchings#addAllMatchings(Matching<T>) because the Matchings instance contains invalid mappings.");
				return;
			}
		}

		getMatchings().putAll(matchings.getMatchings());
	}

	public void addAllMatchings(Collection<? extends Matchings<T>> matchings) {
		for (Matchings<T> matching : matchings) {
			addAllMatchings(matching);
		}
	}

	public NewMatching<T> get(UnorderedTuple<T, T> artifacts) {
		return getMatchings().get(artifacts);
	}

	public NewMatching<T> get(T left, T rigt) {
		return getMatchings().get(UnorderedTuple.of(left, rigt));
	}

	public int getScore(UnorderedTuple<T, T> artifacts) {
		if (!contains(artifacts)) {
			return -1;
		} else {
			return get(artifacts).getScore();
		}
	}

	public int getScore(T left, T right) {
		return getScore(UnorderedTuple.of(left, right));
	}

	public boolean contains(UnorderedTuple<T, T> artifacts) {
		return getMatchings().containsKey(artifacts);
	}

	public boolean contains(T left, T right) {
		return getMatchings().containsKey(UnorderedTuple.of(left, right));
	}

	public Map<UnorderedTuple<T, T>, NewMatching<T>> getMatchings() {

		if (matchings == null) {
			matchings = new HashMap<>();
		}

		return matchings;
	}

	public boolean isEmpty() {
		return matchings == null || matchings.isEmpty();
	}
}
