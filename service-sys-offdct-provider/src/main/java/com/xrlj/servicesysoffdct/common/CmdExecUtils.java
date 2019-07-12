package com.xrlj.servicesysoffdct.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public final class CmdExecUtils {

	public static Logger log = LoggerFactory.getLogger(CmdExecUtils.class);
	
    private CmdExecUtils() {
    }

    public static boolean execCommond(String... args) {
        boolean flg = true;
        Runtime run = Runtime.getRuntime();
        try {
            Process p;
            if (args != null && args.length == 1) {
                p = run.exec(args[0]);
            } else {
                p = run.exec(args);
            }
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
            	log.info("输出行内容：{}", lineStr);
            }
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1) {
                	log.info("==================================命令执行失败!");
                    flg = false;
                }
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            flg = false;
        }
        return flg;
    }

    /**
     * 对可执行文件赋予权限。
     * @param exeFilePath
     * @return
     */
    public static boolean enableExe(String exeFilePath) {
        String cmd = "chmod a+x ".concat(exeFilePath);
        return CmdExecUtils.execCommond(cmd);
    }
    
    /**
     * 文件可读权限。
     * @param exeFilePath
     * @return
     */
    public static boolean chownR(String exeFilePath) {
		String cmd = " chown -R root:root ".concat(exeFilePath);
		return execCommond(cmd);
	}
}
