package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {
    //1R
    public Account registerAccount(Account account){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "insert into account(username, password) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_account_id = rs.getInt(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //1R
    public Boolean checkExistingUsername(String username){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "select username from account where username=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    //2R
    public Account loginAccount(Account account){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where username=(?) and password=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ResultSet rs = ps.executeQuery();;
            if(rs.next()){
                return new Account(rs.getInt("account_id"),
                            rs.getString("username"),
                        rs.getString("password")
                        );
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
