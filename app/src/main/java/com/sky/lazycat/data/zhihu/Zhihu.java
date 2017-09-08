package com.sky.lazycat.data.zhihu;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public class Zhihu {
    /**
     * date : 20170825
     * stories : [{"images":["https://pic2.zhimg.com/v2-88c3f5f23677e12cee319d0c88175325.jpg"],"type":0,"id":9586954,"ga_prefix":"082510","title":"经济危机的先兆有很多，可我们依然预测不出下一次"},{"images":["https://pic2.zhimg.com/v2-5534a7d931c58681807398ffb33f6f89.jpg"],"type":0,"id":9578501,"ga_prefix":"082509","title":"独居、不婚者越来越多，可能对人们和社会有什么影响？"},{"images":["https://pic1.zhimg.com/v2-17b35a776cfde863cb716e3303ae15d8.jpg"],"type":0,"id":9586952,"ga_prefix":"082507","title":"「应该不是只有我女朋友这样吧？怎么会这样呢？」"},{"images":["https://pic4.zhimg.com/v2-f007bffb85f7e115fff61f98aea9997b.jpg"],"type":0,"id":9586165,"ga_prefix":"082507","title":"为什么腾讯游戏做不出所谓的「好游戏」？"},{"images":["https://pic2.zhimg.com/v2-faa05e32289c5685224e4ff23a361ae5.jpg"],"type":0,"id":9586647,"ga_prefix":"082507","title":"郭敬明提起刑事诉讼，李枫会因此坐牢吗？"},{"images":["https://pic1.zhimg.com/v2-d849b937ea998adbeab688f302424490.jpg"],"type":0,"id":9586654,"ga_prefix":"082506","title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic2.zhimg.com/v2-aa36deead4e8ba5fe21044c65cee37a1.jpg"],"type":0,"id":9586188,"ga_prefix":"082506","title":"这里是广告 · 为什么 RAP\r\n 要唱那么快？"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-c5097eddcb8703eea5f1f34b73b8701a.jpg","type":0,"id":9586952,"ga_prefix":"082507","title":"「应该不是只有我女朋友这样吧？怎么会这样呢？」"},{"image":"https://pic3.zhimg.com/v2-8ed5bc89d2988da1175b65eb757a80a6.jpg","type":0,"id":9586165,"ga_prefix":"082507","title":"为什么腾讯游戏做不出所谓的「好游戏」？"},{"image":"https://pic4.zhimg.com/v2-d073349ef2e554292f7b722dfae3086b.jpg","type":0,"id":9586647,"ga_prefix":"082507","title":"郭敬明提起刑事诉讼，李枫会因此坐牢吗？"},{"image":"https://pic3.zhimg.com/v2-0b7c7554f535fe78db76d1bfa021f19a.jpg","type":0,"id":9586015,"ga_prefix":"082414","title":"96 亿元的造船大单，中国人从韩国人手里赢到了"},{"image":"https://pic1.zhimg.com/v2-f969a29b5a79bbb0f4b136c53fab0740.jpg","type":0,"id":9586188,"ga_prefix":"082506","title":"这里是广告 · 为什么 RAP\r\n 要唱那么快？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-88c3f5f23677e12cee319d0c88175325.jpg"]
         * type : 0
         * id : 9586954
         * ga_prefix : 082510
         * title : 经济危机的先兆有很多，可我们依然预测不出下一次
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-c5097eddcb8703eea5f1f34b73b8701a.jpg
         * type : 0
         * id : 9586952
         * ga_prefix : 082507
         * title : 「应该不是只有我女朋友这样吧？怎么会这样呢？」
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
