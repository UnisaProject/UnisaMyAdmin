package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GenericMessageEntityId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "MSG_CODE")
	private String messageCode;

	@Column(name = "PROGRAM")
	private String program;

	public GenericMessageEntityId() {
	}

	public String getMessageCode() {
		return this.messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.messageCode.hashCode();
		hash = hash * prime + this.program.hashCode();

		return hash;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GenericMessageEntityId)) {
			return false;
		}
		GenericMessageEntityId castOther = (GenericMessageEntityId) other;
		return
			this.messageCode.equals(castOther.messageCode)
				&& this.program.equals(castOther.program);
	}
}