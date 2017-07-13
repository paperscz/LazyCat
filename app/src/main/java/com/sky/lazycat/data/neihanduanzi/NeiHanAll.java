package com.sky.lazycat.data.neihanduanzi;

import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/13.
 */

public class NeiHanAll {

        private boolean has_more;
        private String tip;
        private boolean has_new_message;
        private double max_time;
        private int min_time;
        private List<DataBean> data;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public boolean isHas_new_message() {
            return has_new_message;
        }

        public void setHas_new_message(boolean has_new_message) {
            this.has_new_message = has_new_message;
        }

        public double getMax_time() {
            return max_time;
        }

        public void setMax_time(double max_time) {
            this.max_time = max_time;
        }

        public int getMin_time() {
            return min_time;
        }

        public void setMin_time(int min_time) {
            this.min_time = min_time;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * group : {"user":{"user_id":5984290216,"name":"3-8创史人","followings":0,"user_verified":false,"ugc_count":3,"avatar_url":"http://p3.pstatp.com/medium/249a0012cf6a97f02be8","followers":1,"is_following":false,"is_pro_user":false},"text":"爸爸为你感到自豪","dislike_reason":[{"type":1,"id":319,"title":"恶搞"},{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":5984290216,"title":"作者:3-8创史人"}],"create_time":1498961390,"id":62814370386,"favorite_count":12,"go_detail_count":1498,"user_favorite":0,"share_type":1,"max_screen_width_percent":0.6,"is_can_share":1,"category_type":1,"share_url":"http://m.neihanshequ.com/share/group/62814370386/?iid=0&app=joke_essay","label":1,"content":"爸爸为你感到自豪","comment_count":16,"id_str":"62814370386","media_type":1,"share_count":79,"type":3,"status":112,"has_comments":0,"large_image":{"width":570,"r_height":800,"r_width":570,"url_list":[{"url":"http://p3.pstatp.com/large/29d00000c985590b33a3"},{"url":"http://pb9.pstatp.com/large/29d00000c985590b33a3"},{"url":"http://pb1.pstatp.com/large/29d00000c985590b33a3"}],"uri":"large/29d00000c985590b33a3","height":800},"user_bury":0,"status_desc":"热门投稿","display_type":0,"user_digg":0,"online_time":1498961390,"category_name":"搞笑囧图","category_visible":true,"bury_count":23,"is_anonymous":false,"repin_count":12,"min_screen_width_percent":0.167,"digg_count":359,"has_hot_comments":0,"allow_dislike":true,"image_status":1,"user_repin":0,"activity":{},"group_id":62814370386,"middle_image":{"width":202,"r_height":283,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb9.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb1.pstatp.com/w202/29d00000c985590b33a3"}],"uri":"w202/29d00000c985590b33a3","height":283},"category_id":2}
             * comments : []
             * type : 1
             * display_time : 1499915688
             * online_time : 1499915688
             */

            public static final String DUANZI = "内涵段子";
            public static final String GAOXIAO_PIC = "搞笑囧图";
            public static final String BAOXIAO_GIF = "爆笑GIF";
            public static final String GAOXIAO_VIDEO = "搞笑视频";

            public static final int TEXT = 1;
            public static final int IMG = 2;
            public static final int GIF = 3;
            public static final int VIDEO = 4;

            private GroupBean group;
            private int type;
            private double display_time;
            private double online_time;
            private List<?> comments;

            public GroupBean getGroup() {
                return group;
            }

            public void setGroup(GroupBean group) {
                this.group = group;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public double getDisplay_time() {
                return display_time;
            }

            public void setDisplay_time(double display_time) {
                this.display_time = display_time;
            }

            public double getOnline_time() {
                return online_time;
            }

            public void setOnline_time(double online_time) {
                this.online_time = online_time;
            }

            public List<?> getComments() {
                return comments;
            }

            public void setComments(List<?> comments) {
                this.comments = comments;
            }


            public static class GroupBean {


                /**
                 * user : {"user_id":5984290216,"name":"3-8创史人","followings":0,"user_verified":false,"ugc_count":3,"avatar_url":"http://p3.pstatp.com/medium/249a0012cf6a97f02be8","followers":1,"is_following":false,"is_pro_user":false}
                 * text : 爸爸为你感到自豪
                 * dislike_reason : [{"type":1,"id":319,"title":"恶搞"},{"type":2,"id":2,"title":"吧:搞笑囧图"},{"type":4,"id":0,"title":"内容重复"},{"type":3,"id":5984290216,"title":"作者:3-8创史人"}]
                 * create_time : 1498961390
                 * id : 62814370386
                 * favorite_count : 12
                 * go_detail_count : 1498
                 * user_favorite : 0
                 * share_type : 1
                 * max_screen_width_percent : 0.6
                 * is_can_share : 1
                 * category_type : 1
                 * share_url : http://m.neihanshequ.com/share/group/62814370386/?iid=0&app=joke_essay
                 * label : 1
                 * content : 爸爸为你感到自豪
                 * comment_count : 16
                 * id_str : 62814370386
                 * media_type : 1
                 * share_count : 79
                 * type : 3
                 * status : 112
                 * has_comments : 0
                 * large_image : {"width":570,"r_height":800,"r_width":570,"url_list":[{"url":"http://p3.pstatp.com/large/29d00000c985590b33a3"},{"url":"http://pb9.pstatp.com/large/29d00000c985590b33a3"},{"url":"http://pb1.pstatp.com/large/29d00000c985590b33a3"}],"uri":"large/29d00000c985590b33a3","height":800}
                 * user_bury : 0
                 * status_desc : 热门投稿
                 * display_type : 0
                 * user_digg : 0
                 * online_time : 1498961390
                 * category_name : 搞笑囧图
                 * category_visible : true
                 * bury_count : 23
                 * is_anonymous : false
                 * repin_count : 12
                 * min_screen_width_percent : 0.167
                 * digg_count : 359
                 * has_hot_comments : 0
                 * allow_dislike : true
                 * image_status : 1
                 * user_repin : 0
                 * activity : {}
                 * group_id : 62814370386
                 * middle_image : {"width":202,"r_height":283,"r_width":202,"url_list":[{"url":"http://p3.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb9.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb1.pstatp.com/w202/29d00000c985590b33a3"}],"uri":"w202/29d00000c985590b33a3","height":283}
                 * category_id : 2
                 */

                private UserBean user;
                private String text;
                private int create_time;
                private long id;
                private int favorite_count;
                private int go_detail_count;
                private int user_favorite;
                private int share_type;
                private double max_screen_width_percent;
                private int is_can_share;
                private int category_type;
                private String share_url;
                private int label;
                private String content;
                private int comment_count;
                private String id_str;
                private int media_type;
                private int share_count;
                private int type;
                private int status;
                private int has_comments;
                private LargeImageBean large_image;
                private int user_bury;
                private String status_desc;
                private int display_type;
                private int user_digg;
                private int online_time;
                private String category_name;
                private boolean category_visible;
                private int bury_count;
                private boolean is_anonymous;
                private int repin_count;
                private double min_screen_width_percent;
                private int digg_count;
                private int has_hot_comments;
                private boolean allow_dislike;
                private int image_status;
                private int user_repin;
                private ActivityBean activity;
                private long group_id;
                private MiddleImageBean middle_image;
                private int category_id;
                private List<DislikeReasonBean> dislike_reason;

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(int create_time) {
                    this.create_time = create_time;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getFavorite_count() {
                    return favorite_count;
                }

                public void setFavorite_count(int favorite_count) {
                    this.favorite_count = favorite_count;
                }

                public int getGo_detail_count() {
                    return go_detail_count;
                }

                public void setGo_detail_count(int go_detail_count) {
                    this.go_detail_count = go_detail_count;
                }

                public int getUser_favorite() {
                    return user_favorite;
                }

                public void setUser_favorite(int user_favorite) {
                    this.user_favorite = user_favorite;
                }

                public int getShare_type() {
                    return share_type;
                }

                public void setShare_type(int share_type) {
                    this.share_type = share_type;
                }

                public double getMax_screen_width_percent() {
                    return max_screen_width_percent;
                }

                public void setMax_screen_width_percent(double max_screen_width_percent) {
                    this.max_screen_width_percent = max_screen_width_percent;
                }

                public int getIs_can_share() {
                    return is_can_share;
                }

                public void setIs_can_share(int is_can_share) {
                    this.is_can_share = is_can_share;
                }

                public int getCategory_type() {
                    return category_type;
                }

                public void setCategory_type(int category_type) {
                    this.category_type = category_type;
                }

                public String getShare_url() {
                    return share_url;
                }

                public void setShare_url(String share_url) {
                    this.share_url = share_url;
                }

                public int getLabel() {
                    return label;
                }

                public void setLabel(int label) {
                    this.label = label;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public String getId_str() {
                    return id_str;
                }

                public void setId_str(String id_str) {
                    this.id_str = id_str;
                }

                public int getMedia_type() {
                    return media_type;
                }

                public void setMedia_type(int media_type) {
                    this.media_type = media_type;
                }

                public int getShare_count() {
                    return share_count;
                }

                public void setShare_count(int share_count) {
                    this.share_count = share_count;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getHas_comments() {
                    return has_comments;
                }

                public void setHas_comments(int has_comments) {
                    this.has_comments = has_comments;
                }

                public LargeImageBean getLarge_image() {
                    return large_image;
                }

                public void setLarge_image(LargeImageBean large_image) {
                    this.large_image = large_image;
                }

                public int getUser_bury() {
                    return user_bury;
                }

                public void setUser_bury(int user_bury) {
                    this.user_bury = user_bury;
                }

                public String getStatus_desc() {
                    return status_desc;
                }

                public void setStatus_desc(String status_desc) {
                    this.status_desc = status_desc;
                }

                public int getDisplay_type() {
                    return display_type;
                }

                public void setDisplay_type(int display_type) {
                    this.display_type = display_type;
                }

                public int getUser_digg() {
                    return user_digg;
                }

                public void setUser_digg(int user_digg) {
                    this.user_digg = user_digg;
                }

                public int getOnline_time() {
                    return online_time;
                }

                public void setOnline_time(int online_time) {
                    this.online_time = online_time;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public boolean isCategory_visible() {
                    return category_visible;
                }

                public void setCategory_visible(boolean category_visible) {
                    this.category_visible = category_visible;
                }

                public int getBury_count() {
                    return bury_count;
                }

                public void setBury_count(int bury_count) {
                    this.bury_count = bury_count;
                }

                public boolean isIs_anonymous() {
                    return is_anonymous;
                }

                public void setIs_anonymous(boolean is_anonymous) {
                    this.is_anonymous = is_anonymous;
                }

                public int getRepin_count() {
                    return repin_count;
                }

                public void setRepin_count(int repin_count) {
                    this.repin_count = repin_count;
                }

                public double getMin_screen_width_percent() {
                    return min_screen_width_percent;
                }

                public void setMin_screen_width_percent(double min_screen_width_percent) {
                    this.min_screen_width_percent = min_screen_width_percent;
                }

                public int getDigg_count() {
                    return digg_count;
                }

                public void setDigg_count(int digg_count) {
                    this.digg_count = digg_count;
                }

                public int getHas_hot_comments() {
                    return has_hot_comments;
                }

                public void setHas_hot_comments(int has_hot_comments) {
                    this.has_hot_comments = has_hot_comments;
                }

                public boolean isAllow_dislike() {
                    return allow_dislike;
                }

                public void setAllow_dislike(boolean allow_dislike) {
                    this.allow_dislike = allow_dislike;
                }

                public int getImage_status() {
                    return image_status;
                }

                public void setImage_status(int image_status) {
                    this.image_status = image_status;
                }

                public int getUser_repin() {
                    return user_repin;
                }

                public void setUser_repin(int user_repin) {
                    this.user_repin = user_repin;
                }

                public ActivityBean getActivity() {
                    return activity;
                }

                public void setActivity(ActivityBean activity) {
                    this.activity = activity;
                }

                public long getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(long group_id) {
                    this.group_id = group_id;
                }

                public MiddleImageBean getMiddle_image() {
                    return middle_image;
                }

                public void setMiddle_image(MiddleImageBean middle_image) {
                    this.middle_image = middle_image;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public List<DislikeReasonBean> getDislike_reason() {
                    return dislike_reason;
                }

                public void setDislike_reason(List<DislikeReasonBean> dislike_reason) {
                    this.dislike_reason = dislike_reason;
                }

                public static class UserBean {
                    /**
                     * user_id : 5984290216
                     * name : 3-8创史人
                     * followings : 0
                     * user_verified : false
                     * ugc_count : 3
                     * avatar_url : http://p3.pstatp.com/medium/249a0012cf6a97f02be8
                     * followers : 1
                     * is_following : false
                     * is_pro_user : false
                     */

                    private long user_id;
                    private String name;
                    private int followings;
                    private boolean user_verified;
                    private int ugc_count;
                    private String avatar_url;
                    private int followers;
                    private boolean is_following;
                    private boolean is_pro_user;

                    public long getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(long user_id) {
                        this.user_id = user_id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getFollowings() {
                        return followings;
                    }

                    public void setFollowings(int followings) {
                        this.followings = followings;
                    }

                    public boolean isUser_verified() {
                        return user_verified;
                    }

                    public void setUser_verified(boolean user_verified) {
                        this.user_verified = user_verified;
                    }

                    public int getUgc_count() {
                        return ugc_count;
                    }

                    public void setUgc_count(int ugc_count) {
                        this.ugc_count = ugc_count;
                    }

                    public String getAvatar_url() {
                        return avatar_url;
                    }

                    public void setAvatar_url(String avatar_url) {
                        this.avatar_url = avatar_url;
                    }

                    public int getFollowers() {
                        return followers;
                    }

                    public void setFollowers(int followers) {
                        this.followers = followers;
                    }

                    public boolean isIs_following() {
                        return is_following;
                    }

                    public void setIs_following(boolean is_following) {
                        this.is_following = is_following;
                    }

                    public boolean isIs_pro_user() {
                        return is_pro_user;
                    }

                    public void setIs_pro_user(boolean is_pro_user) {
                        this.is_pro_user = is_pro_user;
                    }
                }

                public static class LargeImageBean {
                    private int width;
                    private int height;
                    private List<LargeImageBean.UrlListBeanX> url_list;

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public List<UrlListBeanX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanX{
                        private String url;
                        public String getUrl() {
                            return url;
                        }
                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }

                }

                public static class ActivityBean {
                }

                public static class MiddleImageBean {
                    /**
                     * width : 202
                     * r_height : 283
                     * r_width : 202
                     * url_list : [{"url":"http://p3.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb9.pstatp.com/w202/29d00000c985590b33a3"},{"url":"http://pb1.pstatp.com/w202/29d00000c985590b33a3"}]
                     * uri : w202/29d00000c985590b33a3
                     * height : 283
                     */

                    private int width;
                    private int r_height;
                    private int r_width;
                    private String uri;
                    private int height;
                    private List<UrlListBeanX> url_list;

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getR_height() {
                        return r_height;
                    }

                    public void setR_height(int r_height) {
                        this.r_height = r_height;
                    }

                    public int getR_width() {
                        return r_width;
                    }

                    public void setR_width(int r_width) {
                        this.r_width = r_width;
                    }

                    public String getUri() {
                        return uri;
                    }

                    public void setUri(String uri) {
                        this.uri = uri;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public List<UrlListBeanX> getUrl_list() {
                        return url_list;
                    }

                    public void setUrl_list(List<UrlListBeanX> url_list) {
                        this.url_list = url_list;
                    }

                    public static class UrlListBeanX {
                        /**
                         * url : http://p3.pstatp.com/w202/29d00000c985590b33a3
                         */

                        private String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }
                }

                public static class DislikeReasonBean {
                    /**
                     * type : 1
                     * id : 319
                     * title : 恶搞
                     */

                    private int type;
                   // private int id;
                    private String title;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

//                    public int getId() {
//                        return id;
//                    }

//                    public void setId(int id) {
//                        this.id = id;
//                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }
        }

}
