package za.ac.unisa.myadmin.parceltracking;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adrian on 2018-05-30.
 */
public class ParcelTrackingInfo implements Serializable {

	private static final long serialVersionUID = -8761284121915231771L;

	private List<TrackAndTraceRecordInfo> traceRecordInfoList;

	private boolean studentuser;

	private StudentInfo studentInfo;

	private boolean notvalid;

	public List<TrackAndTraceRecordInfo> getTraceRecordInfoList() {
		return traceRecordInfoList;
	}

	public void setTraceRecordInfoList(List<TrackAndTraceRecordInfo> traceRecordInfoList) {
		this.traceRecordInfoList = traceRecordInfoList;
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
