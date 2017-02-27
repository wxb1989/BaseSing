package com.cx.sin.utils.base;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.activation.MimetypesFileTypeMap;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * @author XuXu
 * @version ：2015年1月8日  上午10:30:40
 */
public class CommonUtil {

	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public final static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public final static String generateMD5(String password) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = password.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
	}
	
	/**
	 * SHA1加密
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	/**
	 * GET请求
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String readContentFromGet(String url) throws IOException {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String result = reader.readLine();

        reader.close();
        connection.disconnect();
        
        return result;
    }
	
	/**
	 * 普通post请求
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String readContentFromPost(String url,String param) throws IOException {
        StringBuffer xmlBuffer=new StringBuffer("");
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(param); 

        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        String line="";
        while ((line = reader.readLine()) != null) {
            xmlBuffer.append(line).append("\n");
        }
        reader.close();
        connection.disconnect();
        return xmlBuffer.toString();
    }
	
	
	/**
	 * post 请求
	 * @param strURL
	 * @param param
	 * @return
	 */
	public static String post(String strURL, String params) {  
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                return result;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }
	
	/**
     * 上传图片
     * 
     * @param urlStr
     * @param textMap
     * @param fileMap
     * @return
     */ 
    public static String formUpload(String urlStr, Map<String, String> textMap, 
        Map<String, String> fileMap) throws Exception{ 
        String res = ""; 
        HttpURLConnection conn = null; 
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符 
        
        URL url = new URL(urlStr); 
        conn = (HttpURLConnection) url.openConnection(); 
        conn.setConnectTimeout(5000); 
        conn.setReadTimeout(30000); 
        conn.setDoOutput(true); 
        conn.setDoInput(true); 
        conn.setUseCaches(false); 
        conn.setRequestMethod("POST"); 
        conn.setRequestProperty("Connection", "Keep-Alive"); 
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)"); 
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY); 
   
        OutputStream out = new DataOutputStream(conn.getOutputStream()); 
        // text 
        if (textMap != null) { 
            StringBuffer strBuf = new StringBuffer(); 
            Iterator iter = textMap.entrySet().iterator(); 
            while (iter.hasNext()) { 
                Map.Entry entry = (Map.Entry) iter.next(); 
                String inputName = (String) entry.getKey(); 
                String inputValue = (String) entry.getValue(); 
                if (inputValue == null) { 
                    continue; 
                } 
                strBuf.append("\r\n").append("--").append(BOUNDARY).append( 
                        "\r\n"); 
                strBuf.append("Content-Disposition: form-data; name=\"" 
                        + inputName + "\"\r\n\r\n"); 
                    strBuf.append(inputValue); 
                } 
                out.write(strBuf.toString().getBytes()); 
            } 
   
            // file 
        if (fileMap != null) { 
            Iterator iter = fileMap.entrySet().iterator(); 
            while (iter.hasNext()) { 
                Map.Entry entry = (Map.Entry) iter.next(); 
                String inputName = (String) entry.getKey(); 
                String inputValue = (String) entry.getValue(); 
                if (inputValue == null) { 
                    continue; 
                } 
                File file = new File(inputValue); 
                String filename = file.getName(); 
                String contentType = new MimetypesFileTypeMap() 
                        .getContentType(file); 
                if (filename.endsWith(".png")) { 
                    contentType = "image/png"; 
                } 
                if (contentType == null || contentType.equals("")) { 
                    contentType = "application/octet-stream"; 
                    } 
   
                    StringBuffer strBuf = new StringBuffer(); 
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append( 
                        "\r\n"); 
                strBuf.append("Content-Disposition: form-data; name=\"" 
                        + inputName + "\"; filename=\"" + filename 
                        + "\"\r\n"); 
                strBuf.append("Content-Type:" + contentType + "\r\n\r\n"); 
   
                    out.write(strBuf.toString().getBytes()); 
   
                    DataInputStream in = new DataInputStream( 
                            new FileInputStream(file)); 
                    int bytes = 0; 
                    byte[] bufferOut = new byte[1024]; 
                    while ((bytes = in.read(bufferOut)) != -1) { 
                        out.write(bufferOut, 0, bytes); 
                    } 
                    in.close(); 
                } 
            } 
   
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(); 
            out.write(endData); 
            out.flush(); 
            out.close(); 
   
            // 读取返回数据 
        StringBuffer strBuf = new StringBuffer(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader( 
                conn.getInputStream())); 
        String line = null; 
        while ((line = reader.readLine()) != null) { 
            strBuf.append(line).append("\n"); 
        } 
        res = strBuf.toString(); 
        reader.close(); 
        reader = null; 
        
        if (conn != null) { 
            conn.disconnect(); 
            conn = null; 
        }
        return res; 
    }
	
	
	/**
	 * 获取简单json值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getJsonValue(String json, String key) {
		String value = "error";
		
		JSONObject node = JSONObject.fromObject(json);
		if (node.get("errcode") == null) {
			value = node.get(key).toString();
		}
		
		return value;
	}
	
	/**
	 * 过滤特殊字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {     
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ߒ]";
		//String regEx="`~@#^&*|\\\\<>/~@#……&*——|ߒ";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}
