package hospital_info_extract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PRE_DEAL {

	public static String dbUrl="jdbc:sqlserver://127.0.0.1:1433; DatabaseName=InfoFetch";
	public static String user = "sa";
	public static String password = "YYJ";
	public static Statement stmt;
	public static Connection c;
	public static ResultSet rs; 
	public static String[] Iteam={"出院时间","入院情况","入院诊断","诊疗经过","出院情况","出院诊断","出院医嘱","经治医师","上级医师"};
	
 	public static void ConnectJdbc() {
		// TODO Auto-generated method stub
		try {
			// Load the driver (registers itself)
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e){
			System.out.println("InfoFetch数据库驱动加载失败!");
		}
		try {
			c = DriverManager.getConnection(dbUrl, user, password);
			stmt=c.createStatement();
			System.out.println("链接InfoFetch数据库成功!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void CreateExcel(String sqlStatment){
		String str="create table Sample_Cases(ID int,Patient_Info varchar(MAX) , Discharge_Time varchar(MAX) , Admission varchar(MAX) , "+
				"Hospital_Diagnosis varchar(MAX) ,Diagnosis_Process varchar(MAX) ,Out_Situation varchar(MAX) ,Out_Diagnosis varchar(MAX) , "+
				"Out_Advice varchar(MAX) ,Attending_Doctor varchar(MAX) ,Superior_Doctors varchar(MAX) ,Note varchar(MAX))";
		sqlStatment=str;
		try {
			stmt.execute(sqlStatment);
			System.out.println("创建表成功!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToSQL(String SQL_Name,String SQL_Str,String text){
		try {
			rs=stmt.executeQuery("Select * From "+SQL_Name);
			stmt.executeUpdate("insert into dbo."+SQL_Name+"("+SQL_Str+") values ("+text+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeSQL(){
		try {
			rs.close();
			c.close();
			System.out.println("数据库关闭成功!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BLTxtRead();
		ConnectJdbc();
		CreateExcel("");
		BLTxtDeal();
		closeSQL();
	}
	public static void BLTxtRead(){
		String str,result="";
		String[] ResultStr;
		String FilePath = "F:/javaworkspace/WorkSpace/Git/Resources/病例原件.txt";
		String WritePath = "F:/javaworkspace/WorkSpace/Git/Resources/Write/";
		long TimeBegin=System.currentTimeMillis();
		try {
			BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(FilePath),"UTF-8"));
			while ((str = fin.readLine()) != null) {
				if(!str.isEmpty()){
					result+=str+"\r\n";
				}
			} 
			System.out.println(result);
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long TimeEnd=System.currentTimeMillis();
		System.out.println("读入病例共花时间:"+(TimeBegin-TimeEnd)/3600000+"小时"+(TimeBegin-TimeEnd)/60000+"分钟"+(TimeBegin-TimeEnd)/1000+"秒");
		ResultStr=result.split("电话023-68757674\r\n");
		for(int i=0;i<ResultStr.length;i++){
			str="";
			str+=ResultStr[i]+"电话023-68757674\r\n";
			WriteToTxt(str,WritePath+"病例"+(i+1)+".txt");
			System.out.println("写病历"+(i+1)+"成功\n");
		}
	}
	
	public static void BLTxtDeal(){
		String WritePath = "F:/javaworkspace/WorkSpace/Git/Resources/Write/";
		String strs="ID,Patient_Info,Discharge_Time,Admission,Hospital_Diagnosis,Diagnosis_Process,Out_Situation,Out_Diagnosis,Out_Advice,Attending_Doctor,Superior_Doctors,Note";		
		String str,save="",result="";
		int flag=0;
		long TimeBegin=System.currentTimeMillis();
		try {
			for(int i=0;i<13;i++){
				BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(WritePath+"病例"+(i+1)+".txt"),"UTF-8"));
				result+=(i+1);
				flag=0;
				while ((str = fin.readLine()) != null) {
					str=str.replaceAll("'", "’");
					for(int j=0;j<Iteam.length;j++){
						if(str.contains(Iteam[j])){
							if(save!=""&&flag==0){
								result+=",'"+save+"'";
								save="";
							}
							flag=1;
							result+=",'"+str+"'";
							break;
						}
					}
					if(flag==0){
						save+=str;
					}
					else{
						flag=0;
					}
				} 
				fin.close();
				result+=",'"+save+"'";
				writeToSQL("Sample_Cases",strs,result);
//				System.out.println(result);
				save="";
				result="";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long TimeEnd=System.currentTimeMillis();
		System.out.println("病例写入数据库共花时间:"+(TimeEnd-TimeBegin)/3600000+"小时"+(TimeEnd-TimeBegin)/60000+"分钟"+(TimeEnd-TimeBegin)/1000+"秒");
	}
	
	public static void WriteToTxt(String str,String WritePath){
		try {
            File file = new File(WritePath);
            if (!file.exists()) {
            	file.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);   
            writer.write(str);
			writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
