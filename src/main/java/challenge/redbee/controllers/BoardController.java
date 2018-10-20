package challenge.redbee.controllers;

import challenge.redbee.domain.Board;
import challenge.redbee.services.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(BoardController.BASE_URL)
@Api(value = "Board", description = "REST API for Board.", tags = { "Board" })
public class BoardController {

    public static final String BASE_URL = "/boards";

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @MessageMapping("/getBoard")
    @SendTo("/topic/board")
    public Board board(String id) throws Exception { return boardService.getBoardById(Long.parseLong(id)); }

    @ApiOperation(value="Get Boards By User Id", tags = { "Board" })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Board> getBoardsByUser(@RequestParam("user") Long userId) { return boardService.getAllBoards(userId); }

    @ApiOperation(value="Get Board By Id", tags = { "Board" })
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Board getBoardById( @PathVariable Long id) { return boardService.getBoardById(id); }

    @ApiOperation(value="Create Board", tags = { "Board" })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Board createNewBoard(@RequestBody Board board) { return boardService.saveBoard(board); }

    @ApiOperation(value="Delete Board", tags = { "Board" })
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id) { boardService.deleteById(id); }

    @ApiOperation(value="Add Location", tags = { "Board" })
    @GetMapping({"/{id}/addLocacion"})
    @ResponseStatus(HttpStatus.OK)
    public Board addLocacion( @PathVariable Long id, @RequestParam("lugar") String lugar) { return boardService.addLocacion(id, lugar); }

    @ApiOperation(value="Remove Location", tags = { "Board" })
    @GetMapping({"/{id}/removeLocacion"})
    @ResponseStatus(HttpStatus.OK)
    public void removeLocacion( @PathVariable Long id, @RequestParam("lugarId") Long lugarId) {  boardService.removeLocacion(id, lugarId); }

}
