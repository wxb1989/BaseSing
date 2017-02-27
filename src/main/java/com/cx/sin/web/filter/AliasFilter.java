package com.cx.sin.web.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cx.sin.utils.base.UriMapper;

@Component(value = "aliasResolverBean")
public class AliasFilter implements Filter {

	
	
	private final Map<String, String> contentTypes = new HashMap<String, String>();
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private UriMapper uriMapper;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		@SuppressWarnings("unchecked")
		Enumeration<String> aliases = filterConfig.getInitParameterNames();
		uriMapper = (UriMapper) WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean("uriMapper");
		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();
			String path = filterConfig.getInitParameter(alias);
			uriMapper.map(alias, path);
		}
		lock.writeLock().lock();
		contentTypes.put("jpg", "image/jpeg");
		contentTypes.put("jpeg", "image/jpeg");
		contentTypes.put("png", "image/png");
		contentTypes.put("gif", "image/gif");
		contentTypes.put("bmp", "image/x-bmp");
		lock.writeLock().unlock();
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String uri = httpServletRequest.getRequestURI();
		lock.readLock().lock();
		int index = uri.lastIndexOf('.');
		if (index != -1) {
			String ext = uri.substring(index + 1);
			String contentType = contentTypes.get(ext);
			if (contentType != null) {
				response.setContentType(contentType);
			}
		} 
		lock.readLock().unlock();
		File resource = uriMapper.resolve(uri);
		if (resource != null && resource.exists()) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			calendar.add(Calendar.MONTH, 1);
			DateFormat format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
			httpServletResponse.setHeader("Expires", format.format(calendar.getTime()));
			new FileInputStream(resource)
				.getChannel()
				.transferTo(0, resource.length(), Channels.newChannel(response.getOutputStream()));
			return;
		}
		chain.doFilter(request, response);
	}
	
}
