package com.mysite.sitebackend.board.news.controller;

import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.news.dao.NewsBoardRepository;
import com.mysite.sitebackend.board.news.domain.NewsBoard;
import com.mysite.sitebackend.board.news.dto.NewsBoardDto;
import com.mysite.sitebackend.board.news.dto.NewsBoardListDto;
import com.mysite.sitebackend.board.news.service.NewsBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board/News")
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsBoardController {
    private final NewsBoardService newsBoardService;

    @RequestMapping("/")
    @ResponseBody
    private String index(){
        System.out.println("News");
        return "News";
    }
    //게시글 작성하기
    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author) {
        this.newsBoardService.save(subject, contents, author);
        System.out.println("성공적으로 저장되었습니다.");
        return "성공적으로 저장되었습니다.";
    }

    //게시글 목록 불러오기
    @GetMapping("/get")
    @ResponseBody
    private List<NewsBoardListDto> get(){
        return this.newsBoardService.findAll();
    }
    //게시글 불러오기
    @GetMapping("/get/{id}")
    @ResponseBody
    public NewsBoardDto getContnets(@PathVariable("id") Integer id){
        return this.newsBoardService.findById(id);
    }
    //게시글 수정하기
    @PatchMapping("/patch/{id}")
    @ResponseBody
    public NewsBoardDto PatchContents(@PathVariable("id") Integer id, @RequestParam("Subject") String subject, @RequestParam("Contents") String contents, @RequestParam("Author") String author){
        return this.newsBoardService.findByIdToPatch(id, subject, contents, author);
    }
    //게시글 삭제
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String DeleteContents(@PathVariable("id") Integer id){
        return this.newsBoardService.findByIdToDelete(id);
    }
}
