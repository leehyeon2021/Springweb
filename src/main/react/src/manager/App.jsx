import React from "react";
import "./css/App.css";
import DepartmentManager from "./pages/department/DepartmentManager";
import EmployeeManager from "./pages/employee/EmployeeManager";

export default function App() {
  return (
    <div className="page-wrapper">
      <div className="container">
        <h2>인사 관리 대시보드</h2>
        <p className="sub-title">부서, 사원 관리 시스템</p>

        <div className="layout">
          <DepartmentManager />
          <EmployeeManager />
        </div>
      </div>
    </div>
  );
}