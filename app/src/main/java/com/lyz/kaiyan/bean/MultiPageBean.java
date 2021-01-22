package com.lyz.kaiyan.bean;

import org.json.JSONObject;

import java.util.List;

/**
 * @author liyanze
 * @create 2021/01/22
 * @Describe
 */
public class MultiPageBean {
    /**
     * "itemList":[...]
     * count : 14
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=1&isTag=true&adIndex=5
     * adExist : false
     */

    public int count;
    public int total;
    public String nextPageUrl;
    public boolean adExist;
    public List<DataBean> itemList;

    public static class DataBean{
        public String type;
        public Object data;
        public Object trackingData;
        public Object tag;
        public int id;
        public int adIndex;
    }

}
