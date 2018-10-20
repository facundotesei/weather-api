package challenge.redbee.services;

import challenge.redbee.domain.Board;
import challenge.redbee.domain.Locacion;
import challenge.redbee.domain.User;
import challenge.redbee.exception.ResourceNotFoundException;
import challenge.redbee.repositories.BoardRepository;
import challenge.redbee.repositories.LocacionRepository;
import challenge.redbee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;


@Service
public class BoardServiceImpl implements BoardService  {

    public static final String API_KEY = "ac7c783b90a242b9dcbb219ff24b56b6"; //PONER LA API EN ENV VARIABLES
    public static final String ROOT_URL = "http://api.openweathermap.org/data/2.5/weather?appid=";

    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private LocacionRepository locacionRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository, LocacionRepository locacionRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.locacionRepository = locacionRepository;
    }

    @Override
    public Iterable<Board> getAllBoards(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario Inexistente."));
        return user.getBoards();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Board No Existe."));
    }

    @Override
    public Board saveBoard(Board board) { return boardRepository.save(board); }

    @Override
    public void deleteById(Long id) { boardRepository.deleteById(id); }

    @Override
    public Board addLocacion(Long id, String lugar) {

        String url = String.format("%s%s&q=%s,us",ROOT_URL,API_KEY,lugar); //Poner como Final o como env variable
        RestTemplate restTemplate = new RestTemplate();
        Locacion locacion = restTemplate.getForObject(url, Locacion.class);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board Inexistente."));
        Set<Locacion> locaciones = new HashSet<>(board.getLocaciones());
        if(!locaciones.contains(locacion)){                                 //Poner en Metodo aparte
            if(locacionRepository.existsById(locacion.getId())){
                locaciones.add(locacionRepository.findById(locacion.getId()).get());
            }
            else{
                locacion = locacionRepository.save(locacion);
                locaciones.add(locacion);
            }
            board.setLocaciones(locaciones);
            return boardRepository.save(board);
        }
        else throw new ResourceNotFoundException("Locacion Ya Existe En El Board");
    }

    @Override
    public void removeLocacion(Long id, Long lugarId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board Inexistente."));
        Locacion locacion = locacionRepository.findById(lugarId)
                .orElseThrow(() -> new ResourceNotFoundException("Locacion Inexistente")); //Que pasa si la locacion esta en otro board?
        board.getLocaciones().remove(locacion);                                             // Parece que se borra de todos los boards.
        locacionRepository.deleteById(locacion.getId());
        boardRepository.save(board);
    }

}
