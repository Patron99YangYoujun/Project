package hospital_info_extract;

public class T_TUMOR_STAGE {//肿瘤分期表
	public int ID;//
	public int CASE_ID;//患者病历主表ID，唯一标示
	public int STAGE;//一期，二期，三期用1,2,3标示
	public String T_PERIOD;//T类型，关联字典组
	public String M_PERIOD;//M类型，关联字典组
	public String N_PERIOD;//N类型，关联字典组
	public String STATUS;//状态，1为有效，0为无效
	public String CREATE_DATE;//创建时间
	
}
