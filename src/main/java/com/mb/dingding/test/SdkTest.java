package com.mb.dingding.test;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import java.util.Arrays;

/**
 * 钉钉机器人测试
 */
public class SdkTest {

    private static String dingServerUrl = "https://oapi.dingtalk.com/robot/send?access_token=fbed777ec29d5554f59014c9a440b717cb1448d8f5660fda123e4c124e680de9";

    private static OapiRobotSendResponse sendDingInfo(String type) throws ApiException {
        OapiRobotSendResponse response = null;
        switch (type) {
            case "text":
                response = sendInfo4Text();
                break;
            case "link":
                response = sendInfo4Link();
                break;
            case "markdown":
                response = sendInfo4Markdown();
                break;
            default:
                System.out.println("钉钉机器人开发，类型不正确...");
                break;
        }
        return response;
    }

    private static OapiRobotSendResponse sendInfo4Text() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(dingServerUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息1111");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("13585770542"));
//       at.setIsAtAll(true);// isAtAll类型如果不为Boolean，请升级至最新SDK
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        return response;
    }

    private static OapiRobotSendResponse sendInfo4Link() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(dingServerUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("http://10.100.24.134:8080/base-admin/");
        link.setPicUrl("");
        link.setTitle("测试时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
        request.setLink(link);
        OapiRobotSendResponse response = client.execute(request);
        return response;
    }

    private static OapiRobotSendResponse sendInfo4Markdown() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(dingServerUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("测试杭州天气");
        markdown.setText("#### 杭州天气 @13585770542\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("13585770542"));
        request.setAt(at);

        OapiRobotSendResponse response = client.execute(request);
        return response;
    }

    public static void main(String[] args) throws ApiException {
//        System.out.println(sendDingInfo("text").isSuccess());
//        System.out.println(sendDingInfo("link").isSuccess());
        System.out.println(sendDingInfo("markdown").isSuccess());
    }

}
