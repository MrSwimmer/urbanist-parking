package com.urbanist.parking.feature.report.di

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.feature.report.data.ReportApi
import com.urbanist.parking.feature.report.domain.repository.ReportRepository
import com.urbanist.parking.feature.report.domain.repository.impl.ReportRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ReportNetworkModule {
	@Provides
	@ActivityScope
	fun provideApi(retrofit: Retrofit): ReportApi = retrofit.create(ReportApi::class.java)

	@Provides
	@ActivityScope
	fun provideRepository(reportApi: ReportApi): ReportRepository = ReportRepositoryImpl(reportApi)
}