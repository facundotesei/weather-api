package challenge.redbee.services;

import challenge.redbee.domain.Board;

import java.util.List;

public interface BoardService {

    List<Board> getAllBoards(Long userId);

    Board getBoardById(Long id);

    Board saveBoard(Board board, Long userId);

    Board addLocacion(Long id, String lugar);

    void removeLocacion(Long id, Long lugarId);

    void  deleteById(Long id, Long userId);





}
