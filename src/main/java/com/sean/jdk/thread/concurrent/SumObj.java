package com.sean.jdk.thread.concurrent;

public class SumObj {
	
	private int x=0;
	private int y=0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSum() {
		return sum;
	}
	public void setSum() {
		this.sum = x+y;
	}
	private int sum=0;
	
	public String toString(){
		return "x:"+x+",y:"+y+",sum:"+sum;
	}

}
