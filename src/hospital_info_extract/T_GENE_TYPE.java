package hospital_info_extract;

public class T_GENE_TYPE {//基因分型表
	public int ID;//
	public int CASE_ID;//患者病历主表ID，唯一标示
	public String EGFR_TYPE;//突变，无突变，关联字典组表
	public String EGFR_TEST_TYPE;//EGFR检测方式,关联字典组
	public String ALK_TYPE;//阴性，阳性，关联字典组表
	public String ALK_TEST_TYPE;//ALK检测方式,关联字典组
	public String OTHER;//其它类型
	public String CREATE_DATE;//创建时间
	public String REMARK;//备注
	
}
