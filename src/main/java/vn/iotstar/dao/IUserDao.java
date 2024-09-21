package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;
// Dao là lớp định nghĩa phương thức (ko viết hàm)
public interface IUserDao {
	// chứa các hàm và các thủ tục, danh sách các user
	List<UserModel> findAll();
	// Hàm lấy một duy user:
	UserModel findById(int id);
	// Thêm user
	void insert(UserModel user);
	
	UserModel findByUsername(String username);
}
