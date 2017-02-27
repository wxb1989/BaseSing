package com.cx.sin.utils.base;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;
@Component("uriMapper")
public class UriMapperImpl implements UriMapper {

	private final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
	
	@Override
	public void map(String alias, String path) {
		map.put(alias, path);
	}
	
	@Override
	public File resolve(String path) {
		for (String alias : map.keySet()) {
			if (alias.equals(path)) {
				path = map.get(alias);
				if (path == null) return null;
				return new File(path);
			}
			String prefix = alias.charAt(alias.length() - 1) == '/' ? alias : alias + '/';
			if (path.startsWith(prefix)) {
				String dir = map.get(alias);
				path = path.substring(prefix.length());
				return new File(dir + '/' + path);
			}
		}
		return null;
	}
	
}
