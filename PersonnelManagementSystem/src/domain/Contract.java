package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Contract implements Comparable<Contract>
{

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String startTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String endTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int signTimes;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String signStatus;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String ifWork;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Contract(){
		super();
	}

	public Contract(int id, String startTime, String endTime, int signTimes, String signStatus, String ifWork) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.signTimes = signTimes;
		this.signStatus = signStatus;
		this.ifWork = ifWork;
	}

	public Contract(String startTime, String endTime, int signTimes, String signStatus, String ifWork) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.signTimes = signTimes;
		this.signStatus = signStatus;
		this.ifWork = ifWork;
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

    @Override
	public int compareTo(Contract o) {
		return this.id - o.id;
	}

	@Override
	public String toString() {
		return "Contract{" +
				"id=" + id +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", signTimes=" + signTimes +
				", signStatus='" + signStatus + '\'' +
				", ifWork='" + ifWork + '\'' +
				'}';
	}
}

