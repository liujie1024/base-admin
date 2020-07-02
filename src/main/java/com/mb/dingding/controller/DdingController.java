package com.mb.dingding.controller;

import com.alibaba.fastjson.JSONObject;
import com.mb.dingding.utils.HttpHelper;

/**
 * 钉钉相关接口信息
 */
public class DdingController {

    // *****钉钉配置生产环境**********************************************************************************
//    private static String DDING_CORP_ID = "dingcf50ebc059afe7af";
//    private static String DDING_CORP_SECRET = "U9jqNDcsORHsFIvApxGBhq678qcegLk4EjG9JiW8c5fL7uhW9cSpPFW9P5RyJ0zM";
    // ***************************************************************************************************

    // *****钉钉配置测试环境**********************************************************************************
    private static String DDING_CORP_ID = "dingbe52d365e587ac7b";
    private static String DDING_CORP_SECRET = "g1guTAOJTngXDr2T5PPmnhafzKoJ_0n8UxqMT0QV_1DVOzWXCTYdF4C89sTmpe9s";
    // ***************************************************************************************************

    /**
     * 获取AccessToken
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String url = "https://oapi.dingtalk.com/gettoken?corpid=" + DDING_CORP_ID + "&corpsecret=" + DDING_CORP_SECRET;
        JSONObject response = HttpHelper.httpGet(url);
        String accessToken = "";
        if (response.containsKey("access_token")) {
            accessToken = response.getString("access_token");
        }
        return accessToken;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getAccessToken());
        //687ce55f9f31361eb4250373f29d6e82
    }

}
