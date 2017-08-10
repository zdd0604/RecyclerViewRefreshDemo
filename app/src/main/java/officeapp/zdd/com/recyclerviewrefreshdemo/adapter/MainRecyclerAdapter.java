package officeapp.zdd.com.recyclerviewrefreshdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import officeapp.zdd.com.recyclerviewrefreshdemo.R;
import officeapp.zdd.com.recyclerviewrefreshdemo.model.NewsModel;

/**
 * Created by Admin on 2017/8/8.
 */

public class MainRecyclerAdapter extends BaseQuickAdapter<NewsModel,BaseViewHolder> {

    public MainRecyclerAdapter(List<NewsModel> data) {
        super(R.layout.item_main_list,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsModel newsModel) {
        baseViewHolder.setText(R.id.title,newsModel.getName())
                .setText(R.id.des,newsModel.getWordpath());
    }
}
