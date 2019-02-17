import com.nsfocus.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TxQueryRunnerTest {
    TxQueryRunner tx =null;
    @Before
    public void setUp() throws Exception {
         tx = new TxQueryRunner();
    }

    @Test
    public void batch() throws SQLException {
        String sql = "insert into  user(username,gender,age) values(?,?,?)";
        Object[][] params = new Object[10][];
        for(int i=0;i<10;i++){
            params[i] = new Object[]{"cheng","batch",i};
        }
        tx.batch(sql, params);
    }

    @Test
    public void query() throws SQLException {
        String sql = "select * from user";
        List<User> list = tx.query(sql, new BeanListHandler<User>(User.class));
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void query1() throws SQLException {
        String sql = "select * from user where uid = ?";
        User user = tx.query(sql, "1", new BeanHandler<User>(User.class));
        System.out.println(user);
    }

    @Test
    public void query2() throws SQLException {
        String sql = "select * from user where uid = ? and username=?";
        List<User> list = tx.query(sql, new String[]{"1", "chengxingfu"}, new BeanListHandler<User>(User.class));
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void query3() throws SQLException {
        String sql = "update user set username=? where username =?";
        tx.update(sql, new String[]{"meilan", "chengxingfu"});
    }

    @Test
    public void update() throws SQLException {
        String sql = "update user set gender=? where username=?";
        tx.update(sql, new String[]{"女", "meilan"});

    }

    @Test
    public void update1() {

    }

    @Test
    public void update2() {
    }
}