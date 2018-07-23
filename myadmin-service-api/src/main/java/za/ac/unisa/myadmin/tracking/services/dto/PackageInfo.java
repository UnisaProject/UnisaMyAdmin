package za.ac.unisa.myadmin.tracking.services.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * Created by Adrian on 2018-05-30.
 */
public class PackageInfo implements Serializable {

	private static final long serialVersionUID = -8761284121915231771L;

	private List<TrackingInfo> trackings;

	private boolean studentuser;

	private StudentInfo studentInfo;

	private boolean notvalid;

	public List<TrackingInfo> getTrackings() {
		if (trackings == null) {
			trackings = new ArrayList<>();
		}
		return trackings;
	}

	public void setTrackings(List<TrackingInfo> trackings) {
		this.trackings = trackings;
	}

	public boolean isStudentuser() {
		return studentuser;
	}

	public void setStudentuser(boolean studentuser) {
		this.studentuser = studentuser;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public boolean isNotvalid() {
		return notvalid;
	}

	public void setNotvalid(boolean notvalid) {
		this.notvalid = notvalid;
	}
}
