package com.moments.claw.service;

import com.moments.claw.domain.common.response.R;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	R<?> uploadAvatar(MultipartFile img);

	R<?> uploadImg(MultipartFile img);
}
