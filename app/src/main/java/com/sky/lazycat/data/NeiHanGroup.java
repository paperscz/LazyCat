package com.sky.lazycat.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class NeiHanGroup {

    private User user;
    private String text;
    @SerializedName("create_time")
    private long createTime;
    // 类型type
    private int category_type;
    private String content;
    private String comment_count;
    // 未知type
    private int type;
    @SerializedName("large_image")
    private LargeImage largeImage;

    private String status_desc;
    // 类型名称
    private String category_name;
    // 热评
    private int has_hot_comments;

    private List<Comments> comments;

    public class User{
        @SerializedName("user_id")
        private String userId;
        @SerializedName("name")
        private String userName;
        @SerializedName("avatar_url")
        private String userHead;

    }

    public class LargeImage{
        @SerializedName("r_height")
        private double height;
        @SerializedName("r_width")
        private double width;
        @SerializedName("url_list")
        private List<ImageUrl> url;

        public class ImageUrl{
            @SerializedName("url")
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public List<ImageUrl> getUrl() {
            return url;
        }

        public void setUrl(List<ImageUrl> url) {
            this.url = url;
        }
    }

    public class Comments{
        private String text;
        @SerializedName("create_time")
        private long createTime;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("avatar_url")
        private String userHeadImg;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCategory_type() {
        return category_type;
    }

    public void setCategory_type(int category_type) {
        this.category_type = category_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LargeImage getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(LargeImage largeImage) {
        this.largeImage = largeImage;
    }

    public String getStatus_desc() {
        return status_desc;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getHas_hot_comments() {
        return has_hot_comments;
    }

    public void setHas_hot_comments(int has_hot_comments) {
        this.has_hot_comments = has_hot_comments;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
