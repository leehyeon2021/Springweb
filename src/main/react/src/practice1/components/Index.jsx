import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios"

export default function Index( props ){

    // 1. Rest API에게 받은 자료 저장하는 상태(state)변수
    const [ taskAry, setTaskAry ] = useState([ ]); // 초기값은 빈배열, 추후에 rest 결과 담기

    // 2. Rest API에게 전체조회 자료 요청
     const taskList = async() => {
        try{
            const response = await axios.get( 'http://localhost:8080/api/task/list' );  
            const data = response.data;                                             
            setTaskAry( data );
        }catch(e){ console.log( e ) }
    }

    // 3. 컴포넌트 생명주기. Rest API 통신 응답 처리된 후 재렌더링(새로고침)
    useEffect( ( ) => { taskList(); } , [ ] ) // 의존성 배열이 빈배열이면 최초 1번 실행

    // 4. 삭제 요청 REST API. delete update , write 존재하는 키워드
    const taskDelete = async( id ) => {
        const result = confirm( '정말 취소할까요? '); 
        if( result == true){
            const response = await axios.delete('http://localhost:8080/api/task?id='+id);
            console.log(response.status);
            // 본문이 없으면 본문으로 분기하지 않고 HTTP 응답 코드 분기
             if( response.status == 204 ){ alert('삭제성공'); taskList(); } 
            else{ alert('삭제실패'); }
        }
    }

    return(<>
        <h3> 전체조회 </h3>
        <Link to="/task/create"> 등록 </Link>
        <table>
            <thead border = "1">
                <tr>
                    <th>번호</th><th>제목</th><th>요청자명</th>
                    <th>상태</th><th>등록일</th><th>비고</th>
                </tr>
            </thead>
            <tbody>
                {
                    taskAry.map( (task) => {
                        return(
                            <tr key={task.id}>
                                <td>{task.id}</td><td>{task.title}</td><td>{task.reqester}</td>
                                <td>{task.content}</td><td>{task.updateAt.split('T')[0]}</td>
                                <td>
                                    <button><Link to={`/task/detail?id=${task.id}`}>상세보기</Link></button>
                                    <button>수정</button>
                                    <button onClick={()=>{ taskDelete(task.id); }}>삭제</button>
                                </td>
                            </tr>
                        )
                    })
                }
            </tbody>
        </table>
    </>)
}