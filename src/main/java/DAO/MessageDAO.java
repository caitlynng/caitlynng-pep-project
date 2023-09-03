package DAO;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    //3R
    public Message createMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "insert into message(posted_by, message_text,time_posted_epoch) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = rs.getInt(1);
                return new Message(generated_message_id, message.getPosted_by(),
                        message.getMessage_text(), message.getTime_posted_epoch());
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //3R
    public Boolean checkExistingAccountID(int account_id){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "select account_id from account where account_id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    //4R
    public List<Message> getAllMessages(){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from message";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    //5R
    public Message getMessageByMessageID(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "select * from message where message_id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //6R
    public Message deleteMessageByMessageID(Message message){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "delete from message where message_id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message.getMessage_id());
            ps.executeUpdate();
            return message;

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //7R
    public int updateMessageByMessageID(int message_id, String message_text){
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "update message set message_text=? where message_id=(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, message_text);
            ps.setInt(2, message_id);
            return ps.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
    //8R
    public List<Message> getAllMessagesByAccountID(int account_id){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from message join account on account.account_id = " +
                    "message.message_id where account.account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
