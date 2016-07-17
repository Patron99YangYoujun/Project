package hospital_info_extract;

public class T_PATIENT_CASE_MAIN {//病历主表
	public int ID;//
	public String PATIENT_NAME;//患者姓名
	public int PATIENT_ID;//患者ID，医院唯一标示
	public String ID_NO;//患者身份证
	public int AGE;//年龄
	public String SEX;//性别
	public String NATIVE;//籍贯
	public String JOB;//职业
	public int SMOKING_YEAR;//吸烟史年
	public String SMOKING_RATE;//吸烟频率
	public int STATE;//是否确诊为肺癌，1是，0否
	public String QZ_DATE;//确诊日期
	public String QZ_WAY;//确诊方式（活检方式表）
	public String QZ_BODY_CODE;//确诊部位（人体部位表）
	public String BL_TYPE_CODE;//病理类型编号，（病理类型表）
	public String MAIN_DOCTOR_DOC;//主治医生编码

}
