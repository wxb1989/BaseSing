package com.cx.sin.dao.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.cx.sin.bean.base.QueryResult;

public interface DAO<T> {
	/**
	 * 获取记录总数
	 * @param entityClass 实体类
	 * @return
	 */
	public long getCount();
	/**
	 * 清除一级缓存的数据
	 */
	public void clear();
	
	/**
	 * 同步到数据库
	 */
	public void flush();
	/**
	 * 保存实体
	 * @param entity 实体id
	 */
	public void save(Object entity);
	/**
	 * 更新实体
	 * @param entity 实体id
	 */
	public void update(Object entity);
	/**
	 * 删除实体
	 * @param entityClass 实体类
	 * @param entityids 实体id数组
	 */
	public void delete(Serializable ... entityids);
	/**
	 * 获取实体
	 * @param <T>
	 * @param entityClass 实体类
	 * @param entityId 实体id
	 * @return
	 */
	public T find(Serializable entityId);
	
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 需要获取的记录数
	 * @param sql where之前的hql语句
	 * @param wherejpql where语句
	 * @param queryParams 参数
	 * @param orderby 排序
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,String sql, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 获取分页数据
	 * @param <T>
	 * @param entityClass 实体类
	 * @param firstindex 开始索引
	 * @param maxresult 需要获取的记录数
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult);
	
	public QueryResult<T> getScrollData();
}
