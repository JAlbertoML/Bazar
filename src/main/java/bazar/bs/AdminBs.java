package bazar.bs;

import bazar.dao.AdminDao;
import bazar.pojos.Admin;

public class AdminBs {
	public static Admin getAdminByUsername(String username) {
		return AdminDao.getAdminByUsername(username);
	}
}
