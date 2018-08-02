package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestMD5 {
	@Test
	public void test1() {
		String str1 = DigestUtils.md5Hex("abc");
		String str2 = DigestUtils.md5Hex("abc");
		System.out.println(str1);
		System.out.println(str2);
//		cdaf5c8b7cf3b5445b4942dc6a90cd3e
	}
	@Test
	public void test2() throws FileNotFoundException, IOException {
		String str1 = DigestUtils.md5Hex(new FileInputStream("pom.xml"));
		String str2 = DigestUtils.md5Hex(new FileInputStream("pom.xml"));
		System.out.println(str1);
		System.out.println(str2);
	}
	@Test
	public void test3() {
		String pwd = "123456";
		String salt = "你喜欢变成吗";
		String str = DigestUtils.md5Hex(pwd+salt);
		System.out.println(str);
	}
	
}
