package vn.iotstar.services.impl;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoIplm;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;

public class userService implements IUserService
{
	// Lấy toàn bộ hàm trong tầng user
	IUserDao userdao= new UserDaoIplm();

	@Override
	public UserModel login(String username, String password) {
		UserModel user=this.FindByUserName(username);
		if(user!=null && password.equals(user.getPassword()))
		{
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		// TODO Auto-generated method stub
		return userdao.findByUsername(username);
	}
	
}
