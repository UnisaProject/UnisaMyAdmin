package za.ac.unisa.myadmin.tracking.services.dto;

import java.io.Serializable;

public class TrackingInfo implements Serializable {

	private static final long serialVersionUID = -3974104637983406999L;

	private String trackTraceDate;

	private String trackTraceAgent;

	private String trackTraceNumber;

	public String getTrackTraceDate() {
		return trackTraceDate;
	}

	public void setTrackTraceDate(String trackTraceDate) {
		this.trackTraceDate = trackTraceDate;
	}

	public String getTrackTraceNumber() {
		return trackTraceNumber;
	}

	public void setTrackTraceNumber(String trackTraceNumber) {
		this.trackTraceNumber = trackTraceNumber;
	}

	public String getTrackTraceAgent() {
		return trackTraceAgent;
	}

	public void setTrackTraceAgent(String trackTraceAgent) {
		this.trackTraceAgent = trackTraceAgent;
	}

}

