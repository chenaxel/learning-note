package com.axel.jvm.annotations;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2020/4/24
 */
public class BADLY_NAMED_CODE {
	enum colors {
		red, blue, green
	}

	static final int FORTY_TWO = 42;
	public static int NOT_A_CONSTANT = FORTY_TWO;

	protected void BADLY_NAMED_CODE() {
		return;
	}

	public void NOTcamelCASEmethodNAME() {
		return;
	}
}
