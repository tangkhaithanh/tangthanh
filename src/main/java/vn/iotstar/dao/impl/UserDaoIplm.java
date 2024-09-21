package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;
// Đây là class viết từng hàm:
public class UserDaoIplm extends DBConnectSQL implements IUserDao {

	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs= null;
	@Override
	public List<UserModel> findAll() {
	    String sql = "SELECT * FROM users";
	    List<UserModel> list = new ArrayList<UserModel>();
	    
	    try {
	        conn = new DBConnectSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            list.add(new UserModel(
	                rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("password"),
	                rs.getString("images"),
	                rs.getString("fullname"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getInt("roleid"),
	                rs.getDate("createDate")
	            ));
	        }
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Remember to close the resources like ResultSet, PreparedStatement, and Connection if applicable
	    }
	    
	    return list;
	}

	

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
				conn = new DBConnectSQL().getConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user; 
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void insert(UserModel user) {
	    String sql = "INSERT INTO [user] (username, password, images, fullname, email, phone, roleid, createDate) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try {
	        conn = super.getConnection(); // Kết nối cơ sở dữ liệu
	        ps = conn.prepareStatement(sql);
	        // Thiết lập các giá trị cho các tham số trong câu lệnh SQL
	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getImages());
	        ps.setString(4, user.getFullname());
	        ps.setString(5, user.getEmail());
	        ps.executeUpdate(); // Thực thi câu lệnh SQL
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Đảm bảo đóng các kết nối để tránh rò rỉ tài nguyên
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	//int id, String username, String email, String password, String fullname, String images)
	// xuất kết quả truy vấn
	// test: 
	public static void main(String[] args)
	{
		try
		{
			IUserDao userDao= new UserDaoIplm();
			System.out.println(userDao.findById(1));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public UserModel findByUsername(String username) 
	{
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
				conn = new DBConnectSQL().getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user; 
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
