package com.fuzhu.studentmanager;

import java.util.ArrayList;
import java.util.List;

public interface IHomeworkDAO {
	public List<String> getHomework() throws Exception;
	public boolean setHomework(String content) throws Exception;
}
