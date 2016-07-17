package hospital_info_extract;

public class DATA_ITEAM {//数据码表
	public int DICT_ID;//
	public String DICT_CODE;//值编码，和外部的关联字段
	public String DICT_NAME;//值，对外的体现，如：右上肺
	public int GROUP_ID;//分类ID
	public String GROUP_NAME;//分类名称
	public String STATUS;//状态，1：有效，0：无效
	public String CREATE_DATE;//创建时间
	public String REMARK;//备注
	public String DESCRIPTION;//描述
	
}
