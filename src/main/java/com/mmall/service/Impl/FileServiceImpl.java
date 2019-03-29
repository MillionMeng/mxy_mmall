package com.mmall.service.Impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.utils.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
@Slf4j
public class FileServiceImpl implements IFileService{

    //private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("开始上传文件，上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件上传成功

            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上

            //上传完后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传异常",e);
            return null;
        }
        return targetFile.getName();
    }

}
