import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class DbUtilsTest {
    private static String driveClassName = "com.mysql.cj.jdbc.Driver"; //"com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://rds0710650me01y6d3ogo.mysql.rds.aliyuncs.com:3306/yunketest?useUnicode=true&characterEncoding=utf8";

    private static String user = "yunker";
    private static String password = "yunke2016";

    public static Connection Connect(){
        Connection conn = null;

        //load driver
        try {
            Class.forName(driveClassName);
        } catch (ClassNotFoundException  e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        //connect db
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }

        return conn;
    }

    static void insert_test() throws SQLException {
        Connection conn = Connect();

        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();

        //执行SQL插入
        int n = qRunner.update(conn, "insert into test1(a) values('xxx')");
        System.out.println("成功插入" + n + "条数据！");

        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

    static void select_test() throws SQLException{
        Connection conn = Connect();

        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();

        @SuppressWarnings("unchecked")
        List<UserBean> list = (List<UserBean>) qRunner.query(conn, "select id,name,age from user", new BeanListHandler(UserBean.class));
        //输出查询结果
        for (UserBean user : list) {
            System.out.println(user.getAge());
        }

        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

    static void update_test() throws SQLException{
        Connection conn = Connect();

        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        //执行SQL插入
        int n = qRunner.update(conn, "update user set name = 'xxx',age=28");
        System.out.println("成功更新" + n + "条数据！");

        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }

    static void del_test() throws SQLException{
        Connection conn = Connect();

        //创建SQL执行工具
        QueryRunner qRunner = new QueryRunner();
        //执行SQL插入
        int n = qRunner.update(conn, "DELETE from user WHERE name='xxx';");
        System.out.println("删除成功" + n + "条数据！");

        //关闭数据库连接
        DbUtils.closeQuietly(conn);
    }
}
