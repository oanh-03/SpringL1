package com.globits.da.service;

import com.globits.da.dto.MyFirstApiDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Phuong Oanh
 */
public interface MyFirstApiService {
     String myApi();
     MyFirstApiDto post(MyFirstApiDto myFirstApiDto);
     boolean sendFileText(MultipartFile fileText);
     boolean sendFileExcel(MultipartFile fileExcel);
}
