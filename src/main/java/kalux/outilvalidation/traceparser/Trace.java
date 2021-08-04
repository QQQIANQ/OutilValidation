package kalux.outilvalidation.traceparser;

public class Trace {
	String expectedLetterDetails;

	String groundTruthLetterDetails;

	String exceptedValue;

	int nbTrace;

	public Trace(String expectedLetterDetails, String groundTruthLetterDetails) {
		this.expectedLetterDetails = expectedLetterDetails;
		this.groundTruthLetterDetails = groundTruthLetterDetails;
	}

	public Trace() {
		super();
	}

	public String getExpectedValue() {
		return this.exceptedValue;
	}

	public String setExpectedValue(String expectedValue) {
		return this.exceptedValue = expectedValue;
	}

	public int getNbTrace() {
		return this.nbTrace;
	}

	public int setNbTrace(int nbTrace) {
		return this.nbTrace = nbTrace;
	}

	public String getExpectedLetterDetails() {
		return this.expectedLetterDetails;
	}

	public void setExpectedLetterDetails(String expectedLetterDetails) {
		this.expectedLetterDetails = expectedLetterDetails;
	}

	public String getGroundTruthLetterDetails() {
		return this.groundTruthLetterDetails;
	}

	public void setGroundTruthLetterDetails(String groundTruthLetterDetails) {
		this.groundTruthLetterDetails = groundTruthLetterDetails;
	}

	@Override
	public String toString() {
		return "ExpectedLetterDetails : " + expectedLetterDetails + "/n GroundTruthLetterDetails : "
				+ groundTruthLetterDetails;
	}

}
