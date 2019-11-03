package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.service.FileService;
import org.apache.tika.mime.MimeTypeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author masonluo
 * @date 2019/10/30 11:39 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FasizheApiApplication.class)
public class TestFTP {

    private String bufPath = "/Users/belle/Desktop/buffer";

    private String imgUrl = "http://a2.att.hudong.com/71/74/19300000345731134717743589922.jpg";

    private String weChatAvatarUrl = "https://wx.qlogo.cn/mmopen/vi_32/8cACjzfp0eUNT7HCPFKJfVMjSjF58omrH6KeXTBecMlicsZaibHiaOYL25Lo00LkUfasAib4JGB4E5RYxNoJDErBSg/132";

    @Autowired
    FileService fileService;
    @Test
    public void test(){
        String url = fileService.saveAndGetUrl(new File("/Users/belle/Desktop/lunbo/img4.png"), "avatar", false);
        fileService.delete("img4.png", "avatar");
        System.out.println(url);
    }

    @Test
    public void downloadAndSave() throws IOException, MimeTypeException {
//        URL url = null;
//        URLConnection connection;
//        try{
//            url = new URL(weChatAvatarUrl);
//            connection = url.openConnection();
//            String contentType = connection.getContentType();
//            MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//            MimeType type = allTypes.forName(contentType);
//            String fileName = UUID.randomUUID().toString() + type.getExtension();
//            InputStream inputStream = connection.getInputStream();
//            byte[] buffer = new byte[1024];
//            int count = 0;
//            FileOutputStream fileOutputStream = new FileOutputStream(bufPath + File.separator + fileName);
//            while((count = inputStream.read(buffer)) != -1){
//                fileOutputStream.write(buffer, 0, count);
//            }
//        }catch (IOException | MimeTypeException e){
//            e.printStackTrace();
//        }
        fileService.saveToBuffer(weChatAvatarUrl);
    }
}
