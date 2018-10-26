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

import static challenge.redbee.Constants.API_KEY;
import static challenge.redbee.Constants.ROOT_URL;


@Service
public class BoardServiceImpl implements BoardService {

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
    public Board saveBoard(Board board, Long userId) { //Deberia llamarse desde el User Controller
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario Inexistente."));
        Board savedBoard = boardRepository.save(board);
        user.getBoards().add(savedBoard);
        userRepository.save(user);              //Acceder a 2 Repo Parece Muy Costoso.
        return savedBoard;                      //No funciona cuando guardo una locacion en board y desp quiero crear un board
    }

    @Override
    public void deleteById(Long id, Long userId) {  //Deberia llamarse desde el User Controller
        User user = userRepository.findById(userId).get();
        user.getBoards().remove(boardRepository.findById(id).get()); //refactor
        userRepository.save(user);
    }

    @Override
    public Board addLocacion(Long id, String lugar) {

        String url = String.format("%s%s&q=%s",ROOT_URL,API_KEY,lugar);
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
        else throw new ResourceNotFoundException("Locacion Ya Existe En El Board.");
    }

    @Override
    public void removeLocacion(Long id, Long lugarId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board Inexistente."));
        Locacion locacion = locacionRepository.findById(lugarId)
                .orElseThrow(() -> new ResourceNotFoundException("Locacion Inexistente.")); //Que pasa si la locacion esta en otro board?
        if(board.getLocaciones().contains(locacion)) {
            board.getLocaciones().remove(locacion);                                             // Parece que se borra de todos los boards.
//        locacionRepository.deleteById(locacion.getId());
            boardRepository.save(board);
        }
        else throw new ResourceNotFoundException("Locacion Existe pero no dentro de este Board.");

    }

}
