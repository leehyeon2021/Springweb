import axios from "axios";


export default function Write( props ){
    
    // 1. REST API로 글쓰기 요청
    const boardWrite = async( e ) => {
        e.preventDefault();

        // 0) token
        const token = localStorage.getItem('token');
        // 1) 입력받은 값 가져오기
        const btitle = e.target.btitle.value;
        const bcontent = e.target.bcontent.value;
        const uploadFile = e.target.uploadFile.files[0];
            // .value: 입력받은 자료
            // .files: file type에 등록된 파일 (file은 '입력받는'게 아니라 '선택'하는 html이라 value가 없음)
                // files[0] : 선택된 1개의 파일 (0번)
        // 2) 객체 구성이 아닌 멀티폼(대용량byte) 객체 
            // 탈란드의 body에 obj객체(json)이 아니라 form으로 넣기. (multipart/form-data)
        const formData = new FormData() // 대용량 폼을 지원하는 객체
        formData.append( 'btitle', btitle ); // .append( 속성명, 값 )
        formData.append( 'bcontent', bcontent );
        formData.append( 'uploadFile', uploadFile );
        // * 만약에 첨부파일이 존재하면 추가
        if(uploadFile){ formData.append('uploadFile', uploadFile );}
        // 3) AXIOS
        const response = await axios.post('http://localhost:8080/api/board/write3'
                                            , formData
                                            , { headers : { Authorization : `Bearer ${ token }`}}
                                        );
        // 4) 결과
        const data = response.data;
        if( data == true ){
            alert('글쓰기 성공');
        }else{
            alert('글쓰기 실패');
        }

    }
    
    return(<>
        <div>
            <h3> 글쓰기 페이지 </h3>
            <form onSubmit={ boardWrite }>
                제목: <input name="btitle" type="text"/> <br/>
                내용: <textarea name="bcontent" type="text"></textarea> <br/>
                첨부파일: <input name="uploadFile" type="file"/> <br/>
                <button type="submit"> 등록하기 </button>
            </form>
        </div>
    </>)
}