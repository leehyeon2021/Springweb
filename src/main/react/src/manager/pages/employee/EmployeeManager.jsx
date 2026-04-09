import axios from "axios";
import React, { useEffect, useState } from "react";

export default function EmployeeManager(props) {

  // 부서 출력
  const [ depAry , setDepAry ] = useState([]);
  const findAllDep = async() => {
    try{
      const response = await axios.get("http://localhost:8080/api/department/findAll");
      const data = response.data;
      setDepAry( data );
    }catch(e){console.log(e)}
  }
  useEffect( () => { findAllDep(); } , [] );

  // 사원 출력
  const [ empAry , setEmpAry ] = useState([]);
  const findAllEmp = async() => {
    try{
      const response = await axios.get("http://localhost:8080/api/employee/findAll");
      const data = response.data;
      setEmpAry( data );
    }catch(e){console.log(e)}
  }
  useEffect( () => {findAllEmp();} , [] );


  // 등록
  const addEmp = async(e) => {
    e.preventDefault();
    console.log('[사원] 등록하기 버튼 클릭')

    let eName = e.target.eName.value;
    let eRank = e.target.eRank.value;
    let dId = e.target.dId.value;
    let uploadFile = e.target.uploadFile.files[0];

    const formData = new FormData();
    formData.append('eName', eName);
    formData.append('eRank', eRank);
    formData.append('dId', dId);
    if(uploadFile){
      formData.append('uploadFile', uploadFile);
    }
    try{
      const response = await axios.post("http://localhost:8080/api/employee/add", formData);
      if(response.data == true){
        alert('등록 완료')
        e.target.eName.value="";
        e.target.eRank.value="";
        e.target.dId.value="0";
        findAllEmp();
      }else{
        alert('등록 실패')
        e.target.eName.value="";
        e.target.eRank.value="";
        e.target.dId.value="0";
      }
    }catch(e){console.error(e)}
  }

  // 수정
  const updateEmp = async(eId) => {  
    let eName = prompt('수정할 사원명을 입력하세요.');
    let eRank = prompt('수정할 직급명을 입력하세요.');
    let obj = {eId, eName , eRank};
    try{
      const response = await axios.put("http://localhost:8080/api/employee/update", obj);
      if(response.data == true){
        alert('수정 성공');
        findAllEmp();
      }else{
        alert('수정 실패');
      }    
    }catch(e){console.error(e)}
  }

  // 삭제
  const deleteEmp = async(eId) => {
    const result = confirm('정말 삭제할까요?');
    if(result == true){
      try{
        const response = await axios.delete(`http://localhost:8080/api/employee/delete?eId=${eId}`);
        if(response.status == 200){
          alert('삭제 성공');
          findAllEmp();
        }else{
          alert('삭제 실패')
        }
      }catch(e){console.error(e)}
    }
  }
  

  return (
    <div className="main">
      {/* 사원 등록 */}
      <form onSubmit={addEmp} className="form-box">
        <h3>사원 등록</h3>
        <div className="form-row">
          <input name="eName" type="text" placeholder="이름" />
          <input name="eRank" type="text" placeholder="직급" />
        </div>
        <div className="form-row">
          <select name="dId">
            <option value="0">부서를 선택하세요</option>
          {
            depAry.map( (dep) => {
              return(              
                  <option key={dep.dId} value={dep.dId}>{dep.dName}</option>                
              )
            })
          }
          </select>
          <input name="uploadFile" type="file" />
        </div>
        <div className="form-action">
          <button type="Submit" className="primary">등록</button>
        </div>
      </form>

      {/* 사원 목록 */}
      <div className="table-box">
        <h3>사원 전체 목록</h3>
        <table>
          <thead>
            <tr>
              <th>사진</th>
              <th>이름</th>
              <th>부서</th>
              <th>직급</th>
              <th>관리</th>
            </tr>
          </thead>
          <tbody>
            {
              empAry.map( (emp) => {
                return(
                  <tr key={emp.eId}>
                    <td><img src={`http://localhost:8080/upload/${emp.eFile}`} className="img-box" /> </td>
                    <td>{emp.eName}</td>
                    <td>{emp.dName}</td>
                    <td>{emp.eRank}</td>
                    <td>
                      <span onClick={()=> {updateEmp(emp.eId);}} className="edit">수정</span>
                      <span onClick={()=> {deleteEmp(emp.eId);}} className="delete">삭제</span>
                    </td>
                  </tr>
                )
              })
            }
          </tbody>
        </table>
      </div>
    </div>
  );
}