package id.alik.movie_mvvm.ui.main.home.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.alik.movie_mvvm.R
import id.alik.movie_mvvm.data.server.entity.response.movie.MovieTrailerData
import kotlinx.android.synthetic.main.holder_trailer_list.view.*


/**
 * Created by alzaichsank on 2019-06-24.
 */
class AdapterMovieTrailer(
    private val context: Context,
    private val onClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<AdapterMovieTrailer.ViewHolder>() {

    private val dataItem: ArrayList<MovieTrailerData> = arrayListOf()

    fun getDataAt(position: Int): MovieTrailerData {
        return dataItem[position]
    }

    fun setData(dataItemList: List<MovieTrailerData>) {
        dataItem.clear()
        dataItem.addAll(dataItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_trailer_list, parent, false)
        return ViewHolder(itemView, onClickListener)
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    override fun onBindViewHolder(holder: AdapterMovieTrailer.ViewHolder, position: Int) {
        holder.bindData(dataItem[position])
    }

    class ViewHolder(itemView: View, onClickListener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClickListener(adapterPosition)
            }
        }

        fun bindData(dataItem: MovieTrailerData?) {
            itemView.tv_title_trailer.text = dataItem?.nameTrailerMovie
            val requestOption = RequestOptions().let {
                it.diskCacheStrategy(DiskCacheStrategy.ALL)
                it.timeout(60000)
                it.centerCrop()
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
