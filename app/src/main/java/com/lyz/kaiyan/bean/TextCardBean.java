package com.lyz.kaiyan.bean;

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
public class TextCardBean {

    /**
     * type : textCard
     * data : {"dataType":"TextCard","id":0,"type":"header5","text":"喵星人","subTitle":null,"actionUrl":"eyepetizer://tag/538/?title=%E5%96%B5%E6%98%9F%E4%BA%BA","adTrack":null,"follow":null}
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
         * dataType : TextCard
         * id : 0
         * type : header5
         * text : 喵星人
         * subTitle : null
         * actionUrl : eyepetizer://tag/538/?title=%E5%96%B5%E6%98%9F%E4%BA%BA
         * adTrack : null
         * follow : null
         */

        public String dataType;
        public int id;
        public String type;
        public String text;
        public Object subTitle;
        public String actionUrl;
        public Object adTrack;
        public Object follow;

        @Override
        public String toString() {
            return "DataBean{" +
                    "dataType='" + dataType + '\'' +
                    ", id=" + id +
                    ", type='" + type + '\'' +
                    ", text='" + text + '\'' +
                    ", subTitle=" + subTitle +
                    ", actionUrl='" + actionUrl + '\'' +
                    ", adTrack=" + adTrack +
                    ", follow=" + follow +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TextCardBean{" +
                "type='" + type + '\'' +
                ", data=" + data +
                ", trackingData=" + trackingData +
                ", tag=" + tag +
                ", id=" + id +
                ", adIndex=" + adIndex +
                '}';
    }
}
