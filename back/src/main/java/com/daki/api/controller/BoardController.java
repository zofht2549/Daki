package com.daki.api.controller;

import com.daki.api.request.BoardModifyReqDto;
import com.daki.api.request.BoardRegistReqDto;
import com.daki.api.response.BoardListResDto;
import com.daki.api.response.BoardModifyResDto;
import com.daki.api.response.BoardRegistResDto;
import com.daki.api.response.BoardResDto;
import com.daki.api.service.BoardService;
import com.daki.db.entity.Board;
import com.daki.db.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/list")
    public List<BoardListResDto> getList() {
        return boardService.getList();
    }

    @GetMapping("/{boardNo}")
    public BoardResDto getBoard(@PathVariable Long boardNo) {
        return  boardService.findById(boardNo);
    }

    @PostMapping
    public Long regist(@RequestBody BoardRegistReqDto reqDto, Authentication authentication) {
        User user = (User) authentication.getDetails();
        return boardService.register(reqDto, user);
    }

    @PatchMapping("/{boardNo}")
    public Long modify (@PathVariable Long boardNo, @RequestBody BoardModifyReqDto reqDto, Authentication authentication) {
        User user = (User) authentication.getDetails();
        BoardResDto res = boardService.findById(boardNo);
        if(user.getUserNickname() != res.getNickName()) {
            throw new RuntimeException("권한이 없습니다");
        }
        boardService.modify(boardNo, reqDto);
        return res.getBoardNo();
    }

    @DeleteMapping("/{boardNo}")
    public Long delete (@PathVariable Long boardNo, Authentication authentication) {
        User user = (User) authentication.getDetails();

        if(user.getUserNickname() != boardService.findById(boardNo).getNickName()) {
            throw new RuntimeException("권한이 없습니다");
        }

        return boardService.delete(boardNo);
    }
}

