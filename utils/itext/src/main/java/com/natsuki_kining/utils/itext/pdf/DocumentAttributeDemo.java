package com.natsuki_kining.utils.itext.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * pdf 设置文档 属性
 *
 * @Author natsuki_kining
 * @Date 2020/1/13 9:56
 **/
public class DocumentAttributeDemo {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\temp\\DocumentAttribute.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("Some content here"));

        //设置属性
        //标题
        document.addTitle("this is a title");
        //作者
        document.addAuthor("H__D");
        //主题
        document.addSubject("this is subject");
        //关键字
        document.addKeywords("Keywords");
        //创建时间
        document.addCreationDate();
        //应用程序
        document.addCreator("hd.com");

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
