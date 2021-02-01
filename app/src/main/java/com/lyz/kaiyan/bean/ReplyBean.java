package com.lyz.kaiyan.bean;

/**
 * @author liyanze
 * @create 2021/02/01
 * @Describe
 */
public class ReplyBean {

    /**
     * type : reply
     * data : {"dataType":"ReplyBeanForClient","id":1356087629384253440,"videoId":233859,"videoTitle":"泰国广告：面对PUA，你有权说不","parentReplyId":0,"rootReplyId":1356087629384253440,"sequence":3,"message":"今日份鸡血","replyStatus":"PUBLISHED","createTime":1612151460000,"user":{"uid":301331076,"nickname":"大肆","avatar":"http://img.kaiyanapp.com/d6255f690c2bbc9acb505541698bd4e4.png","userType":"NORMAL","ifPgc":false,"description":null,"area":null,"gender":null,"registDate":1448645369000,"releaseDate":null,"cover":null,"actionUrl":"eyepetizer://pgc/detail/301331076/?title=%E5%A4%A7%E8%82%86&userType=NORMAL&tabIndex=0","followed":false,"limitVideoOpen":false,"library":"BLOCK","birthday":null,"country":null,"city":null,"university":null,"job":null,"expert":false},"likeCount":0,"liked":false,"hot":false,"userType":null,"type":"video","actionUrl":null,"imageUrl":null,"ugcVideoId":null,"parentReply":null,"showParentReply":true,"showConversationButton":false,"ugcVideoUrl":null,"cover":null,"userBlocked":false,"sid":"1356087629384253440"}
     * trackingData : null
     * tag : null
     * id : 0
     * adIndex : -1
     */

    public String type;
    public DataBean data;
    public Object trackingData;
    public Object tag;
    public int id;
    public int adIndex;

    public static class DataBean {
        /**
         * dataType : ReplyBeanForClient
         * id : 1356087629384253440
         * videoId : 233859
         * videoTitle : 泰国广告：面对PUA，你有权说不
         * parentReplyId : 0
         * rootReplyId : 1356087629384253440
         * sequence : 3
         * message : 今日份鸡血
         * replyStatus : PUBLISHED
         * createTime : 1612151460000
         * user : {"uid":301331076,"nickname":"大肆","avatar":"http://img.kaiyanapp.com/d6255f690c2bbc9acb505541698bd4e4.png","userType":"NORMAL","ifPgc":false,"description":null,"area":null,"gender":null,"registDate":1448645369000,"releaseDate":null,"cover":null,"actionUrl":"eyepetizer://pgc/detail/301331076/?title=%E5%A4%A7%E8%82%86&userType=NORMAL&tabIndex=0","followed":false,"limitVideoOpen":false,"library":"BLOCK","birthday":null,"country":null,"city":null,"university":null,"job":null,"expert":false}
         * likeCount : 0
         * liked : false
         * hot : false
         * userType : null
         * type : video
         * actionUrl : null
         * imageUrl : null
         * ugcVideoId : null
         * parentReply : null
         * showParentReply : true
         * showConversationButton : false
         * ugcVideoUrl : null
         * cover : null
         * userBlocked : false
         * sid : 1356087629384253440
         */

        public String dataType;
        public long id;
        public int videoId;
        public String videoTitle;
        public int parentReplyId;
        public long rootReplyId;
        public int sequence;
        public String message;
        public String replyStatus;
        public long createTime;
        public UserBean user;
        public int likeCount;
        public boolean liked;
        public boolean hot;
        public Object userType;
        public String type;
        public Object actionUrl;
        public Object imageUrl;
        public Object ugcVideoId;
        public Object parentReply;
        public boolean showParentReply;
        public boolean showConversationButton;
        public Object ugcVideoUrl;
        public Object cover;
        public boolean userBlocked;
        public String sid;

        public static class UserBean {
            /**
             * uid : 301331076
             * nickname : 大肆
             * avatar : http://img.kaiyanapp.com/d6255f690c2bbc9acb505541698bd4e4.png
             * userType : NORMAL
             * ifPgc : false
             * description : null
             * area : null
             * gender : null
             * registDate : 1448645369000
             * releaseDate : null
             * cover : null
             * actionUrl : eyepetizer://pgc/detail/301331076/?title=%E5%A4%A7%E8%82%86&userType=NORMAL&tabIndex=0
             * followed : false
             * limitVideoOpen : false
             * library : BLOCK
             * birthday : null
             * country : null
             * city : null
             * university : null
             * job : null
             * expert : false
             */

            public int uid;
            public String nickname;
            public String avatar;
            public String userType;
            public boolean ifPgc;
            public Object description;
            public Object area;
            public Object gender;
            public long registDate;
            public Object releaseDate;
            public Object cover;
            public String actionUrl;
            public boolean followed;
            public boolean limitVideoOpen;
            public String library;
            public Object birthday;
            public Object country;
            public Object city;
            public Object university;
            public Object job;
            public boolean expert;
        }
    }
}
