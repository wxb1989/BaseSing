package com.cx.sin.utils.base;

import java.io.File;


public interface UriMapper {

	void map(String alias, String path);
	
	File resolve(String path);
	
}
