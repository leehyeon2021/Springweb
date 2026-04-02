import axios from "axios";
import { useEffect, useState } from "react";
import { useSearchParams, Link } from "react-router-dom";

export default function Detail( props ){

    // 1. 쿼리스트링의 id 가져오기
    const [ searchParams , setSearchParams ] = useSearchParams();
    const id = searchParams.get( "id" );

    // 3. Rest API 결과를 담는 상태 변수
    const [ task, setTask ] = useState( null ); // 초기값 null

    // 2. Rest API 이용하여 개별조회
    const getTaskDetail = async() => {
        const response = await axios.get(`http://localhost:8080/api/task/detail?id=${id}`);
        const data = response.data;
        setTask( data );
    }

    // 4. 최초 1번 Rest API 요청
    useEffect( () => { getTaskDetail(); } , [] );

    // 5. 만약에 최초 렌더링이면
    if(task==null) return <div> 가져오는 중... </div>

    return(<>
        <br/><Link to="/">뒤로 가기</Link>
        <h3> 상세 페이지 </h3>
        <p> 번호: {task.id} </p>
        <p> 제목: {task.title} </p>
        <p> 요청내용: {task.content} </p>
        <p> 요청자명: {task.requester} </p>
        <p> 상태: {task.state} </p>
        <p> 등록일: {task.createAt} </p>
    </>)
    
}