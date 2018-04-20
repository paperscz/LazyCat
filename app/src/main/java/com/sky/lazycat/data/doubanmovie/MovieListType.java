package com.sky.lazycat.data.doubanmovie;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by yuetu-develop on 2018/4/20.
 */

public class MovieListType extends AbstractExpandableItem implements MultiItemEntity {
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
