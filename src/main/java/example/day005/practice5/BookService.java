package example.day005.practice5;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // 주석 써보기

    // 도서 등록
    public boolean bAdd(@NonNull BookDto bookDto ){
        // 1. Dto -> Entity
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBname( bookDto.getBname() );
//        bookEntity.setBpub( bookDto.getBpub() );
//        bookEntity.setBwriter( bookDto.getBwriter() );
            // 빌더 패턴 써보기! (Entity로 가고 있어요~ Entity에 @Builder가 있어야 해요~)
        BookEntity bookEntity = BookEntity.builder()
                .bname(bookDto.getBname())
                .bwriter(bookDto.getBwriter())
                .bpub(bookDto.getBpub())
                .build();
        // 2. 직접적인 sql이 아닌 JPA 이용한 등록.
        BookEntity savedEntity = bookRepository.save(bookEntity);
        // 3. 저장 여부는 pk 생성 여부로 판단.
        if( savedEntity.getBid()>=1)return true;
        // 4. boolean 반환.
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
