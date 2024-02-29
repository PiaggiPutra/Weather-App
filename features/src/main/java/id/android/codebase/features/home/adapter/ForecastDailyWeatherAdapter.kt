package id.android.codebase.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.android.codebase.domain.state.WeatherUIState
import id.android.codebase.features.databinding.ItemWeatherDailyBinding

class ForecastDailyWeatherAdapter : ListAdapter<WeatherUIState.ItemDailyContentForecastWeather, ForecastDailyWeatherAdapter.ViewHolder>(object : DiffUtil.ItemCallback<WeatherUIState.ItemDailyContentForecastWeather>() {
    override fun areItemsTheSame(oldItem: WeatherUIState.ItemDailyContentForecastWeather, newItem: WeatherUIState.ItemDailyContentForecastWeather) = oldItem.day == oldItem.day
    override fun areContentsTheSame(oldItem: WeatherUIState.ItemDailyContentForecastWeather, newItem: WeatherUIState.ItemDailyContentForecastWeather) = oldItem.day == oldItem.day
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(getItem(position)) {
                binding.tvDay.text = day
                binding.tvTempDaily.text = temp
                binding.tvWeatherDaily.text = weather
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherDailyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    inner class ViewHolder(val binding: ItemWeatherDailyBinding)
        : RecyclerView.ViewHolder(binding.root)
}