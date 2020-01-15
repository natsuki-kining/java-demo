package com.natsuki_kining.utils.itext.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * pdf 添加图片
 *
 * @Author natsuki_kining
 * @Date 2020/1/13 9:58
 **/
public class ImageDemo {

    public static void main(String[] args) throws DocumentException, IOException {

        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\temp\\image.pdf"));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("HD content here"));

        //图片1
        Image image1 = Image.getInstance("E:\\temp\\image.jpg");
        //设置图片位置的x轴和y周
        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        //图片2
        Image image2 = Image.getInstance(new URL("https://n.sinaimg.cn/tech/transform/551/w218h333/20200113/f902-imztzhn2471291.gif"));
        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }
}
