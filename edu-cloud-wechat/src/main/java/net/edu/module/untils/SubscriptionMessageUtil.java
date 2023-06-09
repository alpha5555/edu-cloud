package net.edu.module.untils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.*;

public class SubscriptionMessageUtil {

    /**
     * 观察者模式使用
     * @param userOpenid
     * @param tempId
     * @param wxMpTemplateList
     */
    public static void sent(String userOpenid, String tempId, List<WxMpTemplateData> wxMpTemplateList){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(tempId)
                .data(wxMpTemplateList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }


    public static void chooseMsgTemplate(int type, String userOpenid, String tempId, String... content) {

        switch (type) {
            case 1:
                sendClassOpenMsg(userOpenid, tempId, content[0]);
                break;
            case 2:
                sendHomeWorkMsg(userOpenid, tempId, content[0], content[1]);
                break;
            case 3:
                sendLessonOpenMsg(userOpenid, tempId, content[0]);
                break;
            case 4:
                sendSignSuccessMsg(userOpenid, tempId, content[0], content[1]);
                break;
            case 5:
                sendHomeworkSubmitMsg(userOpenid, tempId, content[0]);
                break;
            case 6:
                sendLessonEvaluationMsg(userOpenid, tempId, content[0], content[1]);
                break;
            case 7:
                sendExamArrangementMsg(userOpenid, tempId, content[0]);
                break;
        }
    }


    //考试安排提醒
    public static void sendExamArrangementMsg(String userOpenid, String tempId, String content) {

        /**
         * {{first.DATA}}
         * 考试科目：{{keyword1.DATA}}
         * 考试时间：{{keyword2.DATA}}
         * 考试地点：{{keyword3.DATA}}
         * 监考老师：{{keyword4.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String examName = jsonObject.getStr("examName");
        String examTime = jsonObject.getStr("examTime");
        String examPlace = jsonObject.getStr("examPlace");
        String teacher = jsonObject.getStr("teacher");


        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的孩子明天有考试安排如下：", "#000000"),
                new WxMpTemplateData("keyword1", examName),
                new WxMpTemplateData("keyword2", examTime),
                new WxMpTemplateData("keyword3", examPlace),
                new WxMpTemplateData("keyword4", teacher),
                new WxMpTemplateData("remark", "请及时到场考试，做文明考生。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    //课堂评价消息
    public static void sendLessonEvaluationMsg(String userOpenid, String tempId, String content, String userName) {

        /**
         * {{first.DATA}}
         * 学生姓名：{{keyword1.DATA}}
         * 课程名称：{{keyword2.DATA}}
         * 评价内容：{{keyword3.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        Long lessonId = jsonObject.getLong("lessonId");
        Long userId = jsonObject.getLong("userId");
        String className = jsonObject.getStr("className");

        //备注
        String evaluationContent = jsonObject.getStr("evaluationContent");
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();

        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的孩子今日表现如下:", "#000000"),
                new WxMpTemplateData("keyword1", userName),
                new WxMpTemplateData("keyword2", className),
                new WxMpTemplateData("keyword3", evaluationContent),
                new WxMpTemplateData("remark", "点击详情查看具体情况。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .url(WeChatProperties.EVALUATION_Url+"/"+lessonId+"/"+userId)
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 作业发布提醒
     */
    public static void sendHomeWorkMsg(String userOpenid, String tempId, String content, String userName) {

        /**
         *  {{first.DATA}}
         * 截止时间：{{keyword1.DATA}}
         * 作业内容：{{keyword2.DATA}}
         * 作业要求：{{keyword3.DATA}}
         * {{remark.DATA}}
         */

        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String endTime = jsonObject.getStr("endTime");
        String content1 = jsonObject.getStr("content");
        String demand=jsonObject.getStr("demand");


        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", userName+"同学，你有新的作业，请查收", "#000000"),
                new WxMpTemplateData("keyword1", endTime),
                new WxMpTemplateData("keyword2", content1),
                new WxMpTemplateData("keyword3", demand),
                new WxMpTemplateData("remark", "")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 课程发布提醒
     */
    public static void sendClassOpenMsg(String userOpenid, String tempId, String content) {
        /**
         * {{first.DATA}}
         * 课程名称：{{keyword1.DATA}}
         * 开课时间：{{keyword2.DATA}}
         * 开课地点：{{keyword3.DATA}}
         * 联系方式：{{keyword4.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;

        // 卡片详情跳转页，设置此值，当点击消息时会打开指定的页面
//        String detailUrl = "https://bing.com";

        JSONObject jsonObject = JSONUtil.parseObj(content);
        String className = jsonObject.getStr("className");
        String classTime = jsonObject.getStr("classTime");
        String location = jsonObject.getStr("location");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        String phoneNumber = "13355556666";
        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好,您报名的课程即将开课", "#000000"),
                new WxMpTemplateData("keyword1", className),
                new WxMpTemplateData("keyword2", classTime),
                new WxMpTemplateData("keyword3", location),
                new WxMpTemplateData("keyword4", "李老师" + phoneNumber),
                new WxMpTemplateData("remark", "如有疑问，请及时与我们取得联系。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 作业及时提交提醒
     */
    public static void sendHomeworkSubmitMsg(String userOpenid, String tempId, String content) {
        /**
         * {{first.DATA}}
         * 截止时间：{{keyword1.DATA}}
         * 提交方式：{{keyword2.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String deadline = jsonObject.getStr("deadline");
        String submitMethod = jsonObject.getStr("submitMethod");
        String remark = jsonObject.getStr("remark");


        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，本次课程的课后作业还有24小时截止，请您及时提交", "#000000"),
                new WxMpTemplateData("keyword1", deadline),
                new WxMpTemplateData("keyword2", submitMethod),
                new WxMpTemplateData("remark", remark)
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }

    /**
     * 上课签到成功提醒
     */
    public static void sendSignSuccessMsg(String userOpenid, String tempId, String content, String userName) {

        /**
         * {{first.DATA}}
         * 学生姓名：{{keyword1.DATA}}
         * 上课班级：{{keyword2.DATA}}
         * 课程内容：{{keyword3.DATA}}
         * 上课时间：{{keyword4.DATA}}
         * 上课地点：{{keyword5.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String lessonName = jsonObject.getStr("lessonName");
        String lessonContent = jsonObject.getStr("lessonContent");
        String lessonTime = jsonObject.getStr("lessonTime");
        String lessonLocation = jsonObject.getStr("lessonLocation");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，您的孩子已完成签到", "#000000"),
                new WxMpTemplateData("keyword1", userName),
                new WxMpTemplateData("keyword2", lessonName),
                new WxMpTemplateData("keyword3", lessonContent),
                new WxMpTemplateData("keyword4", lessonTime),
                new WxMpTemplateData("keyword5", lessonLocation),
                new WxMpTemplateData("remark", "请在下课后及时接走孩子。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }


    /**
     * 上课前提醒
     */
    public static void sendLessonOpenMsg(String userOpenid, String tempId, String content) {

        /**
         * {{first.DATA}}
         * 课程名称：{{keyword1.DATA}}
         * 上课时间：{{keyword2.DATA}}
         * 上课地点：{{keyword3.DATA}}
         * 联系电话：{{keyword4.DATA}}
         * {{remark.DATA}}
         */
        String OrderMsgTemplateId = tempId;


        JSONObject jsonObject = JSONUtil.parseObj(content);
        String lessonName = jsonObject.getStr("lessonName");
        String lessonTime = jsonObject.getStr("lessonTime");
        String lessonLocation = jsonObject.getStr("lessonLocation");

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(WeChatProperties.APP_ID);
        wxStorage.setSecret(WeChatProperties.APP_SECRET);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您好，请带您的孩子准时参加如下课程：", "#000000"),
                new WxMpTemplateData("keyword1", lessonName),
                new WxMpTemplateData("keyword2", lessonTime),
                new WxMpTemplateData("keyword3", lessonLocation),
                new WxMpTemplateData("keyword4", "1335555666"),
                new WxMpTemplateData("remark", "如需请假，请及时联系教务老师。")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }
    }
//
//    /**
//     * 消息群发
//     */
    public static String GroupMessage(String token, String content, String person) {
        System.out.println(content);
        // 接口地址
        String sendMsgApi = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + token;
        //整体参数map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //相关map
        Map<String, Object> dataMap1 = new HashMap<String, Object>();
        Map<String, String> dataMap2 = new HashMap<String, String>();
        if (person.equals("3")) {
            System.out.println("群发");
            dataMap1.put("is_to_all", true);//用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
        } else {
            dataMap1.put("is_to_all", false);
        }
        dataMap1.put("tag_id", 1);//群发到的标签的tag_id，参见用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
        dataMap2.put("content", content);//要推送的内容
        paramMap.put("filter", dataMap1);//用于设定图文消息的接收者
        paramMap.put("text", dataMap2);//文本内容
        paramMap.put("msgtype", "text");//群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
        String back = HttpUtil.post(sendMsgApi, paramMap);
        System.out.println("back:" + back);
        return back;
    }

}



