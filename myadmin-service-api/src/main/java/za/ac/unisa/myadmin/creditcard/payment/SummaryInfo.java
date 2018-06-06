package za.ac.unisa.myadmin.creditcard.payment;

import java.io.Serializable;

/**
 * Created by dev on 2018-06-06.
 */
public class SummaryInfo implements Serializable{

	private boolean errorFlag;

	private String summaryMessage;

	public SummaryInfo() {
	}

	public SummaryInfo(boolean errorFlag, String summaryMessage) {
		this.errorFlag = errorFlag;
		this.summaryMessage = summaryMessage;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getSummaryMessage() {
		return summaryMessage;
	}

	public void setSummaryMessage(String summaryMessage) {
		this.summaryMessage = summaryMessage;
	}
}
