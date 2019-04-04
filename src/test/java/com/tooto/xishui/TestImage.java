package com.tooto.xishui;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */

import java.io.*;

import com.alipay.api.*;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yikai.hu
 * @version $Id: Test.java, v 0.1 Aug 6, 2014 4:20:17 PM yikai.hu Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestImage {
    //private static String apiVersion    = "1.0";
    //alipay.mobile.public.multimedia.upload
    //alipay.mobile.public.multimedia.download
    @Value("${alipay.appid}")
    private String appid;
    @Value("${alipay.privateSecrete}")
    private String privateFilePath;
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    private static String apiMethodName = "alipay.offline.provider.dish.query";
    private static String media_id = "L21pZnMvVDFNeFowWG5kYlhYYUNucHJYP3Q9YW13ZiZ4c2lnPWZlY2I5ZDM5ODZmMTBiMDFiMWQ4MjhkNTA5YTU2NDg4607";
    //公用变量
    private static String format = "json";
    private static String charset = "utf-8";

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        Resource privateResource = applicationContext.getResource("classpath:sha_256_private.pem");
        Resource publicResource = applicationContext.getResource("classpath:rsa_public_key.pem");
        StringBuilder privateKey = new StringBuilder();
        StringBuilder publicKey = new StringBuilder();
        try {
            File privateKeyFile = privateResource.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(privateKeyFile));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                privateKey.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File publicResourceFile = publicResource.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(publicResourceFile));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                publicKey.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,appid,privateKey.toString(),"json","GBK",publicKey.toString(),"RSA2");
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\"2016-04-05\"" +
                "  }");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
