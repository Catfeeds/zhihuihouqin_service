package cn.lc.model.framework.utils;

import java.io.File;

import cn.lc.model.framework.application.SoftApplication;

/**
 * Created by 张海洋 on 2017-08-29.
 */

public class ClearAppCache {

    /**
     *  清除应用中的"data/data/com.clubber.paile下的缓存。"
     */
    public static void clearAppCache() {
        clearCache();
        cleanDatabases();
        cleanSharedPreference();
    }

    public static double getCacheSize(){
        long cacheSize = 0;
        File cacheDir = SoftApplication.getInstance().getCacheDir();
        if (cacheDir.exists()) {
            cacheSize += getFolderSize(cacheDir);
        }
        File databases = new File("/data/data/"
                + SoftApplication.getInstance().getPackageName() + "/databases");
        if (databases.exists()) {
            cacheSize += getFolderSize(databases);
        }

        File shared = new File("/data/data/"
                + SoftApplication.getInstance().getPackageName() + "/shared_prefs");
        if (shared.exists()) {
            cacheSize += getFolderSize(shared);
        }
        return cacheSize/1024/1024.0;
    }

    private static long getFolderSize(File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getFolderSize(child);
        return total;
    }

    /**
     *  清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    public static long clearCache() {
        File cacheDir = new File("/data/data/" + SoftApplication.getInstance().getPackageName() + "/cache");
        if (cacheDir.exists()) {
            deleteFile(cacheDir);
        }
        return 0;
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     */
    public static long cleanDatabases() {
        File file = new File("/data/data/"
                + SoftApplication.getInstance().getPackageName() + "/databases");
        if (file.exists()) {
            deleteFile(file);
        }
        return 0;
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     */
    public static long cleanSharedPreference() {
        File file = new File("/data/data/"
                + SoftApplication.getInstance().getPackageName() + "/shared_prefs");
        if (file.exists()) {
            deleteFile(file);
        }
        return 0;
    }

    /**
     *  删除文件或者文件夹。
     * @param file
     */
    public static void deleteFile(File file){
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files){
                    deleteFile(f);
                }
            }
        }
    }
}
