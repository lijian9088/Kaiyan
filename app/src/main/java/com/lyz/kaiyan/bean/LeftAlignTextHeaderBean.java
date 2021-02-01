package com.lyz.kaiyan.bean;

/**
 * @author liyanze
 * @create 2021/02/01
 * @Describe
 */
public class LeftAlignTextHeaderBean {

    /**
     * type : leftAlignTextHeader
     * data : {"dataType":"LeftAlignTextHeader","text":"最新评论","font":"normal","actionUrl":null,"adTrack":null}
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
         * dataType : LeftAlignTextHeader
         * text : 最新评论
         * font : normal
         * actionUrl : null
         * adTrack : null
         */

        public String dataType;
        public String text;
        public String font;
        public Object actionUrl;
        public Object adTrack;
    }
}
