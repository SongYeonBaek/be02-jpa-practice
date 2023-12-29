//package com.example.demo.board.repository;
//
//import com.example.demo.board.model.BoardDto;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class BoardDao {
//    private JdbcTemplate jdbcTemplate;
//
//    public BoardDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public BoardDto read(int id){
//        String sql = "SELECT * FROM Product WHERE Product_ID=?";
//
//        return jdbcTemplate.queryForObject(
//                sql,
//                BeanPropertyRowMapper.newInstance(BoardDto.class),
//                id
//        );
//    }
//
////    public void create(){
////        String sql = "INSERT INTO Product(name, Img, Information) VALUES(?, ?, ?);";
////    }
//
//}
