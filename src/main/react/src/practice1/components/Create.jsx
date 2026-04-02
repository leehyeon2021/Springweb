import axios from "axios";
import { Link, useNavigate } from "react-router-dom";


export default function Create( props ){

    // * location.href와 같은 것을 깜빡거림 없이 라우터로 페이지 전환
    const navigete = useNavigate();
        // navigete("이동경로")

    // 등록함수 (등록하기 버튼을 클릭하면)
    const taskpost = async( event ) => {
        // * <form>도 <a>처럼 깜빡거림
        event.preventDefault(); // 기존 이벤트 제거
        // 1. event란? 해당 이벤트(등록/클릭)을 발생시킨 정보를 담고 있는 객체
        console.log('등록하기 버튼 클릭됨');
        // 2. 입력받은 값 가져오기. document.querySelector()
            // <form> 식별자는 'name'을 사용한다.
        let title = event.target.title.value;           console.log(title);
        let content = event.target.content.value;       console.log(content);
        let requester = event.target.requester.value;   console.log(requester);
        let status = event.target.status.value;         console.log(status);
        // 2. (REST API에 전송할) 객체 구성
        let obj = {title, content , requester, status};
        // 3. axios
        const response = await axios.post('http://localhost:8080/api/task' ,obj);
        const data = response.data;        console.log(data);

        // 확인. location.href="/";도 깜빡임 O , useNavigate 사용.
        if(data){
            alert("등록이 완료되었습니다.");
            //location.href="/"; <a> 깜빡거림
            navigete("/");
        }
        else{
            alert("등록에 실패하였습니다."); 
            //location.href="/";
            navigete("/");
        }

    }
   

    return(<>
        <br/><Link to="/">뒤로 가기</Link>
        <h3> 등록 페이지 </h3>
        <form onSubmit={ taskpost }>
            제목: <input name="title"/> <br/>
            내용: <input name="content"/><br/> 
            요청자명: <input name="requester"/><br/>
            상태: <select name="status">
                <option> 요청 </option>
                <option> 진행중 </option>
                <option> 완료 </option>
            </select> <br/>
            <button type="submit">등록하기</button>
        </form>
    </>) 

}