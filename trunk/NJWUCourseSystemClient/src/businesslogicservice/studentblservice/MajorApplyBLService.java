package businesslogicservice.studentblservice;

import java.util.Vector;

import po.studentpo.MajorTransferPO;

public interface MajorApplyBLService {

	public abstract Vector<String> getMajorApplyStatus(String student_id);

	public abstract boolean hasApplied(String student_id);

	public abstract void apply(MajorTransferPO po);

}