package bazar.bs;

import bazar.pojos.User;

public class UserBs {
	public static Boolean areCredentialsRight(User user, String password) {
		Boolean rigth = false;
		if(user.getPassword().equals(MD5.getMd5(password))) {
			rigth = true;
		}
		return rigth;
	}
}
