// (260312)

import {useState} from 'react';

function WriteForm( props ){
    // event : e객체란? : 해당 이벤트가 발생했을 때 그 이벤트 정보를 담고 있는 객체
    return(<>
        <form onSubmit={  (event) => {
            event.preventDefault(); // 기존 이벤트(전송방식) 제거
            // 이전 방식: let gubun = document.querySelector();  |  리액트: event 주최자 찾아서 가져옴
            console.log( "이벤트객체 : " , event);
            console.log( "이벤트를 발생시킨 주범 : " , event.target); // -> <form>이 발생시킴
            console.log( event.target.gubun ) // <form> --> 하위요소 , target.class명
            let gubun = event.target.gubun.value; // <form> --> <select>
            let title = event.target.title.value; // <form> --> <select>

            // 함수 실행: 부모 컴포넌트로 부터 받은 함수에 입력 받은 구분과 타이틀을 대입하여 함수를 실행한다.
            props.writeAction( gubun , title );
        } }>
            <select name="gubun">
                <option value="front"> 프론트엔드 </option>
                <option value="back"> 백엔드 </option>
            </select>
            <input type="text" name="title"/>
            <input type="submit" value="추가"/>
        </form>
    </>)
}

export default function Exam2( props ){

    // 상태변수(어제한거): 하나의 값을 저장하고 변경되면 해당 컴포넌트가 재호출(렌더링)된다.
    const [ message , setMessage ] = useState('폼값 검증 진행 중');

    // 자식에게 전달할 함수
    const writeAction = ( gubun , title ) => {
        if(gubun != '' &&  title != ''){
            let frmValue = `검증 완료 폼값: ${gubun} , ${title}`;
            setMessage(frmValue); // 등록 했을 때 setXXX() 실행되면 해당 컴포넌트(Exam2)도 재호출된다. 
                // 즉: return이 재실행된다. 
        }else{alert('빈 칸 있음');}
    }

    return(<>
        <h3> p.135 </h3>
        <WriteForm writeAction={ writeAction }/>
        <pre>{message}</pre>
    </>)
}