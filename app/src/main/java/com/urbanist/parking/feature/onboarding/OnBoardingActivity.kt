package com.urbanist.parking.feature.onboarding

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.ramotion.paperonboarding.PaperOnboardingPage
import com.urbanist.parking.R
import com.urbanist.parking.core.presentation.BaseActivity
import com.urbanist.parking.databinding.ActivityOnboardingBinding
import com.urbanist.parking.feature.onboarding.paper.UPaperOnboardingFragment
import java.util.*


class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>() {

    override fun initBinding() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewModel(state: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val layoutId: Int = R.layout.activity_onboarding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnBoarding()
    }

    private fun setOnBoarding() {
        val onBoardingFragment = UPaperOnboardingFragment.newInstance(dataForOnBoarding())

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment)
        fragmentTransaction.commit()

        onBoardingFragment.setOnRightOutListener {
            finish()
        }

    }

    private fun dataForOnBoarding(): ArrayList<PaperOnboardingPage> {
        val firstPage = PaperOnboardingPage(
            getString(R.string.onboarding_title_1),
            getString(R.string.onboarding_desc_1),
            ContextCompat.getColor(this, R.color.onboard_1),
            0, 0
        )
        val secondPage = PaperOnboardingPage(
            getString(R.string.onboarding_title_2),
            getString(R.string.onboarding_desc_2),
            ContextCompat.getColor(this, R.color.onboard_2),
            0, 0
        )
        val thirdPage = PaperOnboardingPage(
            getString(R.string.onboarding_title_3),
            getString(R.string.onboarding_desc_3),
            ContextCompat.getColor(this, R.color.onboard_3),
            0, 0
        )
        val firthPage = PaperOnboardingPage(
            getString(R.string.onboarding_title_4),
            getString(R.string.onboarding_desc_4),
            ContextCompat.getColor(this, R.color.onboard_4),
            0, 0
        )
        val fifthPage = PaperOnboardingPage(
            getString(R.string.onboarding_title_5),
            getString(R.string.onboarding_desc_5),
            ContextCompat.getColor(this, R.color.onboard_5),
            0, 0
        )

        return arrayListOf(firstPage, secondPage, thirdPage, firthPage, fifthPage)
    }
}