import axios from "axios"
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Board( props ){

    // 2. axios로 부터 (서버로 부터) 게시물들을 저장하는 상태변수
    const [ list , setList ] = useState( [] );

    // 1. axios 통신 (전체조회)
    const findAll = async() =>{
        try{
            const response = await axios.get("http://localhost:8080/api/board/list");
            const data = response.data;
            setList( data ); // 서버로 부터 받은 게시물들을 상태변수에 대입
        }catch(e){console.log(e)}
    }

    // 3. axios 실행 시점: 화면/컴포넌트가 열렸을 때 (한 번)
    useEffect( () => { findAll(); } , [] );


    return(<>
        <div>
            <h3>게시판 목록</h3>
            <table border={1}>
                <thead>
                    <tr>
                        <th>번호</th><th>제목</th>
                        <th>작성자</th><th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        list.map( board => {
                            return(
                                <tr>
                                    <td>{board.bno}</td>                                    
                                    <Link to={`/board/view?bno=${board.bno}`}>{board.btitle}</Link>
                                    <td>{board.mname}</td>
                                    <td>{board.createDate.split("T")[0]}</td>
                                </tr>
                            )
                        } )
                    }
                </tbody>
            </table>
        </div>
    </>)
}