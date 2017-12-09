package github.eigenheim.efood.backend.components.index;

import java.util.Comparator;

public class ScoredResult implements Comparable<ScoredResult> {
    private String id;
    private double score;

    public ScoredResult(String id, double score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoredResult scoredResult) {
        return Double.compare(this.getScore(), scoredResult.getScore());
    }
}
