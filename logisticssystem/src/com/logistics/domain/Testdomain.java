package com.logistics.domain;
/**
 * 这是什么表
 * @author YX
 *
 */
public class Testdomain {
/**
 * test1字段说明
 */
 private String test1;
 /**
  * test2解释中文意思
  */
 private String test2;
public String getTest1() {
	return test1;
}
public void setTest1(String test1) {
	this.test1 = test1;
}
public String getTest2() {
	return test2;
}
public void setTest2(String test2) {
	this.test2 = test2;
}
@Override
public String toString() {
	return "Testdomain [test1=" + test1 + ", test2=" + test2 + "]";
}
 
}
