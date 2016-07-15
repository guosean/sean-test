package com.concurrent;

import java.util.concurrent.Callable;

import com.utils.file.FileUtils;
//用十个线程计算一个数组的和
class TaskWithResult implements Callable<Integer> {
  private Integer[] ints;
  public static final String path = "D:\\test";
  public TaskWithResult(Integer[] ints) {
      this.ints = ints;
  }

  private int sumFromArray() {
      int result = 0;
      for (int a : this.ints) {
          result += a;
      }
      return result;
  }

  public Integer call() throws Exception {
	  int sumFromArray = sumFromArray();
	  String str = Thread.currentThread().toString()+":"+sumFromArray;
//	  Thread.sleep(1000*10);
	  System.out.println(str);
	  FileUtils.writeFile(str.getBytes(), path+sumFromArray+".txt");
	  return sumFromArray;
  }
}
