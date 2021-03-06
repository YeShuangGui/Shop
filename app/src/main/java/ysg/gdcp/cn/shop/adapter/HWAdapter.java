package ysg.gdcp.cn.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ysg.gdcp.cn.shop.R;
import ysg.gdcp.cn.shop.Utils.CartProvider;
import ysg.gdcp.cn.shop.bean.Wares;

/**
 * Created by Administrator on 2017/7/4 08:59.
 *
 * @author ysg
 */

public class HWAdapter extends SimpleAdapter<Wares> {

    private final CartProvider mCartProvider;

    public HWAdapter(List<Wares> datas, Context context) {
        super(datas, context, R.layout.template_hot_wares);
        mCartProvider = new CartProvider(context);
    }

    @Override
    public void bindData(BaseViewHolder holder, final Wares wares) {
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getVIew(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(wares.getImgUrl()));

        holder.getTextView(R.id.text_title).setText(wares.getName());
        holder.getTextView(R.id.text_price).setText(wares.getPrice().toString());
        Button button = holder.getButton(R.id.btn_add);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mCartProvider.put(wares);
                    Toast.makeText(mContext, "添加到购物车成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    public void reSet(int layoutId) {
        this.mLayoutID = layoutId;
        notifyItemRangeChanged(0, getData().size());
    }
}
