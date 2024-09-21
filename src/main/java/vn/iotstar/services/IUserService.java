package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	// Chức năng đăng nhập tài khoản:
	UserModel login(String username, String password);
	
	UserModel FindByUserName(String username);
	
}
