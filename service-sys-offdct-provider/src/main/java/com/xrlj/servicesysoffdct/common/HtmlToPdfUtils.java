package com.xrlj.servicesysoffdct.common;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class HtmlToPdfUtils {

	public static Logger log = LoggerFactory.getLogger(CmdExecUtils.class);

	private HtmlToPdfUtils() {
	}

	private static HtmlToPdfUtils htmltopdfUtils;

	private static String tempDir;

	public static HtmlToPdfUtils newInstance(String userDir) {
		if (htmltopdfUtils == null) {
			htmltopdfUtils = new HtmlToPdfUtils();
		}
		tempDir = createTempDir(userDir);
		return htmltopdfUtils;
	}

	/**
	 * 创建临时文件夹。
	 * 
	 * @param userDir
	 *            一般是。@Value("${user.dir}")
	 * @return 临时文件夹temp所在路径
	 */
	private static String createTempDir(String userDir) {
		// 创建临时文件夹
		// URI uri = URI.create(userDir);
		String tempDirPath = userDir.concat(File.separator).concat("temp");
		File file = new File(tempDirPath);
		if (!file.exists()) {
			file.mkdir();
			log.info(">>>成功创建临时文件夹temp：{}", tempDirPath);
		}

		return tempDirPath;
	}

	/**
	 * 获取wkhtmltopdf可执行文件。在linux系统下有效
	 *
	 * @return
	 */
	@Deprecated
	private String getWkhtmltopdfPathOnLinux() {
		try {
			// 拷贝wkhtmltopdf可执行文件到临时目录temp中
			String wkhtmltopdfPath = tempDir.concat(File.separator).concat("wkhtmltopdf");
			File wkhtmltopdf = new File(wkhtmltopdfPath);
			if (!wkhtmltopdf.exists()) {
				wkhtmltopdf.createNewFile();
				InputStream inputStream = HtmlToPdfUtils.class.getClassLoader().getResourceAsStream("bin/wkhtmltopdf");
				FileUtils.copyToFile(inputStream, wkhtmltopdf);
				CmdExecUtils.enableExe(wkhtmltopdfPath);

				File fontsFile = new File("/usr/share/fonts/");
				if (!fontsFile.exists()) {
					CmdExecUtils.execCommond("mkdir /usr/share/fonts/");
				}
			}

			File fontFile = new File(tempDir.concat(File.separator).concat("simsun.ttc"));
			if (!fontFile.exists()) {
				fontFile.createNewFile();

				InputStream inputStream1 = HtmlToPdfUtils.class.getClassLoader().getResourceAsStream("bin/simsun.ttc");
				FileUtils.copyToFile(inputStream1, fontFile);
				String script = "mv " + fontFile.getAbsolutePath() + " /usr/share/fonts/";
				System.out.println(">>>mv script:" + script);
				CmdExecUtils.execCommond(script);

				CmdExecUtils.chownR("/usr/share/fonts/simsun.ttc");
			}

			return wkhtmltopdfPath;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 获取临时文件夹路径
	 * 
	 * @return
	 */
	public String getTempDir() {
		return tempDir;
	}

	/**
	 * wkhtmltopdf安装在：C:\\Program Files\\wkhtmltopdf\\bin
	 * 
	 * @return
	 */
	private String getWkhtmltopdfPathOnWindows() {
//		return "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe"; //改成自己的安装路径
		return "c:\\Software\\wkhtmltox\\bin\\wkhtmltopdf.exe"; // 改成自己的安装路径

	}

	/**
	 * 创建pdf文件。
	 * 
	 * @param args
	 * @return
	 */
	private boolean createPdf(String... args) {		
		return CmdExecUtils.execCommond(args);
	}
}
