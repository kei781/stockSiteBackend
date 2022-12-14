package com.mysite.sitebackend.board.Inform.service;

import com.mysite.sitebackend.board.Inform.dao.InformBoardRepository;
import com.mysite.sitebackend.board.Inform.domain.InformBoard;
import com.mysite.sitebackend.board.Inform.dto.InformBoardDto;
import com.mysite.sitebackend.board.Inform.dto.InfromBoardListDto;
import com.mysite.sitebackend.board.coin.domain.CoinBoard;
import com.mysite.sitebackend.board.coin.dto.CoinBoardDto;
import com.mysite.sitebackend.board.coin.dto.CoinBoardListDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InformBoardService {
    private final InformBoardRepository boardRepository;
    private final ModelMapper modelMapper;
    //게시글 작성하기
    public void save(String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        InformBoard c1 = new InformBoard();
        c1.setSubject(subject);
        c1.setContents(contents);
        c1.setAuthor(author);
        c1.setViews(0);
        c1.setDate(formatedNow);
        this.boardRepository.save(c1);
        System.out.println("성공적으로 저장되었습니다.");
    }
    //게시글 리스트 불러오기
    public List<InfromBoardListDto> findAll(){
        List<InformBoard> informBoards = boardRepository.findAll();
        List<InfromBoardListDto> infromBoardListDto =
                informBoards.stream()
                .map(informBoard1 -> modelMapper.map(informBoard1, InfromBoardListDto.class))
                .collect(Collectors.toList());
        return infromBoardListDto;
    }
    //게시글 불러오기
    public InformBoardDto findById(Integer id){
        InformBoard board = boardRepository.findById(id).get();
        InformBoardDto boardDto = modelMapper.map(board, InformBoardDto.class);

        return boardDto;
    }

    //게시글 수정
    public InformBoardDto findByIdToPatch(Integer id, String subject, String contents, String author){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formatedNow = now.format(formatter);

        Optional<InformBoard> opBoard = boardRepository.findById(id);
        if(opBoard.isPresent()){
            InformBoard board = opBoard.get();
            board.setSubject(subject);
            board.setSubject(contents);
            board.setSubject(author);
            board.setDate(formatedNow);
            boardRepository.save(board);
        }
        InformBoard board = boardRepository.findById(id).get();
        InformBoardDto boardDto = modelMapper.map(board, InformBoardDto.class);
        return boardDto;
    }
    //게시글 삭제
    public String findByIdToDelete(Integer id){
        this.boardRepository.deleteById(id);
        return id + "번 게시판의 삭제가 완료되었습니다.";
    }

}
