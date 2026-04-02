import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios"

export default function Index( props ){

    // Rest API에게 받은 자료 저장하는 상태(state)변수
    const [ taskAry, setTaskAry ] = useState([ ]); // 초기값은 빈배열, 추후에 rest 결과 담기

    // Rest API에게 전체조회 자료 요청
     const taskList = async() => {
        try{
            const response = await axios.get( 'http://localhost:8080/api/task/list' );  
            const data = response.data;                                             
            setTaskAry( data );
        }catch(e){ console.log( e ) }
    }

    // 컴포넌트 생명주기. Rest API 통신 응답 처리된 후 재렌더링(새로고침)
    useEffect( ( ) => { taskList(); } , [ ] ) // 의존성 배열이 빈배열이면 최초 1번 실행

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
                                    <button>상세보기</button>
                                    <button>수정</button>
                                    <button>삭제</button>
                                </td>
                            </tr>
                        )
                    })
                }
            </tbody>
        </table>
    </>)
}