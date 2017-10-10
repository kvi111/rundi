import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Optional;

/**
 * 运行方法：
 * java -Djava.ext.dirs=./ -jar rundi.jar
 */
public class main {
    static String selectsql = null;
    static ResultSet retsult = null;

    public static final String url = "jdbc:mysql://rds0710650me01y6d3ogo.mysql.rds.aliyuncs.com/yunketest";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "yunker";
    public static final String password = "yunke2016";

    public static Connection conn = null;
    public static PreparedStatement pst = null;

    public static void main(String[] args) {
        Joiner.on(",").join(Arrays.asList(1, 5, 7));

        //ReadSql();

        //WriteFile();

        try {
            DbUtilsTest.insert_test();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.printf("ok");
    }

    private static void WriteFile() {
        File file = new File("//root//a.txt");
        FileOutputStream outputStream;
        boolean FileExists = false;


        if (file.exists() == false) {
            try {
                FileExists = file.createNewFile();
            }
            catch (Exception ex)
            {
                System.out.printf("error:"+ ex.getMessage());
                return;
            }
        }
        else{
            FileExists = true;
        }
        if(FileExists==true) {
            try {
                String str = "asddf\n";
                outputStream = new FileOutputStream(file,true);
                outputStream.write(str.getBytes());
                outputStream.close();
            }
            catch (Exception ex)
            {
                System.out.printf("error:"+ ex.getMessage());
            }
        }
    }

    public static void ReadSql()
    {
        int paraCount = 5; //读取参数数量
        selectsql = "select * from ai_yan_case";//SQL语句

        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(selectsql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }

        String [] paras = new String [paraCount];
        try {
            retsult = pst.executeQuery();//执行语句，得到结果集

            while (retsult.next()) {
                for(int i = 0;i<paraCount;i++){
                    paras[i] = retsult.getString(i+2);
                }
                System.out.println(Arrays.toString(paras));

            }//显示数据
            retsult.close();
            conn.close();//关闭连接
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
