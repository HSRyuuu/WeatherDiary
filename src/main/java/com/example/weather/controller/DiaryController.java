package com.example.weather.controller;

import com.example.weather.domain.Diary;
import com.example.weather.service.DiaryService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @ApiOperation(value="일기 텍스트와 날씨를 이용하여 DB에 일기 저장", notes = "ApiOperation note")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져온다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간 중의 모든 일기 데이터를 가져온다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value="조회할 기간의 첫째 날", example = "2020-02-02 ") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value="조회할 기간의 마지막 날", example = "2020-02-02 ") LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("일기 데이터 업데이트")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody @ApiParam(value = "일기 내용") String text){
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 삭제한다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.deleteDiary(date);
    }
}
