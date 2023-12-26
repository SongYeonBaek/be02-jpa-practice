package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import com.example.demo.board.model.BoardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
       this.boardService = boardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<Object> read(int id){
        BoardDto boardDto = boardService.read(id);
        return ResponseEntity.ok().body(boardDto);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public ResponseEntity<Object> edit(BoardDto boardDto){
        if(boardService.view(boardDto)){
            return ResponseEntity.ok().body("게시물 수정 성공");
        }
        else {
            return ResponseEntity.badRequest().body("게시물 수정 실패");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseEntity<Object> delete(BoardDto boardDto){
        if(boardService.view(boardDto)){
            return ResponseEntity.ok().body("게시물 삭제 성공");
        }
        else {
            return ResponseEntity.badRequest().body("게시물 삭제 실패");
        }
    }

        @RequestMapping(method = RequestMethod.POST, value = "/post")
    public ResponseEntity<Object> post(BoardDto boardDto){
        if(boardService.view(boardDto)){
            return ResponseEntity.ok().body("게시물 등록 성공");
        }
        else {
            return ResponseEntity.badRequest().body("게시물 등록 실패");
        }
    }


}
