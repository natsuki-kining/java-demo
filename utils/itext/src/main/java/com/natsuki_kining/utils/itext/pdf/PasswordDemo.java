package com.natsuki_kining.utils.itext.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/1/13 10:10
 **/
public class PasswordDemo {
    public static void main(String[] args) throws DocumentException, IOException {
        // 创建文件
        Document document = new Document();
        // 建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\temp\\password.pdf"));

        //用户密码
        String userPassword = "123456";
        //拥有者密码
        String ownerPassword = "hd";
        writer.setEncryption(userPassword.getBytes(), ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_128);

        // 打开文件
        document.open();

        //添加内容
        document.add(new Paragraph("password !!!!"));

        // 关闭文档
        document.close();
        // 关闭书写器
        writer.close();
    }
}
