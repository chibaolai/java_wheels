package com.xiangwang.otp;

/**
 * 测试动态令牌
 * @author chibaolai
 */
public class OTPAuthUtilTester {
	public static void main(String[] args) {
		// 创建密钥secret信息
		// 测试时须将此密钥保存下来
		String secret = "WJ5E332WQQQ6HHUPM2JELL2ZCFNK56MQLIYD7RY4K5NNTHO6TURA"; // OTPAuthUtil.generateSecret(64);
		// 验证账户（可填写自己的名字，不要用中文）
		String account = "Huahua";
		// 创建协议URI
		String protocaluri = OTPAuthUtil.generateTotpURI(account, secret);
		System.out.println(protocaluri);
		// 验证生成的动态口令是否正确
		String code = "873432";
		System.out.println("动态口令是否正确：" + OTPAuthUtil.verify(secret, code));

		long currentTime = System.currentTimeMillis() / 1_000L;
		String steps = "0";
		try {
			// 获取动态密码计算的随机数
			long t = currentTime / 30;
			System.out.println(t);
			steps = Long.toHexString(t).toUpperCase();
			System.out.println(steps);
			// 不足16位前面补0
			while (steps.length() < 16) {
				steps = "0" + steps;
			}
		} catch (final Exception e) {
			throw new RuntimeException();
		}
		System.out.println(steps);
	}
}
