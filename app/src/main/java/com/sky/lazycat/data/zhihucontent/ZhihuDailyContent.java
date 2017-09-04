package com.sky.lazycat.data.zhihucontent;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class ZhihuDailyContent {


    private String body;
    @SerializedName("image_source")
    private String imageSource;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }


    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     <div class="headline-background">
     <a class="headline-background-link" href="http://med.sina.com/article_detail_103_1_32682.html">
     <div class="heading">相关新闻</div>
     <div class="heading-content">柳叶刀：超过 13.5 万人大型研究发现多吃脂肪能降低死亡率</div>
     <i class="icon-arrow-right"></i>
     </a>
     </div>

     </div>

     <div class="content-inner">



     <div class="question">
     <h2 class="question-title">柳叶刀这篇研究，打的还真不是营养学的脸</h2>
     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic4.zhimg.com/v2-05c68ba9956d2c7f6923c37c9633c1ab_is.jpg">
     <span class="author">KellyWeaver，</span><span class="bio">For Public Health</span>
     </div>

     <div class="content">
     <p>Lancet 上最近发布的研究显示，碳水化合物摄入过量可能提高总死亡率[1]，Lancet 这篇研究，可以说明碳水化合物供能比<strong>达到</strong>60%以上的时候死亡率会上升，但：</p>
     <ul>
     <li>它不能说明碳水化合物供能比低于 40%的时候死亡率还会下降；</li>
     <li>更不能说明目前知乎上各位神棍鼓吹的碳水化合物供能比低于 5%的极低碳水化合物饮食，或者你们可能更熟悉&ldquo;生酮饮食&rdquo;这个名字，能够具有更低的死亡风险。</li>
     <li>绝大部分用的起手机看得见这篇文章的中国人，都不需要担心这篇文章提到的碳水化合物供能比过高可能带来的问题，因为，你们的碳水化合物供能比根本达不到 60%！</li>
     </ul>
     <p><strong><strong>先来带你读懂研究原文关键表格</strong></strong></p>
     <p><img class="content-image" src="http://pic1.zhimg.com/v2-e2723c9523fc838a7fea9fca88ce9214_b.png" alt="" /></p>
     <p>先解释一下这张表的意思。研究按照碳水化合物提供的能量占总能量的比例，把研究中的所有对象挨着排了个名，然后等分成了五段，叫做五分位。</p>
     <p>这张表的第一排，写的就是各个五分位内部的中位数；第 1~5 五个五分位，碳水化合物%E 分别是 46.4%、54.6%、60.8%、67.7%、77.2%。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/v2-9cf8f3f7ed7b15c1cdd366f5fd38c625_b.png" alt="" /></p>
     <p>接下来，这张表右边还有一个风险比（hazard ratio），你们不用理解这玩意是啥意思，只需要知道，这下面的每一个数后面的 95%置信区间，也就是这个数后面的括号里写的那个范围，<strong>只要这个范围包含了 1，风险就是没有统计学意义的。</strong></p>
     <p>比如说，0.83-1.18，包含了 1.00，就是不显著的；1.16-1.60，两个都比 1 大，就证明危险是具有统计学意义的。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/v2-c8ccf7dfaf2be63d053a6e6e540af70a_b.png" alt="" /></p>
     <p>（用黄色标出了可以认为&ldquo;显著&rdquo;的 95%置信区间）</p>
     <p><strong><strong>这张表到底说明了什么？</strong></strong></p>
     <p>在提到所有表之前，首先给大家介绍一下：研究者使用的趋势关系模型是三次的（cubic）。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/v2-4f822aa8e3ec8537548161b7d9b1b540_b.png" alt="" /></p>
     <p>这就意味着，研究中发现的关系，都不能按照普通的线性关系去理解，这些关系的曲线更可能是&ldquo;J&rdquo;型之类的非直线关系，并且任何基于研究数据范围的外推，无论向上还是向下的，都必须非常谨慎。</p>
     <ul>
     <li><strong>碳水化合物部分</strong></li>
     </ul>
     <p>首先请注意，主要心血管疾病（Major cardiovascular disease）、心肌梗塞（myocardial infarction）、中风（stroke）、心血管疾病死亡率（cardiovascular mortality）这第 2-5 排的四项，p 值都是大于 0.05 的，<strong>也就是，心血管病相关的四个率的上升 / 下降的趋势并没有统计学意义。</strong></p>
     <p><img class="content-image" src="http://pic2.zhimg.com/v2-aa94828cfc579d344874281a03846211_b.png" alt="" /></p>
     <p>（黄色标出了不显著的四个率）</p>
     <p>其次，虽然全因死亡率和非心血管疾病死亡率中趋势 p 值都是 0.0001 这个量级，但是，这个关系并不是线性的。这项研究中，两个具有显著上升趋势的项，都是到达第 5 个五分位的时候出现了骤升：</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/v2-3ad0b6868da033620e8777e2634162f1_b.png" alt="" /></p>
     <p>如果各位对数字不敏感，我这里用 Excel 做了一个图：</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/v2-cb81469b1a0394526dadb1169376bd37_b.png" alt="" /></p>
     <p>可以说，如果没有最后这一个高点，<strong>只看中间两个处于膳食指南推荐范围内的点，甚至把第一个点也包括进来，都可以说两个死亡率上升的趋势基本约等于没有。</strong></p>
     <p><strong>这样的极低的趋势，很可能是混杂因素引起的，极可能不具有统计学意义。</strong></p>
     <p>考虑到研究使用的模型是三次曲线（cubic splines），这样的先平缓再陡升的&ldquo;J&rdquo;型曲线才是合理的。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/v2-4f822aa8e3ec8537548161b7d9b1b540_b.png" alt="" /></p>
     <p>从后面的风险比数据也可以看到，<strong>当摄入量在现行膳食指南推荐的碳水化合物供能比范围内的时候，全因死亡率和非心血管病死亡率的上升都不显著</strong>。</p>
     <p><img class="content-image" src="http://pic3.zhimg.com/v2-7dab7305cdbef4e65bf72881201ae47a_b.png" alt="" /></p>
     <p><strong><strong>研究解读结论</strong></strong></p>
     <p>Lancet 这篇研究的碳水化合物部分的发现，最适合的解读正如论文所说，是高碳水化合物摄入可能引起全因死亡率增加。但是，这个&ldquo;高&rdquo;，根据研究数据，是要高到 60%以上；而在碳水化合物供能比上升到 60%之前，这个死亡风险都并没有明显的升高趋势。</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/v2-4f924d38594f6c13a43b1075b2c3e370_b.png" alt="" /></p>
     <p>这篇研究中，风险上升最明显的，是那个碳水供能比达到 77%的组。这是什么概念？中国 60 年代一穷二白、吃不起肥肉的时候，一个轻体力活动成人，得吃下差不多整整三斤白米饭、白面馒头一类的东西，才能把一天的碳水化合物供能比顶到 77%这个量级！</p>
     <p>而对于研究所说的供能比 60%以上，对于现在的中国人，尤其是城市中国人来说，早就基本不是问题了。我国居民膳食碳水化合物供能比早就已经一路下降，而且早在 2011 年，就已经降到只有 54.3%了[2]。</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/v2-465422d081fe9c4edf02a10b150cd6cb_b.png" alt="" /></p>
     <p><strong>换句话说，绝大多数买得起手机的人，根本不在这篇研究警告的人群之列。</strong></p>
     <p>另外，由于该研究没有低碳水化合物供能比低于 42.6% 的组别，且目前三个较低的五分位组中死亡率并没有具有统计学意义的明显的变化趋势，我们也就不能用它来推断碳水化合物供能比低于 40%的时候死亡率会是什么情况了&mdash;&mdash;更不要说生酮饮食低于 5%的碳水化合物供能比之下会是什么情况。</p>
     <p>神棍们看见碳水化合物供能比高到 60%以上会增大死亡风险，就认为要低碳水化合物才好，那照他们这个逻辑，体重也是 BMI 超过 24 以上就开始出现负面健康效应，那岂不是体重为 0 的时候最健康了？</p>
     <p>送你们两个字，呵呵。</p>
     <p><strong>总结</strong></p>
     <p>营养学从来不是一门一味要求&ldquo;多吃 xx&rdquo;、&ldquo;少吃 xx&rdquo;的学科，而是一门讲求平衡和范围的学科。脂肪也好，碳水化合物也好，蛋白质也好，都是在一个合适的范围内比较合适。</p>
     <p>中国居民膳食营养素推荐摄入量 2013 版中就认为，碳水化合物供能比的可接受范围是 50%-65%，脂肪的可接受范围则是 20%-30%。而如果高了、低了，都是不合理的。</p>
     <p><strong>所以，可以说，不管是一味去掉脂肪，还是一味去掉碳水化合物，都不符合现代营养学的思路。</strong>营养学所谓的&ldquo;少吃脂肪&rdquo;，也并不是没有下限，正如前述，至少要保证膳食能量的 20%来源于脂肪，才能不影响健康。</p>
     <p><strong>而现在我们中国人面临的问题是，碳水化合物供能比不断下降，脂肪供能比越来越高，从 1991 年的 21.8%，一路涨到了 2011 年的 32%，大城市中甚至达到了 36.9%；这样改变着的膳食模式，和体力活动减少一样，都是中国近年来肥胖和慢病发病率爆发式增长的原因[3]。</strong></p>
     <p>我们公共卫生工作者，看到了慢性病这样的爆发增长趋势，便积极和政府、和社会合作，想尽一切办法阻止：无论是为婴幼儿提供免费儿保咨询，还是为 65 岁以上老年人提供免费体检，都是为了从源头上抗击慢病的爆发增长。无数基层卫生工作者在教育群众健康饮食、积极锻炼的时候，也曾遭遇过群众的不理解，但一想到这是为了共同建设健康中国，大家都在任劳任怨地坚持！</p>
     <p><strong>哗众取宠，请绕开医疗健康话题。中国人的生命健康之重，不是每个营销号都能承受得起的！</strong></p>
     <p>参考文献：</p>
     <p>[1] Dehghan M, Mente A, Zhang X, et al. Associations of fats and carbohydrate intake with cardiovascular disease and mortality in 18 countries from five continents (PURE): a prospective cohort study[J]. The Lancet, 2017.</p>
     <p>[2] Zhai F Y, Du S F, Wang Z H, et al. Dynamics of the Chinese diet and the role of urbanicity, 1991&ndash;2011[J]. Obesity Reviews, 2014, 15(S1): 16-26.</p>
     <p>[3] Yang G, Kong L, Zhao W, et al. Emergence of chronic non-communicable diseases in China[J]. The Lancet, 2008, 372(9650): 1697-1705.</p>
     <hr />
     <p>&nbsp;</p>
     <p>商业转载请私信联系授权，知乎日报除外。</p>

     <div class="view-more"><a href="http://zhuanlan.zhihu.com/p/29000784">查看知乎讨论</a></div>

     </div>
     </div>
     </div>


     </div>
     </div>
     * image_source : Yestone.com 版权图片库
     * title : 「权威杂志说多吃脂肪降低死亡率」，首先，这事跟你没关系
     * image : https://pic1.zhimg.com/v2-7a5ed7d40e3bdf58f35e405604c70d74.jpg
     * share_url : http://daily.zhihu.com/story/9600882
     * js : []
     * ga_prefix : 090416
     * images : ["https://pic3.zhimg.com/v2-87334799adeb13155223eeb4e1b8a296.jpg"]
     * type : 0
     * id : 9600882
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

}
