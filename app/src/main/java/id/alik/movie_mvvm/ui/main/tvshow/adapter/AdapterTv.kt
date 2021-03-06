package id.alik.movie_mvvm.ui.main.tvshow.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.common.utils.Constants.URL_POSTER
import id.alik.movie_mvvm.data.server.entity.response.tv.TvData
import kotlinx.android.synthetic.main.holder_item_movie.view.*


/**
 * Created by alzaichsank on 2019-06-24.
 */
class AdapterTv(
    private val context: Context,
    private val onClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterTv.ViewHolder>() {

    private val dataItem: ArrayList<TvData> = arrayListOf()

    fun getDataAt(position: Int): TvData {
        return dataItem[position]
    }

    fun setData(dataItemList: List<TvData>) {
        dataItem.clear()
        dataItem.addAll(dataItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_item_movie, parent, false)
        return ViewHolder(itemView, onClickListener)
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

        fun bindData(dataItem: TvData?) {
            itemView.tv_title.text = dataItem?.originalNameMovie
            itemView.tv_rating.text = dataItem?.voteAverageMovie
            itemView.tv_category.text = dataItem?.originalLanguage
            val requestOption = RequestOptions().let {
                it.diskCacheStrategy(DiskCacheStrategy.ALL)
                it.timeout(60000)
                it.centerCrop()
                it.error(R.drawable.ic_vect_broken_image)
                it.placeholder(R.drawable.no_images)
            }
            Glide.with(itemView.context)
                .load(
                    StringBuilder().append(URL_POSTER)
                        .append(dataItem?.backdropPathMovie).toString()
                )
                .apply(requestOption)
                .into(itemView.iv_poster)
        }
    }
}
