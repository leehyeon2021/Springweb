import axios from "axios";
import React, { useEffect, useState } from "react";

export default function DepartmentManager(prpos) {

  // 전체 조회
  const [ depAry , setDepAry ] = useState([]);

  const findAllDep = async() => {
    try{
      const response = await axios.get("http://localhost:8080/api/department/findAll");
      const data = response.data;
      setDepAry( data );
    }catch(e){console.log(e)}
  }

  // 등록
  const addDep = async(e) => {
    e.preventDefault();
    console.log('[부서] 등록하기 버튼 클릭됨');

    let dName = e.target.dName.value;
    console.log(dName);
    let obj = { dName };
    try{
      const response = await axios.post("http://localhost:8080/api/department/add", obj);
      const data = response.data;
      console.log(data);

      if(data == true){
        alert('등록 완료')
        e.target.dName.value="";
        findAllDep();
      }else{
        alert('등록 실패')
        e.target.dName.value="";
      }
    }catch(e){console.error(e)}
  }

  // 수정
  const updateDep = async(dId) => {
    let dName = prompt('수정할 부서명을 입력하세요.');
    let obj = { dId , dName };
    try{
      const response = await axios.put("http://localhost:8080/api/department/update", obj);
      if(response.data == true){
        alert('수정 성공');
        findAllDep();
      }else{
        alert('수정 실패');
      }
    }catch(e){console.error(e)}
  }

  // 삭제
  const deleteDep = async(dId) => {
    const result = confirm('정말 삭제할까요?');
    if(result == true){
      try{
        const response = await axios.delete(`http://localhost:8080/api/department/delete?dId=${dId}`);
        if(response.status == 200){
          alert('삭제 성공');
          findAllDep();
        }else{
          alert('삭제 실패')
        }
      }catch(e){console.error(e)}
    }
  }

  useEffect( () => { findAllDep(); } , [] );

  return (
    <div className="sidebar">
      <h3>부서 관리</h3>

      <form className="dept-input" onSubmit={addDep}>
        <input name="dName" type="text" placeholder="신규 부서명 입력" />
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
                    <span onClick={() => {updateDep(dep.dId);}} className="edit">수정</span>
                    <span onClick={() => {deleteDep(dep.dId);}} className="delete">삭제</span>
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