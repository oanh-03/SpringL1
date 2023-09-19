package com.globits.da.rest;

import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.impl.MyFirstApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Phuong Oanh
 */
@RestController
@RequestMapping("/api")
public class RestMyFirstApiController {
    @Autowired
    private MyFirstApiServiceImpl myFirstApiService;

    @GetMapping()
    public String getMyFirst() {
        return "My First Api";
    }

    @GetMapping("/myFirst")
    public String getMyFirstService() {
        return myFirstApiService.myApi();
    }

    @PostMapping("/myFirstDto")
    public MyFirstApiDto postMyFirstDto(@RequestBody MyFirstApiDto myFirstApiDto) {
        return myFirstApiService.post(myFirstApiDto);
    }

    @PostMapping("/myFirstApi")
    public MyFirstApiDto postMyFirstService(@RequestBody MyFirstApiDto myFirstApiDto) {
        return myFirstApiService.post(myFirstApiDto);
    }

    @PostMapping("/myFirstApiRequest")
    public MyFirstApiDto postMyFirstRequest(@RequestParam(name = "code") String code,
                                            @RequestParam(name = "name") String name,
                                            @RequestParam(name = "age") Integer age
    ) {
        MyFirstApiDto myFirstApiDto = new MyFirstApiDto(code, name, age);
        return myFirstApiDto;
    }

    @PostMapping("/myFirstApiPath/{code}/{name}/{age}")
    public MyFirstApiDto postMyFirstPath(@PathVariable String code,
                                         @PathVariable String name,
                                         @PathVariable Integer age
    ) {
        MyFirstApiDto myFirstApiDto = new MyFirstApiDto(code, name, age);
        return myFirstApiDto;
    }

    @PostMapping("/myFirstApi/NoRequestBody")
    public MyFirstApiDto postMyFirstNoRequest(MyFirstApiDto myFirstApiDto) {
        return myFirstApiService.post(myFirstApiDto);
    }

    @PostMapping("/myFirstApi/NoRequestParam")
    public MyFirstApiDto postMyFirstRequestParam(String code, String name, Integer age) {
        MyFirstApiDto myFirstApiDto = new MyFirstApiDto(code, name, age);
        return myFirstApiDto;
    }
    @PostMapping("/postFileText")
    public ResponseEntity<?> postFileText(@RequestParam("file") MultipartFile fileText){
        if(myFirstApiService.sendFileText(fileText)){
            return new ResponseEntity<>("Thành công", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Thất bại",HttpStatus.EXPECTATION_FAILED);
        }
    } @PostMapping("/postFileExcel")
    public ResponseEntity<?> postFileExcel(@RequestParam("file") MultipartFile fileText){
        if(myFirstApiService.sendFileExcel(fileText)){
            return new ResponseEntity<>("Thành công", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Thất bại",HttpStatus.EXPECTATION_FAILED);
        }
    }
}

