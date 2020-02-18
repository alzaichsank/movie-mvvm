package id.alik.movie_mvvm.ui.main.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.domain.entity.TrailerDetailData
import kotlinx.android.synthetic.main.holder_trailer_list.view.*


/**
 * Created by alzaichsank on 2019-06-24.
 */
class AdapterTrailer(
    private val context: Context,
    private val onClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterTrailer.ViewHolder>() {

    private val dataItem: ArrayList<TrailerDetailData> = arrayListOf()

    fun getDataAt(position: Int): TrailerDetailData {
        return dataItem[position]
    }

    fun setData(dataItemList: List<TrailerDetailData>) {
        dataItem.clear()
        dataItem.addAll(dataItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_trailer_list, parent, false)
        return ViewHolder(
            itemView,
            onClickListener
        )
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataItem[position])
    }

    class ViewHolder(itemView: View, onClickListener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClickListener(adapterPosition)
            }
        }

        fun bindData(dataItem: TrailerDetailData?) {
            itemView.tv_title_trailer.text = dataItem?.name
            val requestOption = RequestOptions().let {
                it.diskCacheStrategy(DiskCacheStrategy.ALL)
                it.timeout(60000)
                it.fitCenter()
                it.error(R.drawable.ic_vect_broken_image)
                it.placeholder(R.drawable.no_images)
            }
            Glide.with(itemView.context)
                .load(
                    R.drawable.trailer_image
                )
                .apply(requestOption)
                .into(itemView.iv_thumbnail)
        }
    }
}
