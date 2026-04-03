import axios from "axios";
import { useNavigate } from "react-router-dom"


export default function Signup( props ){

    const navigate = useNavigate();

    // 1. REST API로 회원가입 요청
    const signup = async(e) => {
        e.preventDefault();

        // 1. 입력받은 값 가져오기
        const mid = e.target.mid.value;
        const mpwd = e.target.mpwd.value;
        const mname = e.target.mname.value;
        // 2. 객체 구성
        const obj = { mid , mpwd , mname }
        // 3. axios 통신
        try{
            const response = await axios.post("http://localhost:8080/api/member/signup", obj);
            const data = response.data;
            // 4. 가입 확인
            if( data == true ){
                alert(`[가입 성공] ${mname} 님 가입 성공`);
                navigate("/member/login");
            }else{
                alert('[가입 실패] 관리자에게 문의하십시오.');
            }
        }catch(e){console.log(e); alert('[가입 실패] 이미 존재하는 아이디입니다.')}
    }

    return(<>
        <div>
            <h3> 회원가입 페이지 </h3>
            <form onSubmit={ signup }>
                아이디: <input name="mid" type="text" placeholder="아이디 입력"/> <br/>
                비밀번호: <input name="mpwd" type="password" placeholder="비밀번호 입력"/> <br/>
                닉네임: <input name="mname" type="text" placeholder="닉네임 입력"/> <br/>
                <button type="submit"> 가입하기 </button>
            </form>
        </div>
    </>)
}