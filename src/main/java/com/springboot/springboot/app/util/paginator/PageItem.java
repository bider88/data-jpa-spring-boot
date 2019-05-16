/**
 * 
 */
package com.springboot.springboot.app.util.paginator;

/**
 * @author bider
 *
 */
public class PageItem {
	
	private int number;
	private boolean current;
	
	public int getNumber() {
		return number;
	}

	public boolean isCurrent() {
		return current;
	}

	public PageItem(int number, boolean current) {
		this.number = number;
		this.current = current;
	}
	
}
