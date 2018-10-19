package challenge.redbee.controllers;

import challenge.redbee.domain.Board;
import challenge.redbee.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


@RestController
@RequestMapping(BoardController.BASE_URL)
public class BoardController {

    public static final String BASE_URL = "/boards";

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
//          Todos los boards de todos los users
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<Board> getListOfBoards(@RequestParam("user") Long userId){ return boardService.getAllBoards(userId); }

    @MessageMapping("/getBoard")
    @SendTo("/suscribers/board")
    public Board board(Long id) throws Exception {
        return boardService.getBoardById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Board> getBoardsByUser(@RequestParam("user") Long userId){ return boardService.getAllBoards(userId); }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Board getBoardById( @PathVariable Long id){ return boardService.getBoardById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Board createNewBoard(@RequestBody Board board){ return boardService.saveBoard(board); }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id){ boardService.deleteById(id); }

    @GetMapping({"/{id}/addLocacion"})
    @ResponseStatus(HttpStatus.OK)
    public Board addLocacion( @PathVariable Long id, @RequestParam("lugar") String lugar){ return boardService.addLocacion(id, lugar); }

    @GetMapping({"/{id}/removeLocacion"})
    @ResponseStatus(HttpStatus.OK)
    public void removeLocacion( @PathVariable Long id, @RequestParam("lugarId") Long lugarId){  boardService.removeLocacion(id, lugarId); }



}
