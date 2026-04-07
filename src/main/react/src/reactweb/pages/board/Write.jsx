import axios from "axios";
import { useNavigate } from "react-router-dom";

import { useState } from 'react';
import ReactQuill from 'react-quill-new';
import 'react-quill-new/dist/quill.snow.css';


export default function Write( props ){

    const navigate = useNavigate();
    
    // 1. REST API로 글쓰기 요청
    const boardWrite = async( e ) => {
        e.preventDefault();

        // 0) token
        //const token = localStorage.getItem('token'); -> 쿠키 사용 시 제거

        // 1) 입력받은 값 가져오기
        const btitle = e.target.btitle.value;
        //const bcontent = e.target.bcontent.value; -> 쿠키 사용 시 제거
                                                    // textarea -> quill로 변경했기 때문
        const uploadFile = e.target.uploadFile.files[0];
            // .value: 입력받은 자료
            // .files: file type에 등록된 파일 (file은 '입력받는'게 아니라 '선택'하는 html이라 value가 없음)
                // files[0] : 선택된 1개의 파일 (0번)

        // 2) 객체 구성이 아닌 멀티폼(대용량byte) 객체 
            // 탈란드의 body에 obj객체(json)이 아니라 form으로 넣기. (multipart/form-data)
        const formData = new FormData() // 대용량 폼을 지원하는 객체
        formData.append( 'btitle', btitle ); // .append( 속성명, 값 )
        //formData.append( 'bcontent', bcontent );
        formData.append( 'bcontent', value ); // textarea -> quilld 에디터가 담고 있는 vaule 상태변수 대입

        // * 만약에 첨부파일이 존재하면 추가
        if(uploadFile){ formData.append('uploadFile', uploadFile );}

        // 3) AXIOS
        const response = await axios.post('http://localhost:8080/api/board/write4'
                                            , formData
                                            //, { headers : { Authorization : `Bearer ${ token }`}}
                                            , {withCredentials : true}
                                        );
        // 4) 결과
        const data = response.data
        if( data == true ){
            alert('글쓰기 성공');
            navigate( "/board" );
        }else{
            alert('글쓰기 실패');
        }

    }
    

    // 리액트 에디터 사용 (react-quill-new )
    const [value, setValue] = useState('');
    // * 웹 에디터 설정 변경
    const modules = {
        toolbar: [
            [ { header : [1, 2, 3, 4, 5, 6] } ],                    // 헤더 종류 <h3>
            [ {"list" : "ordered"}, { "list" : "bullet"} ],         // 리스트 <ol>
            [ "bold" , "italic" , "underline"],
            ["image"] // 이미지 기능 추가
        ]
    }
    const formats = [
        "header",
        "list",
        "bold",
        "italic",
        "underline",
        "image"
    ]

    
    return(<>
        <div>
            <h3> 글쓰기 페이지 </h3>
            <form onSubmit={ boardWrite }>
                제목: <input name="btitle" type="text"/> <br/>

                {/* 내용: <textarea name="bcontent" type="text"></textarea> <br/> 이거 주석하고
                    웹 에디터 사용 (bcontent->value)*/}
                <ReactQuill 
                    theme="snow"
                    value={ value }
                    onChange={ setValue }
                    modules={ modules }
                    formats={ formats }
                />

                첨부파일: <input name="uploadFile" type="file"/> <br/>
                <button type="submit"> 등록하기 </button>
            </form>
        </div>
    </>)
}