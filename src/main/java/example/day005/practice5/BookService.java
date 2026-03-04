package example.day005.practice5;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // 도서 등록
    public boolean bAdd( BookDto bookDto ){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBname( bookDto.getBname() );
        bookEntity.setBpub( bookDto.getBpub() );
        bookEntity.setBwriter( bookDto.getBwriter() );
        BookEntity savedEntity = bookRepository.save(bookEntity);
        if( savedEntity.getBid()>=1)return true;
        return false;
    }

    // 도서 전체 조회
    public List<BookDto> bFindAll(){
        List<BookEntity> bookEntityList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        bookEntityList.forEach( bookEntity -> {
            BookDto bookDto = new BookDto();
            bookDto.setBid( bookEntity.getBid() );
            bookDto.setBname( bookEntity.getBname() );
            bookDto.setBwriter( bookEntity.getBwriter() );
            bookDto.setBpub( bookEntity.getBpub() );
            bookDtoList.add( bookDto );
        });
        return bookDtoList;
    }

    // 특정 도서 수정
    @Transactional // @Transactional의 역할: 여러 SQL들을 하나의 묶음으로 묶어 한 번에 처리해준다.
    public boolean bUpdate( BookDto bookDto ){
        Optional<BookEntity> optional = bookRepository.findById( bookDto.getBid() );
        if( optional.isPresent() ){
            BookEntity bookEntity = optional.get();
            bookEntity.setBname( bookDto.getBname() );
            bookEntity.setBwriter( bookDto.getBwriter() );
            bookEntity.setBpub( bookDto.getBpub() );
            return true;
        }
        return false;
    }

    // 특정 도서 삭제
    public boolean bDelete( int bid ){
        bookRepository.deleteById( bid );
        return true;
    }

}
