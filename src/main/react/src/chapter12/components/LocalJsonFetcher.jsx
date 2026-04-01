import { useEffect, useState } from "react";
import axios from 'axios';

const GlobalTop = ( props ) =>{
    
    console.log( "[1] 컴포넌트 실행");

    const[ myList , setMyList ] = useState( [ ] ); // 빈배열을 갖는 상태변수이며 추후 AXIOS 결과를 담는다.

    // 1. axios 사용하기. (설치: npm install axios) (임포트: import axios from 'axios')
    // async function axios(){}
    // const axios = async( ) => {} 함수에 변수 담기는 자바 안 됨(당연)
    const axios1 = async( ) => {                                // axios 통신하는 함수를 하나 만든다. async 동기화를 한다.
        const response = await axios.get('./json/myData.json'); // await 동기화된 axios 통신을 한다. (get mapping)
                                                                // response에는 HTTp 응답 관련 정보들이 객체로 들어있다.
                                                                // 그중에 response.data는 실질적인 결과물(내용물/body) 있다.
        const result = response.data;                           // 통신 결과 내 .data가 실질적인 결과물(내용물/body) 가져온다.
        setMyList( result );                                    // axios 통신 결과를 상태변수에 대입한다. !! 렌더링 되면서 console.log( "[1] 컴포넌트 실행");이 다시 뜬다.
                                                                    // 첫 번쩨는 일단 최초로 사용자에게 보여주는 화면, 두 번째는 렌더링 되고 백에서 가져온 화면.
    }

    /*  1. useEffect( () => { } )                          : 최초 실행, 렌더링할 때마다 실행 (무한루프로 빠지기 쉽기에 잘 안 씀.)
        2. useEffect( () => { } , [ ] )                    : 최초 실행
        3. useEffect( () => { } , [ 상태변수1, 상태변수2 ] )  : 최초 실행, 특정 상태 변경될 때 마다 실행    */
    useEffect( () => {
        console.log( '[3] useEffect 실행' )
        axios1();
    }, [ ] ) // [ ] 빠지면 무한루프 빠짐.

    // [3] 현재 상태( myList => json => axios ) 정보를 반복하여 html 구성 함수.
    // 리스트/배열변수명.map( (반복변수) => { return (<> JSX </> ) } ) 주로 HTML 구성할 때 사용한다.
    let listTag = myList.map( (data) => {
        // 첫 번째 반복 data = {"num":1, "id":"yu", "name":"유비", "cell":"(02) 235-1111"}
        // 두 번째 반복 data = {"num":2, "id":"kwan", "name":"관우", "cell":"(051) 235-2222"}
        // 세 번째 반복 data = {"num":3, "id":"jang", "name":"장비", "cell":"(031) 235-3333"}

        //  onClick = { 함수선언또는함수명 }

        return(
            <li key={ data.id }>
                <a href={ data.id } data-id={ data.num } onClick={ (e) => {
                    e.preventDefault(); // e.preventDefault(): a마크업 관련된 기본 기능 제거(깜빡이는 기능 제거)
                    props.myLinkClick(e.target.dataset.id); 
                }}> {data.id} </a>
            </li>
        );
    });
    // 변수예측: listTag = <li><a> yu </a></li> <li><a>kwan</a></li> <li><a>jang</a></li>

    console.log( '[2] return 실행');
    return(<>
        <nav>
            <ul>
                {/*
                    myList.map( (data) => {
                        return(
                            <li> <a > {data.id} </a></li>
                        );
                    })*/
                }
                {/* 위 방법 vs. 아래 방법 */}
                { listTag }
            </ul>
        </nav>
    </>)

}

// LocalJsonFetcher.jsx
export default function LocalJsonFetcher( props ){

    // 
    const[ myResult , setMyResult ] = useState( {} ); // 상태변수 , 배열 아닌 객체. 빈객체
    console.log( myResult );                        // 확인

    return(<>
        <h3> 내부 서버 통신 </h3>
        <GlobalTop myLinkClick= {async (num) => {
            // async () => {} vs. async function(){} vs. async function 함수명(){}
            console.log('클릭', num); // 

            // fectch 대신에 axios 사용 중
            const response = await axios.get( `./json/dto${num}.json`);
            const result = response.data;
            setMyResult( result );

        }}></GlobalTop>
    </>)
}