package id.android.codebase.features.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import id.android.codebase.common.base.BaseFragment
import id.android.codebase.features.R
import id.android.codebase.features.databinding.FragmentHomeBinding
import id.android.codebase.features.home.adapter.ForecastDailyWeatherAdapter
import id.android.codebase.features.home.adapter.ForecastWeatherAdapter
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by inject()

    override fun getLayoutResId() = R.layout.fragment_home

    private val locationPermissionCode = 2
    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val forecastWeatherAdapter by lazy {
        ForecastWeatherAdapter()
    }

    private val forecastDailyWeatherAdapter by lazy {
        ForecastDailyWeatherAdapter()
    }

    override fun main() {
        checkConnection()
        hideActionBar()
        getLocation()
        initView()
        setupObserver()
    }

    private fun checkConnection() {
        binding?.apply {
            if (!isOnline()) {
                tvWarning.visibility = View.VISIBLE
            } else {
                tvWarning.visibility = View.GONE
            }
        }
    }

    private fun isOnline(): Boolean {
        val cmg =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
        }

        return false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private fun initView() {
        binding?.apply {
            rvWeatherHourly.adapter = forecastWeatherAdapter
            rvWeatherDaily.adapter = forecastDailyWeatherAdapter
            swipeRefresh.setOnRefreshListener {
                checkConnection()
                getLocation()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun setupObserver() {
        viewModel.apply {
            forecastWeatherData.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    forecastWeatherAdapter.submitList(it.toList())
                    forecastWeatherAdapter.notifyDataSetChanged()
                }
            }

            forecastDailyWeatherData.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    forecastDailyWeatherAdapter.submitList(it.toList())
                    forecastDailyWeatherAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getLocation() {
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                locationPermissionCode
            )
        } else {
            fusedLocationClient?.lastLocation
                ?.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        viewModel.getCurrentWeather(
                            location.latitude.toString(),
                            location.longitude.toString()
                        )
                    }
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.text_permission_granted),
                    Toast.LENGTH_SHORT
                ).show()
                getLocation()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.text_permission_denied),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
