import axios from "axios";


export default function Login( props ){

    // REST API 에게 AXIOS로 통신하기
    const login = async( e ) => {   
        e.preventDefault()
        // 1. 입력받은 값 가져오기
        const mid = e.target.mid.value;
        const mpwd = e.target.mpwd.value;
        // 2. 객체 구성: 전송할 내용
        const obj = { mid , mpwd }
        // 3. axios 통신
        const response = await axios.post( "http://localhost:8080/api/member2/login" , obj );
        // 4. 인증 결과 확인 (HTTP headers에 Authorization 속성 확인)
        let token = response.headers['authorization'];
        console.log(token);
        // 5. 인증 결과 분기
        if(token && token.startsWith("Bearer ")){ // Bearer 뒤로 띄어쓰기 주의
            token = token.substring(7);
                // 문자열 내 7번째 부터 자른 값을 대입. 즉) Bearer 제거
        }
        if( token ){

            // 페이지 이동 전에 localStorage에 토큰 저장. (글쓰기 할 경우 token 필요)
            localStorage.setItem("token", token); // token이라는 이름으로 서버로 부터 받은 token 저장.

            alert('로그인 성공');
            location.href="/";
                // 웬만하면 navigate를 하지만,
                // 로그인/로그아웃은 초기화가 필요하기 때문에 location 사용.
                    // 주로 인증(Autorizaion)이 중요한 것들.
        }else{ alert('로그인 실패') }
    }
    
    return(<>
        <div>
            <h3> 로그인 페이지 </h3>
            <form onSubmit={ login }>
                {/* DTO와 일치하면 편하다 */}
                아이디: <input name="mid" placeholder="아이디 입력"/> <br/>
                비밀번호: <input name="mpwd" placeholder="비밀번호 입력"/> <br/>
                {/* submit: 현재 form 안에 있는 마크업들 전송 이벤트 */}
                <button type="sumit"> 로그인 </button>
            </form>
        </div>
    </>)

}