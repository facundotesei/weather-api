package challenge.redbee.services;

import challenge.redbee.domain.Board;
import challenge.redbee.domain.Locacion;


public interface BoardService {

    Iterable<Board> getAllBoards(Long id);

    Board getBoardById(Long id);

    Board saveBoard(Board board);

    Board addLocacion(Long id, String lugar);

    void removeLocacion(Long id, Long lugarId);

    void  deleteById(Long id);





}
