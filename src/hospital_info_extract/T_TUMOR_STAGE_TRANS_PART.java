package hospital_info_extract;

public class T_TUMOR_STAGE_TRANS_PART {//肿瘤分期转移部位表
	public int ID;//
	public int CASE_ID;//患者病历主表ID，唯一标示
	public int STAGE;//第几阶段
	public String TYPE;//T/N/M
	public String BODY_CODE;//转移部位
	public String BODY_SIZE;//转移部位大小
	public String CREATE_DATE;//创建时间
	
}
