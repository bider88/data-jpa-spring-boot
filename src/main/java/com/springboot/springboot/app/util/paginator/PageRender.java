/**
 * 
 */
package com.springboot.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * @author bider
 *
 */
public class PageRender<T> {
		
	private String url;
	private Page<T> page;
	
	private int totalPages;
	
	private int numberOfElementsPerPage;
	
	private int currentPage;
	
	private List<PageItem> pages;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		
		numberOfElementsPerPage = page.getSize();
		totalPages = page.getTotalPages();
		currentPage = page.getNumber() + 1;
		
		int from, until;
		until = numberOfElementsPerPage;
		if (totalPages <= numberOfElementsPerPage) {
			from = 1;
			until = totalPages;
		} else if (currentPage <= numberOfElementsPerPage / 2) {
			from = 1;
		} else if (currentPage >= totalPages - numberOfElementsPerPage / 2) {
			from = totalPages - numberOfElementsPerPage + 1;
		} else {
			from = currentPage - numberOfElementsPerPage / 2;
		}
		
		for (int i = 0; i < until; i++) {
			pages.add(new PageItem(from, currentPage == from + 1));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
