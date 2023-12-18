package com.donggukthon.Showman.service.posting;

import com.donggukthon.Showman.common.CommonResponse;
import com.donggukthon.Showman.dto.posting.request.PostingDescriptionRequest;
import com.donggukthon.Showman.dto.posting.request.PostingLocationRequest;
import com.donggukthon.Showman.dto.posting.response.PostingDescriptionResponse;
import com.donggukthon.Showman.dto.posting.response.PostingImageResponse;
import com.donggukthon.Showman.dto.posting.response.PostingLocationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostingService {

    PostingImageResponse postingImage(MultipartFile multipartFile) throws IOException;

    PostingDescriptionResponse postingDescription(PostingDescriptionRequest postingDescriptionRequest);

    PostingLocationResponse postingLocation(PostingLocationRequest postingLocationRequest);
}
