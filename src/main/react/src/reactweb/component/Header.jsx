import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header( props ){

    // 2. 로그인 상태를 저장하는 상태 변수 (로그인 여부)
    const [ login , setLogin ] = useState( false ); 
        // 초기 값은 false 로그인 안 했다는 뜻

    // 3. 로그인 중인 회원 정보를 담는 상태 변수 (로그인 여부)
    const [ user , setUser ] = useState( null );

    // 1. 로그인 상태에 따라 메뉴 분기 (로그인하면 로그인/회원가입 X)
    const getMyInfo = async () => {
        // 1) 로그인 시 localStorage에 저장한 token 가져오기. (.setItem , .getItem)
        //const token = localStorage.getItem( 'token' ); -> 쿠키 사용 시 제거
        // 2) 만약에 token이 없으면 - 함수 종료 (로그인상태)
        //if( !token ){ setLogin( false ); return; } -> 쿠키 사용 시 제거
        // 3) 헤더에 표시할 로그인 된 유저 아이디 가져오기
        const response = await axios.get('http://localhost:8080/api/member3/my/info'
                                        //, { headers : { Authorization : `Bearer ${token}`} } -> 쿠키 사용 시 제거
                                            // { headers : { 속성명 , 값 } } (탈란드 header에 넣었던 것처럼.)
                                            // axios 특징: Content-Type : application/json이 기본값
                                            // 그러므로 아닐 경우 명시해야 한다. { headers : { 여기에! } }
                                        , {withCredentials : true}) // header에 쿠키(+토큰포함) 전송으로 변경
        // 4) 통신 결과 보기 
        const data = response.data;
        if( data || data != false ){ // 응답 자료(Dto)가 존재하면
            setLogin( true ); // 로그인 상태 변경
            setUser( data ); // 응답받은 자료(회원정보)를 저장
        }else{ setLogin(false);} // 비로그인상태 변경
    }

    // 4. 헤더가 열리면 최초 1번 실행 (로그인상태 - 백엔드에서 검증할 수밖에 없음 - 통신 필요)
    useEffect( () => { getMyInfo(); } , [] );


    // 5. 로그아웃
    // const logout = () => {
    //     // 1. localStorage 에서 token 제거 (.removeItem())
    //     localStorage.removeItem( 'token' );
    //     // 2. 로그아웃 상태 변경
    //     setLogin( false );
    //     // 3. 로그아웃
    //     alert('로그아웃 성공'); location.href="/";
    // }

    // 5. 로그아웃 (토큰+쿠키)
    const logout = async() => {
        
        // 1. axios
        const response = await axios.get(
            "http://localhost:8080/api/member3/logout" // 통신할 서버의 경로
            , {withCredentials : true} // 쿠키(+토큰포함) 전송
        )

        // 2. 로그인 상태 변경 , 안내후 페이지 변경
        setLogin( false );
        alert('로그아웃');
        location.href="/"
    }
    
    // localStorage는 브라우저를 껐다 켜도 남아있음. 
    // 사용자가 '로그아웃'하지 않으면 자동으로 로그인 상태.
        // => Cookies 사용! (로컬과 쿠키 둘 다 사용)

    return(<>
        <div>
            <Link to="/"> 홈 </Link> |
            <Link to="/board"> 글 목록 </Link> | {/*로그인&비로그인모두조회가능*/}

            {/* 비회원 메뉴 */}
            { login == false && (<> 
                <Link to="/member/login"> 로그인 </Link> |
                <Link to="/member/signup"> 회원가입 </Link> |
            </>) }

            {/* 회원 메뉴 */}
            { login == true && (<>
                <span> {user.mname} 님 </span> |
                <Link to="/member/page"> 내 정보 </Link> |
                <Link to="/board/write"> 글쓰기 </Link> |
                <Link to="/chat"> 채팅방 </Link> |
                <button onClick={ logout }> 로그아웃 </button>
            </>) }

            <hr/>
        </div>
    </>)
}


/* 

### JS 삼항 연산자
: 조건 ? 참실행문 : 거짓실행문
   - 조건이 참이면 참 실행문, 거짓이면 거짓 실행문 실행.

### JS 단축 평가
: 조건 && 실행문
   - 조건이 참이면 실행문, 거짓이면 생략

*/