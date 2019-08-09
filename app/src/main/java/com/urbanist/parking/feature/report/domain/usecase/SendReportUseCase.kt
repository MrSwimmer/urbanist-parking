package com.urbanist.parking.feature.report.domain.usecase

import com.urbanist.parking.feature.report.domain.entity.Report
import com.urbanist.parking.feature.report.domain.repository.ReportRepository
import io.reactivex.Completable
import javax.inject.Inject

// TODO завезти rx usecases из memes
interface SendReportUseCase {

	operator fun invoke(report: Report): Completable
}

class SendReportUseCaseImpl @Inject constructor(
	private val repository: ReportRepository
) : SendReportUseCase {

	override fun invoke(report: Report): Completable = repository.sendReport(report)
}