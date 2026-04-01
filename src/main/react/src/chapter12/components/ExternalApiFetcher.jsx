import { useEffect, useState } from "react";
import axios from 'axios';

function RandomUser( props ){
    
    const [ myJSON , setMyJSON ] = useState({results: []}); // vs. const myJSON = { result : [] }
    // 상태변수 선언(렌더링하려고). 초기값 객체(배열) 타입으로 만듦.
        // 렌더링 왜: 서버와 통신하여 자료를 가져오는 시간을 기다리지 않고 서버가 응답하면 다시 렌더링되는 이점 때문에 상태 변수를 사용한다.
    
    // 컴포넌트(함수)의 특정한 시점(마운트, 언마운트, 업데이트 -> 생명주기)에 훅(갈고리) 이용한 추가 작업이 가능하다.
    const axiosApi = async() =>{
        const response = await axios.get('https://api.randomuser.me?results=10');   // 탈란드로 확인하니 자료 이름이 results.
        const result = response.data;
        setMyJSON( result );
    }
    useEffect( function(){
        // 마운트 자리. 최초로 마운트될 때 서버로 부터 정보를 요청하자. 주로 REST API(AXIOS) (-> 이번엔 위로 뺐음)
        axiosApi();
    }, []);

    // [2] <table> 마크업 내 <tr> 구성하는 함수
    let trTag = myJSON.results.map((data)=>{
        // spring에서 html에 넣던 거랑 동일.
            return(
                <tr>
                    <td> <img src={ data.picture.thumbnail} /></td>
                    <td> <a href="/"> {data.login.username} </a></td>
                    <td> {data.name.title} {data.name.first} {data.name.last} </td>
                    <td> {data.net} </td>
                    <td> {data.email} </td>
                </tr>
            );
    });

    // [3]
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>사진</th><th>로그인</th><th>이름</th>
                        <th>국가</th><th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    {trTag}
                </tbody>
            </table>
        </div>
    )
}


// ExternalApiFetcher.jsx
export default function ExternalApiFetcher( props ){
    return(<>
        <h2> 외부 서버 통신 </h2>
        <RandomUser></RandomUser>
    </>)
}