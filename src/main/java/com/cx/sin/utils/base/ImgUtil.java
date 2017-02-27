package com.cx.sin.utils.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.pool.impl.GenericKeyedObjectPool.Config;
import org.springframework.web.multipart.MultipartFile;


public class ImgUtil {
	/**
	 * 上传图片(jpg,png)
	 *            图片前缀(MAC_000001:编号) 例如 MAC_000001_20150506.jpg
	 */
	public static String upImg(MultipartFile imgFile, HttpServletRequest request, String imgPrefix) throws IllegalStateException,
			IOException {
		List<String> fileTypes = new ArrayList<String>(); // 允许上传文件格式
		fileTypes.add("jpg");
		fileTypes.add("png");
		String fileName = "";
		if (!(imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))) {
			fileName = imgFile.getOriginalFilename();
			// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			ext = ext.toLowerCase(); // 对扩展名进行小写转换
			File filepath = null;
			if (fileTypes.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
				fileName = reName(imgPrefix, ".".concat(ext));
				mkr_folder(ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator + "temp"
						+ File.separator, request);
				filepath = new File(ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator
						+ "temp" + File.separator); // 文件临时目录
				if (!filepath.exists()) {
					filepath.mkdir(); // 如果没有就创建文件夹
				}
				File file2 = new File(filepath, fileName);
				imgFile.transferTo(file2); // 保存上传的文件
				return fileName;
			} else {
				return "error1"; // 上传图片格式错误
			}
		}
		return "error2"; // 未选择正确图片 ,无法获取图片文件;
	}

	/**
	 * 根据路径自动生成以picture开始的文件夹 例如 ../picture/illnesscase/test 将 自动生成
	 * picture和illnesscase 和test文件夹 注意:路径里面必须以picture开头, 不限目录层数
	 * 
	 * @param newPath
	 * @param request
	 */
	public static void mkr_folder(String newPath, HttpServletRequest request) {
		String fileNa[] = newPath.substring(newPath.indexOf("picture"), newPath.lastIndexOf(File.separator)).split("\\" + File.separator); // 文件夹
		String sysPath = ConfigUtils.getSysConfig("sys.czsypicture") + File.separator;
		for (int i = 0; i < fileNa.length; i++) {
			File folder = new File(sysPath.concat(File.separator), fileNa[i]);
			if (!folder.exists()) {
				folder.mkdir();
			}
			sysPath = sysPath.concat(fileNa[i]).concat(File.separator);
		}
	}

	/**
	 * @author Administrator LiuWang
	 * @category 文件重命名 传入 前缀 , 后缀
	 * @param name
	 *            重命名格式为:name+年月日时分秒 +suffix 例如:illnesscase20150423130710.jpg
	 */
	public static String reName(String name, String suffix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = name + "_" + sdf.format(new Date()) + suffix;
		return str;
	}

	/***
	 * @param fileName
	 *            文件名
	 * @param newPath
	 *            移动到的全路径+文件名如(../../../MCA_0000001_20150213024.jpg)
	 * @param request
	 * @throws IOException
	 */

	public static void copeImg(String fileName, String newPath, HttpServletRequest request) throws IOException {
		int byteread = 0;
		String allpath = ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator + "temp"
				+ File.separator + fileName; // 路径+文件名
		File oldfile = new File(allpath);
		if (oldfile.exists()) {
			// 文件存在时
			BufferedInputStream bf = new BufferedInputStream(new FileInputStream(oldfile));

			/************* 是否存在 ../picture/illnesscase/ 如果没有就创建文件夹 开始 **************/
			mkr_folder(newPath, request);
			/************* 是否存在 ../picture/illnesscase/ 如果没有就创建文件夹 结束 **************/

			// 读入原文件
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(newPath));
			byte[] buffer = new byte[1444];
			while ((byteread = bf.read(buffer)) != -1) {
				bs.write(buffer, 0, byteread);
			}
			bs.close();
			bf.close();

			/*********************** 移动成功后,删除原文件 *******************************/
			//oldfile.delete();

		}
	}

	/***
	 * @param fileName
	 *            文件名
	 * @param newPath
	 *            移动到的全路径+文件名如(../../../MCA_0000001_20150213024.jpg)
	 * @param request
	 * @throws IOException
	 */

	public static void moveImg2(String fileName, String newPath, HttpServletRequest request) throws IOException {
		int byteread = 0;
		String allpath = ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator
				+ "illnesscase" + File.separator + fileName; // 路径+文件名
		File oldfile = new File(allpath);
		if (oldfile.exists()) {
			// 文件存在时
			BufferedInputStream bf = new BufferedInputStream(new FileInputStream(oldfile));

			/************* 是否存在 ../picture/illnesscase/ 如果没有就创建文件夹 开始 **************/
			mkr_folder(newPath, request);
			/************* 是否存在 ../picture/illnesscase/ 如果没有就创建文件夹 结束 **************/

			// 读入原文件
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(newPath));
			byte[] buffer = new byte[1444];
			while ((byteread = bf.read(buffer)) != -1) {
				bs.write(buffer, 0, byteread);
			}
			bs.close();
			bf.close();

			/*********************** 移动成功后,删除原文件 *******************************/
			oldfile.delete();

		}
	}

	// 图片路径 截取工具 截取为 XX.jpg
	public static String cutImgPath(String medicinepict) {
		if (medicinepict != null && medicinepict.length() > 0) {
			int start = medicinepict.lastIndexOf("/") + 1;
			int end = medicinepict.length();
			medicinepict = medicinepict.substring(start, end);
		}
		return medicinepict;
	}

	/**
	 * 从字符串中截取第一个 含有src="././*.jpg" 或者其他图片格式的图片路径 用于:从百度编辑器的内容里面获取第一张图片名称;
	 * 
	 * @return
	 */
	public static String cutFirstSrcImg(String s) {
		String regex;
		regex = "src=\"(.*?)\"";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			return ma.group();
		}
		return null;
	}
	public static  String cutFirstSrcImgName(String imgSrc){
		int star = imgSrc.lastIndexOf("ueditor/jsp");//  http://    目录地址 ：/ueditor/jsp/upload/image/ 
		int end =imgSrc.lastIndexOf("\"");
		String src = "";
		if(star >= 0 ){
			src = imgSrc.substring(star, end);
		}
		return src ;
	}
	
	/**
	 * 从富文本编辑器内容中获取图片src 的集合;
	 */
	public static List<String> cutFirstSrcImgList(String s) {
		List<String> list =new ArrayList<String>();
		String regex;
		regex = "src=\"(.*?)\"";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	public static  List<String>  cutFirstSrcImgListName(List<String> list){
		List<String> list_new =new ArrayList<String>();
		for(int i =0; i <list.size(); i++){
			String imgSrc = list.get(i);
			int star = imgSrc.lastIndexOf("ueditor/jsp");//  http://    目录地址 ：/ueditor/jsp/upload/image/ 
			int end =imgSrc.lastIndexOf("\"");
			String src = "";
			if(star >= 0 ){
				src = imgSrc.substring(star, end);
				list_new.add(src);
			}
		}
		return list_new;
	
	}
	
	
	/**
	 * 从富文本编辑器内容中获取文件href 的集合;
	 */
	public static List<String> cutFirstHrefList(String s) {
		List<String> list =new ArrayList<String>();
		String regex;
		regex = "href=\"(.*?)\"";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}
	
	public static  List<String>  cutFirstHrefListName(List<String> list){
		List<String> list_new =new ArrayList<String>();
		for(int i =0; i <list.size(); i++){
			String href = list.get(i);
			int star = href.lastIndexOf("ueditor/jsp");//  http://    目录地址 ：/ueditor/jsp/upload/image/ 
			int end =href.lastIndexOf("\"");
			String src = "";
			if(star >= 0 ){
				src = href.substring(star, end);
				list_new.add(src);
			}
		}
		return list_new;
	
	}
	
	
	
	
	public static void moveImg(String fileName, String newPath, HttpServletRequest request) throws IOException {
		int byteread = 0;
		String allpath = ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator + "temp"
				+ File.separator + fileName; // 路径+文件名
		File oldfile = new File(allpath);
		if (oldfile.exists()) {
			// 文件存在时
			BufferedInputStream bf = new BufferedInputStream(new FileInputStream(oldfile));
			/************* 是否存在 ../picture/illnesscase/ 如果没有就创建文件夹 开始 **************/
			mkr_folder(newPath, request);
			// 读入原文件
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(newPath));
			byte[] buffer = new byte[1444];
			while ((byteread = bf.read(buffer)) != -1) {
				bs.write(buffer, 0, byteread);
			}
			bs.close();
			bf.close();

			/*************复制成功后,删除原文件  就变成移动了*****************/
			oldfile.delete();

		}
	}

	
	/**
	 * 将图片复制到temp 临时图片目录中去
	 * 
	 * @throws IOException
	 */
	private static void copeImgToTemp(String medicinepict, String newPath, HttpServletRequest request) throws IOException {
		int byteread = 0;
		String allpath = ConfigUtils.getSysConfig("sys.czsypicture") + File.separator + "picture" + File.separator
				+ "illnesscase" + File.separator + medicinepict; // 路径+文件名
		File oldfile = new File(allpath);
		if (oldfile.exists()) {
			// 文件存在时
			BufferedInputStream bf = new BufferedInputStream(new FileInputStream(oldfile));
			mkr_folder(newPath, request);
			// 读入原文件
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(newPath));
			byte[] buffer = new byte[1444];
			while ((byteread = bf.read(buffer)) != -1) {
				bs.write(buffer, 0, byteread);
			}
			bs.close();
			bf.close();
			// oldfile.delete(); // 复制和移动的区别在此 ,删除原文件为移动,否则为复制
	}}
}
