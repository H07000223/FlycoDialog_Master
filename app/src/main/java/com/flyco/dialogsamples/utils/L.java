package com.flyco.dialogsamples.utils;

import android.util.Log;

/**
 * 一个具有开关的日志工具,代替系统的Log类
 */
public class L
{
	private static boolean isShow = true;

	/** 是否显示Log日志 */
	public static void openOrClose(boolean isShow)
	{
		L.isShow = isShow;
	}

	public static void v(String tag, String msg)
	{
		if (isShow)
			Log.v(tag, msg);
	}

	public static void d(String tag, String msg)
	{
		if (isShow)
			Log.d(tag, msg);
	}

	public static void i(String tag, String msg)
	{
		if (isShow)
			Log.i(tag, msg);
	}

	public static void w(String tag, String msg)
	{
		if (isShow)
			Log.w(tag, msg);
	}

	public static void e(String tag, String msg)
	{
		if (isShow)
			Log.e(tag, msg);
	}
}
