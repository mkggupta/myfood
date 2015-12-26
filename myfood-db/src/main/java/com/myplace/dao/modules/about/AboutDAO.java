package com.myfood.dao.modules.about;

import com.myfood.dao.exception.DataAccessFailedException;

public interface AboutDAO {
	public String getAboutUs() throws DataAccessFailedException;
}
