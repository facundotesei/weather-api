package challenge.redbee.services;

import challenge.redbee.domain.Board;
import challenge.redbee.domain.Locacion;
import challenge.redbee.domain.User;
import challenge.redbee.repositories.BoardRepository;
import challenge.redbee.repositories.LocacionRepository;
import challenge.redbee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BoardServiceImpl implements BoardService  {

    public static final String API_KEY = "ac7c783b90a242b9dcbb219ff24b56b6";
    public static final String ROOT_URL = "http://api.openweathermap.org/data/2.5/weather?appid=";

    private BoardRepository boardRepository;
//    private UserRepository userRepository;
//    private LocacionRepository locacionRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
//        this.userRepository = userRepository;
//        this.locacionRepository = locacionRepository;
    }

    @Override
    public Iterable<Board> getAllBoards(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if(user != null) Arreglar esto
//        return user.getBoards();
return null;
    }

    @Override
    public Board getBoardById(Long id) { return boardRepository.findById(id).orElse(null); }

    @Override
    public Board saveBoard(Board board) { return boardRepository.save(board); }

    @Override
    public void deleteById(Long id) { boardRepository.deleteById(id); }

    @Override
    public Board addLocacion(Long id, String lugar) {

        String url = String.format("%s%s&q=%s,us",ROOT_URL,API_KEY,lugar);
        RestTemplate restTemplate = new RestTemplate();
        Locacion locacion = restTemplate.getForObject(url, Locacion.class);
        Board board = boardRepository.findById(id).orElse(null);
        board.getLocaciones().add(locacion); //si ya existe no deberia agregarla, cambiar la DS o otra cosa
        return boardRepository.save(board);
    }

    @Override
    public void removeLocacion(Long id, Long lugarId) {
//        Board board = boardRepository.findById(id).orElse(null);
//        Locacion locacion = locacionRepository.findById(lugarId).get(); //arreglar los elseNUll
//        board.getLocaciones().remove(locacion);

    }
}
