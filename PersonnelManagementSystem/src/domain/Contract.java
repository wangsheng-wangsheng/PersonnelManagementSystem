package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Contract {
	private int id;
	private String startTime;
	private String endTime;
	private int signTimes;
	private String signStatus;
	private String ifWork;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Contract(){
		super();
	}
	public Contract(String startTime, String endTime, int signTimes,
					String signStatus, String ifWork){
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.signTimes = signTimes;
		this.signStatus = signStatus;
		this.ifWork = ifWork;
	}
	public Contract(int id, String startTime, String endTime,
					int signTimes, String signStatus, String ifWork){
		this(startTime,endTime,signTimes,signStatus,ifWork);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getSignTimes() {
		return signTimes;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public String getIfWork() {
		return ifWork;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setSignTimes(int signTimes) {
		this.signTimes = signTimes;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public void setIfWork(String ifWork) {
		this.ifWork = ifWork;
	}

	public String toString()
	{
		final String TAB = "    ";
		String retValue = "Contract ( "
				+ super.toString() + TAB
				+ "id = " + this.id + TAB
				+ "startTime = " + this.startTime + TAB
				+ "endTime = " + this.endTime + TAB
				+ "signTimes = " + this.signStatus + TAB
				+ "signStatus = " + this.signStatus + TAB
				+ "ifwork = " + this.ifWork + TAB
				+ " )";
		return retValue;
	}
}

