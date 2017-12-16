package cn.lc.model.easeui.init.db;

import android.content.Context;

import com.lidroid.xutils.DbUtils;


public class BaseDao {
	public DbUtils dbUtils;

	public BaseDao(Context context) {
		super();
		dbUtils = DbUtils.create(context, "man7_men.db");
		
	}

}
