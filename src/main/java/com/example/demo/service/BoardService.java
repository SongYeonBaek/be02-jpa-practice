package com.example.demo.service;

import com.example.demo.model.BoardDto;
import com.example.demo.repository.BoardDao;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardDto read(int id){
        return boardDao.read(id);
    }

    /*
    public boolean post(BoardDto boardDto){
        return true;
    }

    public boolean edit(BoardDto boardDto){
        return true;
    }

    public boolean delete(BoardDto boardDto){
        return true;
    }

     */
}
