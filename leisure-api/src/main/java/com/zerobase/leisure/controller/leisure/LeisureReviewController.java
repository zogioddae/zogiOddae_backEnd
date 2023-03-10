package com.zerobase.leisure.controller.leisure;


import com.zerobase.leisure.domain.dto.LeisureDto;
import com.zerobase.leisure.domain.dto.LeisureReviewDto;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import com.zerobase.leisure.domain.form.AddLeisureReviewForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.LeisureReviewService;
import com.zerobase.leisure.service.SellerLeisureService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/leisure/review")
@RequiredArgsConstructor
public class LeisureReviewController {

	private final LeisureReviewService leisureReviewService;

	@PostMapping
	public WebResponseData<LeisureReviewDto> addLeisureReview(@RequestBody AddLeisureReviewForm form) {
		return WebResponseData.ok(
			LeisureReviewDto.from(leisureReviewService.addLeisureReview(form)));
	}

	/*
	상품 조회 시 모든 리뷰 조회
	 */
	@GetMapping
	public WebResponseData<List<LeisureReviewDto>> getLeisure(@RequestParam Long leisureId) {
		return WebResponseData.ok(leisureReviewService.getAllLeisureReview(leisureId));
	}

	@PutMapping
	public WebResponseData<LeisureReviewDto> updateLeisureReview(@RequestParam Long reviewId, @RequestBody AddLeisureReviewForm form) {
		return WebResponseData.ok(
			LeisureReviewDto.from(leisureReviewService.updateLeisureReview(reviewId,form)));
	}

	/*
	샐러가 답글 달기
   */
	@PutMapping("/reply")
	public WebResponseData<LeisureReviewDto> updateReplyLeisure(@RequestParam Long reviewId, @RequestBody AddLeisureReviewForm form) {
		return WebResponseData.ok(
			LeisureReviewDto.from(leisureReviewService.updateReplyLeisureReview(reviewId,form)));
	}

	@DeleteMapping
	public WebResponseData<String> deleteLeisureReview(@RequestParam Long reviewId) {
		leisureReviewService.deleteLeisureReview(reviewId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

}
