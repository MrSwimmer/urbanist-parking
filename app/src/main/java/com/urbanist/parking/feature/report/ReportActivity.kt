package com.urbanist.parking.feature.report

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import com.urbanist.parking.R
import com.urbanist.parking.core.presentation.BaseActivity
import com.urbanist.parking.databinding.ActivityReportBinding
import javax.inject.Inject
import android.view.MenuItem
import com.urbanist.parking.feature.recomendation.RecommendationActivity
import com.urbanist.parking.utils.hasNull
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : BaseActivity<ActivityReportBinding>() {

	private var isLocationEnabled = true
	private var currentLocation = Location(LocationManager.GPS_PROVIDER)
	private var currentPhotoId = 0
	private var images: Array<Bitmap?> = arrayOfNulls(3)

	@Inject
	lateinit var viewModel: ReportViewModel

	override val layoutId: Int = com.urbanist.parking.R.layout.activity_report

	override fun initBinding() {
		requireBinding().viewModel = viewModel
	}

	override fun initViewModel(state: Bundle?) {
		viewModel.onBind()
		viewModel.setEventListener(eventsListener)
	}

	private val eventsListener: ReportViewModel.EventsListener = object : ReportViewModel.EventsListener {
		override fun getPhoto(i: Int) {
			openCamera(i)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initLocationListener()
	}

	@SuppressLint("MissingPermission")
	private fun initLocationListener() {
		val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
	}

	private val locationListener = object : LocationListener {
		override fun onLocationChanged(location: Location?) {
			currentLocation = location ?: return
		}

		override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

		}

		override fun onProviderEnabled(provider: String?) {
			isLocationEnabled = true
		}

		override fun onProviderDisabled(provider: String?) {
			isLocationEnabled = false
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.recommendation_item -> startActivity(Intent(this, RecommendationActivity::class.java))
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.report_menu, menu)
		return true
	}

	private fun openCamera(numPhoto: Int) {
		currentPhotoId = numPhoto
		val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		when (requestCode) {
			CAMERA_PIC_REQUEST -> if (resultCode == Activity.RESULT_OK) {
				val bitmap = data?.extras?.get(DATA_KEY) as Bitmap
				images[currentPhotoId] = bitmap
				viewModel.setBitmapToImageView(currentPhotoId, bitmap)
				sendButton.isEnabled = images.hasNull().not()
			}
		}
	}

	companion object {

		const val CAMERA_PIC_REQUEST = 20000
		const val DATA_KEY = "data"
	}

	override fun onDestroy() {
		super.onDestroy()
		viewModel.onUnbind()
	}
}