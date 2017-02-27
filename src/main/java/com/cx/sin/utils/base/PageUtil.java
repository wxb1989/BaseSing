package com.cx.sin.utils.base;

import java.util.ArrayList;
import java.util.List;

import com.cx.sin.bean.base.PageView;

/**
 * 分页工具类
 * @author yinjf
 *
 */
public class PageUtil<E> {
	
	public static <E> PageUtil<E> getInstance(){
		return new PageUtil<E>();
	}
	
	public void paging(List<E> list,PageView<E> pageView){
		
		if (list != null && list.size() > 0) {
			List<E> newList = new ArrayList<E>();
			int firstResult = pageView.getFirstResult();
			int maxResult = pageView.getMaxresult();
			for (int i = firstResult; i < firstResult + maxResult; i++) {
				if (i < list.size()) {
					newList.add(list.get(i));
				}
			}
			
			pageView.setRecords(newList);
			pageView.setTotalrecord(list.size());
		}
	}
}
