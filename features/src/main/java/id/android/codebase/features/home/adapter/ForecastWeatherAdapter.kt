package id.android.codebase.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.android.codebase.common.utils.keys.Keys
import id.android.codebase.domain.state.WeatherUIState
import id.android.codebase.features.databinding.ItemWeatherHourlyBinding

class ForecastWeatherAdapter : ListAdapter<WeatherUIState.ItemContentForecastWeather, ForecastWeatherAdapter.ViewHolder>(object : DiffUtil.ItemCallback<WeatherUIState.ItemContentForecastWeather>() {
    override fun areItemsTheSame(oldItem: WeatherUIState.ItemContentForecastWeather, newItem: WeatherUIState.ItemContentForecastWeather) = oldItem.time == oldItem.time
    override fun areContentsTheSame(oldItem: WeatherUIState.ItemContentForecastWeather, newItem: WeatherUIState.ItemContentForecastWeather) = oldItem.time == oldItem.time
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {
                binding.tvHourItem.text = time
                binding.tvTempHourlyItem.text = temp
                Glide.with(holder.itemView.context)
                    .load(Keys.BASE_URL_IMAGE + icon + Keys.FORMAT_IMAGE)
                    .into(binding.ivIconWeatherHourly)
                binding.tvWeatherItem.text = weatherDescription
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherHourlyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemWeatherHourlyBinding)
        : RecyclerView.ViewHolder(binding.root)
}