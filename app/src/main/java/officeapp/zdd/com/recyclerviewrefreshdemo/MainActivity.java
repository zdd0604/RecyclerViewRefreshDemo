package officeapp.zdd.com.recyclerviewrefreshdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import officeapp.zdd.com.recyclerviewrefreshdemo.adapter.MainRecyclerAdapter;
import officeapp.zdd.com.recyclerviewrefreshdemo.model.NewsModel;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.main_refreshLayout)
    SwipeRefreshLayout main_refreshLayout;
    @BindView(R.id.main_recyclerView)
    RecyclerView main_recyclerView;

    private LayoutInflater inflater;
    private MainRecyclerAdapter mRecyclerAdapter;
    private List<NewsModel> listModel = new ArrayList<>();
    private int inumb = 0;
    private int total = 10;
    private int model_Size = 0;
    private Gson mGson;
    private String url = "http://midp.tunnel.qydev.com/testServer/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 加载控件
     */
    private void initView() {
        mGson = new Gson();
        inflater = LayoutInflater.from(this);
        main_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new MainRecyclerAdapter(null);
        //默认提供5种方法（渐显、缩放、从下到上，从左到右、从右到左）
        //public static final int ALPHAIN = 0x00000001;
        //public static final int SCALEIN = 0x00000002;
        //public static final int SLIDEIN_BOTTOM = 0x00000003;
        //public static final int SLIDEIN_LEFT = 0x00000004;
        //public static final int SLIDEIN_RIGHT = 0x00000005;
        //  mRecyclerAdapter.openLoadAnimation();
        //开启加载动画
        mRecyclerAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //动画默认只执行一次,如果想重复执行可设置
        mRecyclerAdapter.isFirstOnly(false);
        main_recyclerView.setAdapter(mRecyclerAdapter);

        //设置刷新的颜色
        main_refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //设置刷新事件
        main_refreshLayout.setOnRefreshListener(this);
        //设置加载更多的事件
        mRecyclerAdapter.setOnLoadMoreListener(this);
        onRefresh();
    }


    /**
     * 下啦刷新
     */
    @Override
    public void onRefresh() {
        inumb = 0;
        model_Size = 0;
        OkGo.<String>post(url)
                .tag(this)
                .params("num", inumb)
                .params("tonum", total)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Type type = new TypeToken<List<NewsModel>>() {
                        }.getType();
                        List<NewsModel> models = mGson.fromJson(response.body(), type);
                        model_Size += models.size();
                        if (models != null) {
                            //设置数据
                            mRecyclerAdapter.setNewData(models);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //可能需要移除之前添加的布局
                        mRecyclerAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });

    }


    /**
     * 上啦加载
     */
    @Override
    public void onLoadMoreRequested() {
        inumb++;
        if (model_Size == 30) {
            //数据全部加载完毕,也就是没有更过数据,加载结束
            mRecyclerAdapter.loadMoreEnd();
        } else {
            OkGo.<String>post(url)
                    .tag(this)
                    .params("num", inumb)
                    .params("tonum", total)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Type type = new TypeToken<List<NewsModel>>() {
                            }.getType();
                            List<NewsModel> models = mGson.fromJson(response.body(), type);
                            model_Size += models.size();
                            if (models != null) {
                                //成功获取更多数据
                                mRecyclerAdapter.addData(models);
                                //加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
                                mRecyclerAdapter.loadMoreComplete();
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            //加载失败
                            mRecyclerAdapter.loadMoreFail();
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            //获取更多数据失败
                            mRecyclerAdapter.loadMoreComplete();
                        }
                    });

        }


    }


    public void setRefreshing(final boolean refreshing) {
        main_refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                main_refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
