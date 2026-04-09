import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function DepartmentManager() {

  const navigete = useNavigate();

  // 전체 조회
  const [ depAry , setDepAry ] = useState([]);

  const findAllDep = async() => {
    try{
      const response = await axios.get("http://localhost:8080/api/department/findAll");
      const data = response.data;
      setDepAry( data );
      console.log(data);
    }catch(e){console.log(e)}
  }

  const addDep = async(e) => {
    e.preventDefault();
    console.log('등록하기 버튼 클릭됨');

    let dName = e.target.dName.value;
    console.log(dName);
    let obj = { dName };
    try{
      const response = await axios.post("http://localhost:8080/api/department/add", obj);
      const data = response.data;
      console.log(data);

      if(data){
        alert('등록이 완료되었습니다.')
        navigete("/");
      }else{
        alert('등록에 실패했습니다.')
        navigete("/")
      }
    }catch(e){console.error(e)}
  }


  return (
    <div className="sidebar">
      <h3>부서 관리</h3>

      <form className="dept-input" onSubmit={addDep}>
        <input name="bName" type="text" placeholder="신규 부서명 입력" />
        <button type="submit">추가</button>
      </form>

      <table className="dept-table">
        <thead>
          <tr>
            <th>부서명</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
          {
            depAry.map( (dep) => {
              return(
                <tr key={dep.dId}>
                  <td>{dep.dName}</td>
                  <td>
                    <span className="edit">수정</span>
                    <span className="delete">삭제</span>
                  </td>
                </tr>
              )
            })
          }
        </tbody>
      </table>
    </div>
  );
}