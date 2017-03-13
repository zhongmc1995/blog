package com.zhongmc.blog.utils;

/**
 * Created by ZMC on 2017/1/29.
 */
public class PagingUtil {
    /**
     * 返回anchor类型的分页
     * @param currentPageNo
     * @param maxPageNo
     * @param parameters
     * @param url
     * @return
     *
     */
    public static String getPagelink(int currentPageNo, int maxPageNo, String parameters, String url) {
        int begainNo = 1;
        int endNo = maxPageNo;

        StringBuffer sb = new StringBuffer("");
        if (maxPageNo>1){
            sb.append(currentPageNo <= 1 ? "<li><a href=\"#\" class=\"disabled\">←</a></li>" : "<li><a href=\""+url+"?page="+((currentPageNo-1)>1?(currentPageNo-1):1)+"\" class=\"button\">←</a></li>");

            if (maxPageNo<=7){
                for (int i = begainNo; i <= endNo; i++) {
                    if (currentPageNo==i){
                        sb.append("<li class=\"current\"><a href=\"\" class=\"current\">"+i+"</a></li>");
                    }else {
                        sb.append("<li><a href=\""+url+"?page="+i+"\" class=\"page\">"+i+"</a></li>");
                    }
                }
            }else {
                if (currentPageNo<6){
                    for (int i=1;i<=7;i++){
                        if (currentPageNo==i){
                            sb.append("<li class=\"current\"><a href=\"\" class=\"current\">"+i+"</a></li>");
                        }else {
                            sb.append("<li><a href=\""+url+"?page="+i+"\" class=\"\">"+i+"</a></li>");
                        }
                    }
                    sb.append("<li><span>&hellip;</span></li>");
                }else if (currentPageNo<(maxPageNo-2)){
                    sb.append("<li><a href=\""+url+"?page=1"+"\" class=\"\">"+1+"</a></li>");
                    sb.append("<li><a href=\""+url+"?page=2"+"\" class=\"\">"+2+"</a></li>");
                    sb.append("<li><span>&hellip;</span></li>");
                    sb.append("<li><a href=\""+url+"?page="+(currentPageNo-2)+"\" class=\"\">"+(currentPageNo-2)+"</a></li>");
                    sb.append("<li><a href=\""+url+"?page="+(currentPageNo-1)+"\" class=\"\">"+(currentPageNo-1)+"</a></li>");
                    sb.append("<li><a href=\"\" class=\" current\">"+(currentPageNo)+"</a></li>");
                    sb.append("<li><a href=\""+url+"?page="+(currentPageNo+1)+"\" class=\"\">"+(currentPageNo+1)+"</a></li>");
                    sb.append("<li><a href=\""+url+"?page="+(currentPageNo+2)+"\" class=\"\">"+(currentPageNo+2)+"</a></li>");
                    sb.append("<li><span>&hellip;</span></li>");
                }else if (currentPageNo<=maxPageNo){
                    sb.append("<li><a href=\""+url+"?page=1"+"\" class=\"\">"+1+"</a></li>");
                    sb.append("<li><a href=\""+url+"?page=2"+"\" class=\"\">"+2+"</a></li>");
                    sb.append("<li><span>&hellip;</span></li>");
                    for (int i=4;i>=0;i--){
                        if ((maxPageNo-i)==currentPageNo){
                            sb.append("<li class=\"current\"><a href=\"\" class=\"current\">"+(maxPageNo-i)+"</a></li>");
                        }else {
                            sb.append("<li><a href=\""+url+"?page="+(maxPageNo-i)+"\" class=\"\">"+(maxPageNo-i)+"</a></li>");
                        }
                    }
                }

            }
            sb.append(maxPageNo==currentPageNo?"<li><a href=\"#\" class=\"disabled\">→</a></li>":"<li><a href=\""+url+"?page="+(currentPageNo+1>maxPageNo?maxPageNo:currentPageNo+1)+"\" class=\"\">→</a></li>");
        }

        return  sb.toString();
    }

    public static int fixIndex(String page,int total){
        int index =1;
        if (page!=null){
            index = Integer.parseInt(page);
            if (index<1){
                index = 1;
            }else if (index>total){
                index = total;
            }
        }
        return index;
    }
}
