package com.example.cms.service.utils;

import javafx.concurrent.Worker;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @author
 * @date 2018/12/13
 */
public class FileUtils {
    public static String uploadFile(MultipartFile file,String type) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath;
        String ppt="ppt";
        if (type.equals(ppt)) {
            filePath = "${program.filePath.ppt}";
        }
        else{
            filePath = "${program.filePath.report}";
        }
        String path = filePath + fileName ;
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);
        return path;
    }

    public static void downloadFile(HttpServletResponse response, String path, String filename) throws IOException {
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }

    /**
     * @param response HTTPServletResponse
     * @param files 名称 路径
     * @param zipName
     */
    public static void zipDownload(HttpServletResponse response, Map<String, String> files, String zipName){
        try{
            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(zipName, "utf-8"));

            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            BufferedOutputStream bos = new BufferedOutputStream(zos);

            for(Map.Entry<String, String> entry : files.entrySet()){
                String fileName = entry.getKey();
                String  path = entry.getValue();

                InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));;
                zos.putNextEntry(new ZipEntry(fileName));

                int len = 0;
                byte[] buf = new byte[10 * 1024];
                while( (len=bis.read(buf, 0, buf.length)) != -1){
                    bos.write(buf, 0, len);
                }
                bis.close();
                bos.flush();
            }
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
